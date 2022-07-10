package com.example.vkr.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vkr.R
import com.example.vkr.user_interface.StorageTrening
import kotlinx.android.synthetic.main.fragment_this_prog_trens.*


class ThisProgTrensFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_this_prog_trens, container, false)
    }

    override fun onStart() {
        super.onStart()

        btn_back_this_prog_tren.setOnClickListener {
            navigationFF(ProgramTrensFragment())
        }

        var title = StorageTrening.pt_title
        var tip = StorageTrening.pt_tip
        var opisanie = StorageTrening.pt_opisanie

        if(tip == "Начинающий"){
            tip == " новичка"
        }
        else if(tip == "Средний"){
            tip == " уверенного спортсмена"
        }
        else if(tip == "Продвинутый"){
            tip == " продвинутого спортсмена"
        }

        title_prog_tren.text = "Программа тренировок: $title для $tip"
        opisanie_prog_tren.text = opisanie


    }

    // Навигация фрагмента во фрагмент
    private fun navigationFF(fragment: Fragment)
    {
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_wrapper, fragment).commitNow()
    }
    // конец


}