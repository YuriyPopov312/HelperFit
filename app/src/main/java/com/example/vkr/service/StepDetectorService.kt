package com.example.vkr.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.vkr.Activity.ClientActivity
import com.example.vkr.helper.GeneralHelper

import com.example.vkr.callback.stepsCallback
import com.example.vkr.helper.PrefsHelper
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageSetting
import kotlin.math.roundToInt

class StepDetectorService : Service(), SensorEventListener {

    companion object {
        lateinit var callback: stepsCallback
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val countSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(countSensor != null){
            Toast.makeText(this, "Начинаем считать шаги", Toast.LENGTH_SHORT).show()
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL)

            GeneralHelper.updateNotification(this, this, PrefsHelper.getInt("FSteps"))
            callback.subscribeSteps(PrefsHelper.getInt("FSteps"))

        }else{
            Toast.makeText(this, "Нe получается отследить вашу активность", Toast.LENGTH_SHORT).show()
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onSensorChanged(p0: SensorEvent?) {
        if (PrefsHelper.getString("TodayDate") != GeneralHelper.getToadyDate() || StorageSetting.previewClientLogin != Storage.login) {
            PrefsHelper.putInt("Steps", p0!!.values[0].roundToInt())
            GeneralHelper.updateNotification(this, this, 0)
            PrefsHelper.putString("TodayDate", GeneralHelper.getToadyDate())
            StorageSetting.previewClientLogin = Storage.login
        } else {
            val storeSteps = PrefsHelper.getInt("Steps")
            val sensorSteps = p0!!.values[0].roundToInt()
            val finalSteps = sensorSteps - storeSteps
            if (finalSteps > 0) {
                PrefsHelper.putInt("FSteps", finalSteps)
                GeneralHelper.updateNotification(this, this, finalSteps)
                callback?.subscribeSteps(finalSteps)
            }
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d("SERVICE", p0.toString())
    }

    object subscribe {
        fun register(activity: ClientActivity) {
            callback = activity as stepsCallback
        }
    }

}