package com.example.wizeline.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.room.Room
import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.RemoteDataSourceImpl
import com.example.wizeline.data.datasource.models.WifiConnected
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.data.repository.CurrencyRepositoryImpl
import com.example.wizeline.data.service.BaseService
import com.example.wizeline.data.service.GenericInterceptor
import com.example.wizeline.database.WizelineDatabase
import com.example.wizeline.database.dao.AvailableBooksDao
import com.example.wizeline.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
abstract class BitsoModule {

    @Binds
    abstract fun bindsBitsoRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    companion object{
        @Provides
        fun providesRetrofitClient(): Retrofit {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(GenericInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build()
        }

        @Provides
        fun providesBitsoService(retrofit: Retrofit): BaseService =
            retrofit.create(BaseService::class.java)

        @Provides
        fun providesDatabase(@ApplicationContext context: Context): WizelineDatabase = Room.databaseBuilder(
            context,
            WizelineDatabase::class.java,
            "APP_DATABASE"
        ).build()

        @Provides
        fun providesBooksDao(database: WizelineDatabase): AvailableBooksDao = database.booksDao()

        @Provides
        fun isOnline(@ApplicationContext context: Context): WifiConnected {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return WifiConnected(true)
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return WifiConnected(true)
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return WifiConnected(true)
                    }
                }
            }
            return WifiConnected(false)
        }
    }

}