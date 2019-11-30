package hu.unideb.pedometer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import hu.unideb.pedometer.R
import kotlinx.android.synthetic.main.activity_auth.*

import android.view.View
import kotlinx.android.synthetic.main.activity_auth.toolbar
import kotlinx.android.synthetic.main.activity_profile.*

class AuthActivity : AppCompatActivity(){

    private lateinit var welcomeNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setSupportActionBar(toolbar)

        welcomeNavController = Navigation.findNavController(this, R.id.welcome_fragment)
        welcome_bottom_nav.setupWithNavController(welcomeNavController)
        NavigationUI.setupActionBarWithNavController(this, welcomeNavController)

        


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(welcomeNavController, null)
    }

}
