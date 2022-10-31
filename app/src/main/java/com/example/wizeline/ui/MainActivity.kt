package com.example.wizeline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.wizeline.R
import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.data.service
import com.example.wizeline.databinding.ActivityMainBinding
import com.example.wizeline.domain.FilterCurrenciesUseCase

class MainActivity : AppCompatActivity() {
    private val bitsoViewModel by viewModels<BitsoViewModel> {
        BitsoViewModelFactory(
            FilterCurrenciesUseCase(
                CurrencyRepository(
                    RemoteDataSource(
                        service
                    )
                )
            ))
    }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

}