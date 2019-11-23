package hu.unideb.pedometer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.user_registration.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var dbHandler: DBHandler
    var running: Boolean = false
    var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showHome()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        dbHandler = DBHandler(this)

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
                showProfile()

            } else {

                login_message.text = getString(R.string.login_error)
                login_message.setTextColor(Color.RED)

            }
        }
    }

    override fun onResume() {
        super.onResume()

        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(stepsSensor == null) {

            Toast.makeText(this, "Sensor problem occurred!", Toast.LENGTH_SHORT).show()
            println("Gikszer van")

        } else {

            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
            println("elvileg prima")

        }
    }

    override fun onPause() {
        super.onPause()

        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if(running) {

            profile_steps.setText("" + event?.values!![0])
        }
    }

    private fun showRegistration() {
        registration_layout.visibility = View.VISIBLE
        login_layout.visibility = View.GONE
        home_layout.visibility = View.GONE
        profile_layout.visibility = View.GONE
    }

    private fun showLogin() {
        login_layout.visibility = View.VISIBLE
        registration_layout.visibility = View.GONE
        home_layout.visibility = View.GONE
        profile_layout.visibility = View.GONE
    }

    private fun showHome() {
        home_layout.visibility = View.VISIBLE
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.GONE
        profile_layout.visibility = View.GONE
    }

    private fun showProfile() {
        profile_layout.visibility = View.VISIBLE
        login_layout.visibility = View.GONE
        registration_layout.visibility = View.GONE
        home_layout.visibility = View.GONE
    }
}
