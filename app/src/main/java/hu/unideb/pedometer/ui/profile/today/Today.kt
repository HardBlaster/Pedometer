package hu.unideb.pedometer.ui.profile.today

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import hu.unideb.pedometer.R
import hu.unideb.pedometer.data.History
import hu.unideb.pedometer.database.PMDatabase
import hu.unideb.pedometer.databinding.TodayFragmentBinding


class Today() : Fragment(), SensorEventListener {

    companion object {
        fun newInstance() = Today()
    }

    private lateinit var viewModel: TodayViewModel
    private lateinit var binding: TodayFragmentBinding
    var running = false
    var sensorManager: SensorManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.today_fragment, container, false)
        binding.goJson.setOnClickListener {
            goToJson()
        }

        sensorManager =
            activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(context, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = PMDatabase.getInstance(application).historyDAO
        val viewModelFactory = TodayViewModellFactory(dataSource)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayViewModel::class.java)
    }

    fun goToJson(){
        val action=TodayDirections.actionTodayFragmentToJSONFragment()
        view!!.findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(context, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)

        var history: History? = viewModel.database.getHistory(0).value?.get(0)
        if(history == null) {
            history = History(0, binding.todayStepCount.text.toString().toInt())
        }

        viewModel.database.upsert(history)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            binding.todayStepCount.text = event?.values?.get(0).toString()
        }
    }
}
