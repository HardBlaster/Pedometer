package hu.unideb.pedometer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.user_registration.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = DBHandler(this)

        showHome()

        main_registration.setOnClickListener {
            showRegistration()
        }

        main_login.setOnClickListener {
            showLogin()
        }

        registration_register.setOnClickListener {
            val username = registration_name.text.toString()
            val email = registration_email.text.toString()
            val password = registration_password.text.toString()

            if(!dbHandler.usernameExists(username) || !dbHandler.emailExists(email)) {

                dbHandler.insertUserData(username, email, password)
                registration_message.text = getString(R.string.reg_success)
                registration_message.setTextColor(Color.GREEN)

            } else {

                if (dbHandler.usernameExists(username)) {

                    registration_message.text = getString(R.string.username_duplicate)
                    registration_message.setTextColor(Color.RED)

                } else {

                    registration_message.text = getString(R.string.email_duplicate)
                    registration_message.setTextColor(Color.RED)

                }
            }
        }

        login_login.setOnClickListener {
            val username = login_username.text.toString()
            val password = login_password.text.toString()

            if(dbHandler.registeredUser(username, password)) {

                login_message.text = getString(R.string.login_success)
                login_message.setTextColor(Color.GREEN)

            } else {

                login_message.text = getString(R.string.login_error)
                login_message.setTextColor(Color.RED)

            }
        }
    }

    private fun showRegistration() {
        registration_layout.visibility = View.VISIBLE
        login_layout.visibility = View.GONE
        home_layout.visibility = View.GONE
    }

    private fun showLogin() {
        login_layout.visibility = View.VISIBLE
        registration_layout.visibility = View.GONE
        home_layout.visibility = View.GONE
    }

    private fun showHome() {
        home_layout.visibility = View.VISIBLE
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.GONE
    }
}
