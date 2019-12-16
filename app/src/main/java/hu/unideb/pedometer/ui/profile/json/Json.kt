package hu.unideb.pedometer.ui.profile.json

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.pedometer.R
import hu.unideb.pedometer.api.JsonApi
import hu.unideb.pedometer.data.JsonListener
import hu.unideb.pedometer.data.JsonObject
import hu.unideb.pedometer.databinding.ItemLayoutBinding
import hu.unideb.pedometer.databinding.JsonFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Json : Fragment() {

    companion object {
        fun newInstance() = Json()
    }

    private lateinit var viewModel: JsonViewModel
    private lateinit var binding: JsonFragmentBinding

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.json_fragment, container, false)
        val adapter = hu.unideb.pedometer.data.JsonAdapter(JsonListener { jsonId ->
            val toast= Toast.makeText(context, "${jsonId}", Toast.LENGTH_LONG)
            toast.show()
            toast.duration=20
            //  viewModel.onSleepNightClicked(wordId)
        })

        JsonApi().getData().enqueue(object: Callback<List<JsonObject>> {
            override fun onFailure(call: Call<List<JsonObject>>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<JsonObject>>, response: Response<List<JsonObject>>) {
                val inf = response.body()

                inf?.let {
                    adapter.addHeaderAndSubmitList(it)
                }
            }

        })
        binding.jsonRecycle.adapter=adapter


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(JsonViewModel::class.java)
        }

}


