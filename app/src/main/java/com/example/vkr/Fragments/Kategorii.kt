package com.example.vkr.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vkr.R
import com.example.vkr.user_interface.StorageSetting
import kotlinx.android.synthetic.main.fragment_kategorii.*


class Kategorii : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategorii, container, false)
    }

    override fun onStart() {
        super.onStart()
        var flag_front = false
        var flag_back = false
        all_front.setVisibility(View.GONE)
        all_back.setVisibility(View.GONE)

        tren_rastyajka.setOnClickListener {
            StorageSetting.paramentFragment = "Растяжка"
            navigat(LookAllTrensFragment())
        }

        back_kategorii.setOnClickListener {
            navigat(MainClientFragment())
        }

        btn_all_front.setOnClickListener {
            if(!flag_front) {
                Toast.makeText(activity,
                    "Нажмите на необходимую мышечную группу",
                    Toast.LENGTH_SHORT).show()
                all_front.setVisibility(View.VISIBLE)
                flag_front = true
            }

            else{
                all_front.setVisibility(View.GONE)
                flag_front = false
            }
        }

        btn_all_back.setOnClickListener {
            if(!flag_back) {
                Toast.makeText(activity,
                    "Нажмите на необходимую мышечную группу",
                    Toast.LENGTH_SHORT).show()
                all_back.setVisibility(View.VISIBLE)
                flag_back = true
            }

            else{
                all_back.setVisibility(View.GONE)
                flag_back = false
            }
        }

        tren_street.setOnClickListener {

            StorageSetting.paramentFragment = "Улица"
            navigat(LookAllTrensFragment())
        }

        tren_home.setOnClickListener {

            StorageSetting.paramentFragment = "Дома"
            navigat(LookAllTrensFragment())
        }

        tren_kardio.setOnClickListener {

            StorageSetting.paramentFragment = "Кардио"
            navigat(LookAllTrensFragment())
        }

        sheya_front.setOnClickListener {

            StorageSetting.paramentFragment = "sheya_front"
            navigat(LookAllTrensFragment())
        }
        grud_front.setOnClickListener {

            StorageSetting.paramentFragment = "grud_front"
            navigat(LookAllTrensFragment())
        }

        delta_left_back.setOnClickListener {

            StorageSetting.paramentFragment = "delta_front"
            navigat(LookAllTrensFragment())
        }
        delta_right_back.setOnClickListener {

            StorageSetting.paramentFragment = "delta_front"
            navigat(LookAllTrensFragment())
        }
        biceps_left_front.setOnClickListener {

            StorageSetting.paramentFragment = "biceps_front"
            navigat(LookAllTrensFragment())
        }
        biceps_right_front.setOnClickListener {

            StorageSetting.paramentFragment = "biceps_front"
            navigat(LookAllTrensFragment())
        }
        predplecho_left_front.setOnClickListener {

            StorageSetting.paramentFragment = "predplecho_front"
            navigat(LookAllTrensFragment())
        }
        predplecho_right_front.setOnClickListener {

            StorageSetting.paramentFragment = "predplecho_front"
            navigat(LookAllTrensFragment())
        }
        bedro_left_back.setOnClickListener {

            StorageSetting.paramentFragment = "bedro_front"
            navigat(LookAllTrensFragment())
        }
        bedro_right_back.setOnClickListener {

            StorageSetting.paramentFragment = "bedro_front"
            navigat(LookAllTrensFragment())
        }
        trapeciya_back.setOnClickListener {

            StorageSetting.paramentFragment = "trapeciya_back"
            navigat(LookAllTrensFragment())
        }
        spin_back.setOnClickListener {

            StorageSetting.paramentFragment = "spin_back"
            navigat(LookAllTrensFragment())
        }
        delta_left_back.setOnClickListener {

            StorageSetting.paramentFragment = "delta_back"
            navigat(LookAllTrensFragment())
        }
        delta_right_back.setOnClickListener {

            StorageSetting.paramentFragment = "delta_back"
            navigat(LookAllTrensFragment())
        }

        triceps_left_back.setOnClickListener {

            StorageSetting.paramentFragment = "triceps_back"
            navigat(LookAllTrensFragment())
        }
        triceps_right_back.setOnClickListener {

            StorageSetting.paramentFragment = "triceps_back"
            navigat(LookAllTrensFragment())
        }
        ikra_left_back.setOnClickListener {

            StorageSetting.paramentFragment = "ikra_back"
            navigat(LookAllTrensFragment())
        }
        ikra_right_back.setOnClickListener {

            StorageSetting.paramentFragment = "ikra_back"
            navigat(LookAllTrensFragment())
        }
        poyasnica_back.setOnClickListener {

            StorageSetting.paramentFragment = "poyasnica_back"
            navigat(LookAllTrensFragment())
        }
        bedro_left_back.setOnClickListener {

            StorageSetting.paramentFragment = "bedro_back"
            navigat(LookAllTrensFragment())
        }
        bedro_right_back.setOnClickListener {

            StorageSetting.paramentFragment = "bedro_back"
            navigat(LookAllTrensFragment())
        }
        abs_front.setOnClickListener {

            StorageSetting.paramentFragment = "abs_front"
            navigat(LookAllTrensFragment())
        }


    }

    fun navigat(fragment: Fragment){
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .commitNow()
    }
}


