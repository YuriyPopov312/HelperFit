package com.example.vkr.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vkr.Activity.CalendarActivity
import com.example.vkr.R
import com.example.vkr.user_interface.Model.Statistik
import com.example.vkr.user_interface.StorageSetting
import kotlinx.android.synthetic.main.fragment_main_client.*


class MainClientFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_client, container, false)
    }

    override fun onStart() {
        super.onStart()

        if(StorageSetting.isOnline == false) {
            YourTrening.setBackgroundResource(R.drawable.slytle_for_push_button)
            look_tren.setBackgroundResource(R.drawable.slytle_for_push_button)
            prof_client.setBackgroundResource(R.drawable.slytle_for_push_button)
            treningKategor.setBackgroundResource(R.drawable.slytle_for_push_button)
            recomend.setBackgroundResource(R.drawable.slytle_for_push_button)
            program_trens.setBackgroundResource(R.drawable.slytle_for_push_button)
        }


        val dostig_cl = Dostigeniya()
        val tren = YourTrensFragment()
        val kategorii = Kategorii()
        val stat = StatistikaFragment()
        val progtrens = ProgramTrensFragment()


        program_trens.setOnClickListener {
            if(StorageSetting.isOnline == true) {
                navig(progtrens)
            }
            else{
                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }
        }


        treningKategor.setOnClickListener {
            if(StorageSetting.isOnline == true) {
                navig(kategorii)
            }
            else{
                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }
        }

        prof_client.setOnClickListener {
            if(StorageSetting.isOnline == true) {
                navig(dostig_cl)
            }
            else{
                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }
        }

        Statistika_btn.setOnClickListener {
            navig(stat)
        }

        log_trening.setOnClickListener {
            StorageSetting.flagAllFragment = "2"
            startActivity(Intent(activity, CalendarActivity::class.java))
        }

        look_tren.setOnClickListener {
            if(StorageSetting.isOnline == true) {
                navig(LookAllTrensFragment())
            }
            else{
                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }
        }

        YourTrening.setOnClickListener {
            if(StorageSetting.isOnline == true) {
                navig(tren)
            }
            else{

                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }

        }

        recomend.setOnClickListener {
            if(StorageSetting.isOnline == true) {
                navig(Recomendation())
            }
            else{

                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun navig(fragment: Fragment){
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .commitNow()
    }
}

