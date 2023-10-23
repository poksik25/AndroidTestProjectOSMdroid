package com.poklad.androidtestprojectosmdroid.data

import com.poklad.androidtestprojectosmdroid.di.annotations.ApplicationScope
import com.poklad.androidtestprojectosmdroid.domain.MapRepository
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

@ApplicationScope
class MapRepositoryImpl @Inject constructor() : MapRepository {
    override suspend fun getPoints(): List<PointModel> {
        return listOf(
            PointModel(1, "Історичний музей", GeoPoint(49.9918804, 36.2316152)),
            PointModel(2, "New Mail №6", GeoPoint(50.0078178, 36.3034023)),
            PointModel(3, "Multiplex", GeoPoint(49.9899739, 36.2351798)),
            PointModel(4, "Nikas Restaurant", GeoPoint(49.9929846, 36.229371)),
            PointModel(5, "Derzhprom", GeoPoint(49.9975076, 36.218518)),
        )
    }

    override suspend fun buildRoad(startPoint: PointModel, endPointModel: PointModel) {
        TODO("Not yet implemented")
    }
}