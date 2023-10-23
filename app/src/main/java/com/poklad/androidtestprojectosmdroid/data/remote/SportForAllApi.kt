package com.poklad.androidtestprojectosmdroid.data.remote

import retrofit2.http.GET

interface SportForAllApi {
    @GET
    suspend fun getClubsNewsStatus(): List<ClubNewsStatusResponse>
}