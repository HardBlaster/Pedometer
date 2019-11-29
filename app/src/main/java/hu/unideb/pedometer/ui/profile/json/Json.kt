package hu.unideb.pedometer.ui.profile.json

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hu.unideb.pedometer.R

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
        // TODO: Use the ViewModel
    }

}
