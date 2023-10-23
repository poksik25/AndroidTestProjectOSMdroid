package com.poklad.androidtestprojectosmdroid.di.module

import com.poklad.androidtestprojectosmdroid.domain.MapRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindMapRepositoryImpl(repositoryImpl: MapRepository): MapRepository
}