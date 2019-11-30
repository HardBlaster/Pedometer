package hu.unideb.pedometer.ui.auth.registration

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import hu.unideb.pedometer.R
import hu.unideb.pedometer.databinding.RegistrationFragmentBinding
import hu.unideb.pedometer.ui.ProfileActivity
import hu.unideb.pedometer.ui.auth.UserData
import kotlinx.android.synthetic.main.registration_fragment.*

class Registration : Fragment() {

    companion object {
        fun newInstance() = Registration()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding
    private val userData: UserData = UserData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationFragment_registration.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}
