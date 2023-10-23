package com.poklad.androidtestprojectosmdroid.domain

import com.poklad.androidtestprojectosmdroid.data.PointModel

interface MapRepository {
    suspend fun getPoints(): List<PointModel>
    suspend fun buildRoad(startPoint: PointModel, endPointModel: PointModel)
}