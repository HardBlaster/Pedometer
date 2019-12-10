package hu.unideb.pedometer.ui.profile.json

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.annotations.JsonAdapter

import hu.unideb.pedometer.R
import hu.unideb.pedometer.ui.ProfileActivity
import kotlinx.android.synthetic.main.json_fragment.*

class Json : Fragment() {

    companion object {
        fun newInstance() = Json()
    }

    private lateinit var viewModel: JsonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.json_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(JsonViewModel::class.java)

        val jsons = viewModel.getJson()

        Log.d("JSON", "elvileg itt is megvan")

        json_recycle.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = hu.unideb.pedometer.data.JsonAdapter(jsons)
        }

        Log.d("JSON", "Elvileg ki is dobta")
        }

    fun showError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT)
    }

}
