package hu.unideb.pedometer.api

import hu.unideb.pedometer.data.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/albums")
    fun getAllAlbums() : Call<List<JsonObject>>
}