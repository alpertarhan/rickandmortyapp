package com.project.rickmortycaseapp.network

import com.project.rickmortycaseapp.models.AppResponse
import com.project.rickmortycaseapp.utils.Credentials
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Credentials.END_POINT)
    suspend fun getAllCharacters(
        @Query("page")  page:Int
    ):Response<AppResponse>
}