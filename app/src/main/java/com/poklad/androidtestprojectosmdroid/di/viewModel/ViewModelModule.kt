package com.poklad.androidtestprojectosmdroid.di.viewModel

import androidx.lifecycle.ViewModel
import com.poklad.androidtestprojectosmdroid.di.annotations.ViewModelKey
import com.poklad.androidtestprojectosmdroid.presentation.screens.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindGiphyListViewModel(yourViewModel: MapViewModel): ViewModel

}

