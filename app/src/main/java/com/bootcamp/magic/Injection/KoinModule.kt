package com.bootcamp.magic.Injection

import com.bootcamp.magic.ViewModel.DetailFragmentViewModel
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodel = module {
    viewModel { DetailFragmentViewModel() }
    viewModel { HomeFragmentViewModel() }
}