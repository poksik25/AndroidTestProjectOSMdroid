package com.poklad.androidtestprojectosmdroid.presentation.screens

import com.poklad.androidtestprojectosmdroid.presentation.base.BaseViewModel
import com.poklad.androidtestprojectosmdroid.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class MapViewModel  @Inject constructor(
    dispatcher: CoroutineDispatchersProvider
) : BaseViewModel(dispatcher) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}