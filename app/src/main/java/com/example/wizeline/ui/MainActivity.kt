package com.example.wizeline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.wizeline.R
import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.RemoteDataSourceImpl
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.data.repository.CurrencyRepositoryImpl
import com.example.wizeline.data.service.service
import com.example.wizeline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[BitsoViewModel::class.java] }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

}