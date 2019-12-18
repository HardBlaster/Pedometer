package hu.unideb.pedometer.ui.auth.registration

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import hu.unideb.pedometer.R
import hu.unideb.pedometer.data.User
import hu.unideb.pedometer.databinding.RegistrationFragmentBinding
import hu.unideb.pedometer.data.UserData
import hu.unideb.pedometer.database.PMDatabase

class Registration : Fragment() {

    var newUser = User()

    companion object {
        fun newInstance() = Registration()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PMDatabase.getInstance(application).userDAO
        val viewModelFactory = RegistrationViewModellFactory(dataSource)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(RegistrationViewModel::class.java)

        binding.registrationFragmentRegistration.setOnClickListener {
            doRegistration()
        }

        return binding.root
    }

    fun doRegistration() {
        binding.apply {
            newUser.username = registrationFragmentUsername.text.toString()
            newUser.password = registrationFragmentPassword.text.toString()
            newUser.email = registrationFragmentEmail.text.toString()

            Log.d("TTTT", newUser.toString())

            viewModel.registration(newUser)
        }
    }
}
