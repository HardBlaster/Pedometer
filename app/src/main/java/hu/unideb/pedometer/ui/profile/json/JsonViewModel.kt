package hu.unideb.pedometer.ui.profile.json

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import hu.unideb.pedometer.api.ApiService
import hu.unideb.pedometer.data.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonViewModel : ViewModel() {

    fun getJson() : MutableList<JsonObject> {
        val jsons = mutableListOf<JsonObject>()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getAllAlbums().enqueue(object: Callback<List<JsonObject>> {
            override fun onFailure(call: Call<List<JsonObject>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<JsonObject>>,
                response: Response<List<JsonObject>>
            ) {

                jsons.addAll(response.body()!!)

            }

        })

        return jsons
    }
}
