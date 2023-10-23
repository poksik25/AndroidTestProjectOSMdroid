package com.poklad.androidtestprojectosmdroid.domain

import com.poklad.androidtestprojectosmdroid.data.PointModel
import javax.inject.Inject

class GetAllPointUseCase  @Inject constructor(
    private val repository: MapRepository
) : UseCase<Unit, List<PointModel>> {
    override suspend fun execute(parameter: Unit): List<PointModel> {
        return repository.getPoints()
    }
}