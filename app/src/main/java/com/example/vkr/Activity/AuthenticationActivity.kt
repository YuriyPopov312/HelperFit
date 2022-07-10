package com.example.vkr.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.vkr.Fragments.Avtorisation
import com.example.vkr.Fragments.StatistikaFragment
import com.example.vkr.R
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageSetting
import com.example.vkr.user_interface.initFirebase
import kotlin.system.exitProcess

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFirebase()
        setContentView(R.layout.activity_authentication)

        shouldShowRequestPermissionRationale()

        navigation(Avtorisation())

        if(isOnline(this)){
            StorageSetting.isOnline = true
        }
        else{
            Toast.makeText(this, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            StorageSetting.isOnline = false
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onBackPressed() {
        // do nothing
    }


// Навигация из актививти во фрагмент
    private fun navigation(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainWindowAuthentication, fragment)
            commit()
        }
    }
// конец


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

    private fun shouldShowRequestPermissionRationale() {
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
        }
        else{
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                44)
        }
    }
}