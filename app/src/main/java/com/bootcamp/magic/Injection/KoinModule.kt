package com.bootcamp.magic.Injection

import com.bootcamp.magic.ViewModel.DetailFragmentViewModel
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import com.bootcamp.magic.repository.ServiceRequestRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodel = module {
    viewModel { DetailFragmentViewModel() }
    viewModel { HomeFragmentViewModel.getInstance() }
}

