package hu.unideb.pedometer.ui.auth.login

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import hu.unideb.pedometer.R
import hu.unideb.pedometer.databinding.LoginFragmentBinding
import hu.unideb.pedometer.ui.ProfileActivity
import hu.unideb.pedometer.ui.auth.UserData
import kotlinx.android.synthetic.main.login_fragment.*

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private val userData: UserData = UserData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginFragment_login.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            activity?.startActivity(intent)
        }
    }

}