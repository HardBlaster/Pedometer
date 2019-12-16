package hu.unideb.pedometer.ui.profile.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import hu.unideb.pedometer.R
import hu.unideb.pedometer.databinding.TodayFragmentBinding
import hu.unideb.pedometer.data.UserData

class Today : Fragment() {

    companion object {
        fun newInstance() = Today()
    }

    private lateinit var viewModel: TodayViewModel
    private lateinit var binding: TodayFragmentBinding
    private lateinit var userData: UserData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.today_fragment, container, false)
        binding.goJson.setOnClickListener {
            goToJson()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun goToJson(){
        val action=TodayDirections.actionTodayFragmentToJSONFragment()
        view!!.findNavController().navigate(action)
    }
}
