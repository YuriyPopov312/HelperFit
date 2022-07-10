package com.example.vkr.Activity

import android.Manifest.permission.ACTIVITY_RECOGNITION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.vkr.Fragments.MainClientFragment
import com.example.vkr.Fragments.SettingFragment
import com.example.vkr.Fragments.StepCounterFragment
import com.example.vkr.R
import com.example.vkr.callback.stepsCallback
import com.example.vkr.helper.GeneralHelper
import com.example.vkr.helper.PrefsHelper
import com.example.vkr.service.StepDetectorService
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageSetting
import kotlinx.android.synthetic.main.activity_client.*
import kotlinx.android.synthetic.main.fragment_avtorisation.*
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_step_counter.*


    var s = ""
    var c = ""
    var d = ""

class ClientActivity : AppCompatActivity(), stepsCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)

        checkActivityRecognitionPermission(1)

        val homeFragment = StepCounterFragment()
        val runFragment = MainClientFragment()
        val settingsFragment = SettingFragment()



        makeCurrentFragment(homeFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
           when(it.itemId){
               R.id.ic_home -> makeCurrentFragment(homeFragment)
               R.id.ic_run -> makeCurrentFragment(runFragment)
               R.id.ic_settings -> makeCurrentFragment(settingsFragment)
           }
            true
        }

    }

    override fun onStart() {
        super.onStart()
        if(isOnline(this)){
            StorageSetting.isOnline = true
        }
        else{
            StorageSetting.isOnline = false
        }

    }

    override fun onBackPressed() {
        // do nothing
    }

    private fun makeCurrentFragment(fragment: Fragment){
        StorageSetting.paramentFragment  = ""
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }


    fun checkActivityRecognitionPermission(requestCode: Int) {
        var isActivityRecognitionAuthorize = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isActivityRecognitionAuthorize = ContextCompat.checkSelfPermission(this,
                ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED
        }
        if (!isActivityRecognitionAuthorize && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    ACTIVITY_RECOGNITION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(ACTIVITY_RECOGNITION),
                    requestCode)
            }
        }
    }


    override fun subscribeSteps(steps: Int) {
        var stepMax = StorageSetting.stepMax.toInt();

        if (PrefsHelper.getString("TodayDate") != GeneralHelper.getToadyDate() || StorageSetting.previewClientLogin != Storage.login) {
            s = "0"
            c = "0 калорий"
            d = "0 метров"

        }
        else{
        s = steps.toString()
        c = GeneralHelper.getCalories(steps).toString()
        d = GeneralHelper.getDistanceCovered(steps).toString()}

        this.step.text = s
        this.cal.text = c
        this.dist.text = d
    }

    fun isOnline(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivityManager != null){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null){
                if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Interent", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }else if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    Log.i("Interent", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }else if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    Log.i("Interent", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


}
