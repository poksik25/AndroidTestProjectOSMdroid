package com.poklad.androidtestprojectosmdroid.domain

interface UseCase<Parameter, Result> {
    suspend fun execute(parameter: Parameter): Result
}