package com.example.vkr.Fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vkr.R
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageRecomendation
import kotlinx.android.synthetic.main.fragment_recomendation.*
import kotlinx.android.synthetic.main.fragment_registry.*


class Recomendation : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recomendation, container, false)
    }

    override fun onStart() {
        super.onStart()

        val index_val = Storage.weight.toDouble()/((Storage.height.toDouble()/100)*(Storage.height.toDouble()/100))


        btnCanselRecomend.setOnClickListener { navigat(MainClientFragment()) }

        index.text = index_val.toString()

        if( index_val < 18.5 ){
            recomendation.text = StorageRecomendation.nizkiy_imt
            index_form.setBackgroundColor(Color.parseColor("#FFA5CC1A"))
            recomendation.setTextColor(Color.parseColor("#FFA5CC1A"))
        }
        else if( index_val >= 18.5 && index_val <= 24.9 ){
            recomendation.text = StorageRecomendation.obichniy_imt
            index_form.setBackgroundColor(Color.parseColor("#FF43B61A"))
            recomendation.setTextColor(Color.parseColor("#FF43B61A"))
        }
        else if( index_val >= 25 && index_val <= 29.9 ){
            recomendation.text = StorageRecomendation.povisheniy_imt
            index_form.setBackgroundColor(Color.parseColor("#FFCC9A1A"))
            recomendation.setTextColor(Color.parseColor("#FFCC9A1A"))
        }
        else if( index_val >= 30 && index_val <= 34.9 ){
            recomendation.text = StorageRecomendation.visokiy_imt
            index_form.setBackgroundColor(Color.parseColor("#FFCC6D1A"))
            recomendation.setTextColor(Color.parseColor("#FFCC6D1A"))
        }
        else if( index_val >= 35 && index_val <= 39.9 ){
            recomendation.text = StorageRecomendation.very_visokiy_imt
            index_form.setBackgroundColor(Color.parseColor("#FFCC3E1A"))
            recomendation.setTextColor(Color.parseColor("#FFCC3E1A"))
        }
        else if(index_val >= 40){
            recomendation.text = StorageRecomendation.very_very_visokiy_imt
            index_form.setBackgroundColor(Color.parseColor("#FFC50000"))
            recomendation.setTextColor(Color.parseColor("#FFC50000"))

        }
        else{
            recomendation.text = "Что-то пошло не так"
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