package hu.unideb.pedometer.ui.profile.json

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.pedometer.R
import hu.unideb.pedometer.api.JsonApi
import hu.unideb.pedometer.data.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Json : Fragment() {

    companion object {
        fun newInstance() = Json()
    }

    private lateinit var viewModel: JsonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = hu.unideb.pedometer.data.JsonAdapter()

        JsonApi().getData().enqueue(object: Callback<List<JsonObject>> {
            override fun onFailure(call: Call<List<JsonObject>>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<JsonObject>>, response: Response<List<JsonObject>>) {
                val inf = response.body()

                inf?.let {
                    adapter.jsons=it
                }
            }

        })

        val rView = activity?.findViewById(R.id.json_recycle) as RecyclerView
        rView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rView.adapter = adapter

        return inflater.inflate(R.layout.json_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(JsonViewModel::class.java)
        }

}
