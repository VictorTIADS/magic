package com.bootcamp.magic.Injection

import com.bootcamp.magic.Database.ItemsDatabase
import com.bootcamp.magic.ViewModel.DetailFragmentViewModel
import com.bootcamp.magic.ViewModel.FavoriteFragmentViewModel
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodel = module {
    viewModel { DetailFragmentViewModel(get()) }
    viewModel { HomeFragmentViewModel.getInstance() }
    viewModel { FavoriteFragmentViewModel(get()) }
}

val databaseModel = module {
    single { ItemsDatabase.getAppDataBase(androidApplication())?.itemsDAO() }
}
