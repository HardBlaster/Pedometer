package hu.unideb.pedometer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import hu.unideb.pedometer.R
import kotlinx.android.synthetic.main.activity_main.*

import android.view.View
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.profile_layout.*

class MainActivity : AppCompatActivity(){

    private lateinit var welcomeNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        welcomeNavController = Navigation.findNavController(this, R.id.welcome_fragment)
        welcome_bottom_nav.setupWithNavController(welcomeNavController)
        NavigationUI.setupActionBarWithNavController(this, welcomeNavController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(welcomeNavController, null)
    }

    fun hideWelcomeLayout() {
        welcome_layout.visibility = View.GONE
    }
}
