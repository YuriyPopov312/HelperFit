package com.example.vkr.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vkr.Activity.c
import com.example.vkr.Activity.d
import com.example.vkr.Activity.s
import com.example.vkr.R
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageSetting
import com.example.vkr.user_interface.downloadSetImage
import kotlinx.android.synthetic.main.fragment_step_counter.*
import replaceFragment


class StepCounterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_counter, container, false)
    }

    override fun onStart() {
        super.onStart()


        if(isOnline(activity!!)){
            StorageSetting.isOnline = true
        }
        else{
            Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            StorageSetting.isOnline = false
        }


        var index_val = Storage.weight.toDouble()/((Storage.height.toDouble()/100)*(Storage.height.toDouble()/100))
        var stepMax = StorageSetting.stepMax.toInt()
        maxStep.text = " / " + stepMax.toString()


        name.text = Storage.name
        height.text = "Рост: " + Storage.height + " см"
        weight.text = "Вес: " + Storage.weight + " кг"
        index.text = "ИМТ: " + index_val
        profile_image.downloadSetImage(Storage.linkphotouser)

            swipe.setOnRefreshListener() {
                if (step.text != s) {
                    replaceFragment(StepCounterFragment())
                    swipe.isRefreshing = false
                }
                else{
                    Toast.makeText(activity, "Синхронизировано", Toast.LENGTH_SHORT).show()
                    swipe.isRefreshing = false
                }
            }

        step.text = s
        cal.text = c
        dist.text = d
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