package hu.unideb.pedometer.ui.auth.login

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import hu.unideb.pedometer.R
import hu.unideb.pedometer.databinding.LoginFragmentBinding
import hu.unideb.pedometer.ui.ProfileActivity
import hu.unideb.pedometer.data.UserData
import hu.unideb.pedometer.database.PMDatabase
import kotlinx.android.synthetic.main.login_fragment.*

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private val userData: UserData =
        UserData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PMDatabase.getInstance(application).userDAO
        val viewModelFactory = LoginViewModellFactory(dataSource)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)

        viewModel.users.observe(viewLifecycleOwner, Observer {
            x -> Log.d("TTT", x.toString())
        })

        return binding.root
    }
}