package hu.unideb.pedometer.api

import hu.unideb.pedometer.data.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface  JsonApi {

    @GET("albums")
    fun getData() : Call<List<JsonObject>>

    companion object {
        operator fun invoke(): JsonApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonApi::class.java)
        }
    }
}