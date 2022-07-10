package com.example.vkr.user_interface.recycler_trening

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.vkr.R
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.StorageSetting
import com.example.vkr.user_interface.downloadSetImage

class HolderAllTrens (inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_all_trens, parent, false)) {
        private var mImg: ImageView? = null
        private var mTip: TextView? = null
        private var mTitle: TextView? = null
        private var mOpisanie: TextView?=null
        private var mCountex:TextView?=null
        private var mTimetr:TextView?=null



        init {

            mImg=itemView.findViewById(R.id.img_client_trening_all)
            mTip=itemView.findViewById(R.id.tip_all)
            mTitle=itemView.findViewById(R.id.title_all)
            mOpisanie=itemView.findViewById(R.id.opisanie_all)
            mCountex=itemView.findViewById(R.id.countex_all)
            mTimetr=itemView.findViewById(R.id.timetr_all)


        }

        fun bind(trening: Trening) {

            var tip_tren = ""


            if (trening.tip == "sheya_front")
            {
                tip_tren = "Передние мышцы шеи"
            }
            else if (trening.tip == "grud_front")
            {
                tip_tren = "Грудные мышцы"
            }
            else if (trening.tip == "delta_front")
            {
                tip_tren = "Передний пучок дельтовидных мышц"
            }
            else if (trening.tip == "biceps_front")
            {
                tip_tren = "Бицепсы"
            }
            else if (trening.tip == "predplecho_front")
            {
                tip_tren = "Мышцы предплечья"
            }
            else if (trening.tip == "abs_front")
            {
                tip_tren = "Пресс"
            }
            else if (trening.tip == "bedro_front")
            {
                tip_tren = "Передние мышцы бедра"
            }
            else if (trening.tip == "trapeciya_back")
            {
                tip_tren = "Мышцы трапеции"
            }
            else if (trening.tip == "spin_back")
            {
                tip_tren = "Широчайшие мышцы"
            }
            else if (trening.tip == "delta_back")
            {
                tip_tren = "Задний пучок дельтовидных мышц"
            }
            else if (trening.tip == "triceps_back")
            {
                tip_tren = "Трицепсы"
            }
            else if (trening.tip == "ikra_back")
            {
                tip_tren = "Икраножные мышцы"
            }
            else if (trening.tip == "poyasnica_back")
            {
                tip_tren = "Поясничный корсет"
            }
            else if (trening.tip == "bedro_back")
            {
                tip_tren = "Мышцы обратной стороны бедра"
            }
            else
            {

            }


            if (trening.opisanie.contains("Растяжка",false) ||
                trening.title.contains("Растяжка",false))
            {
                tip_tren += " Растяжка"
            }
            else if (trening.opisanie.contains("дом", false)
                || trening.title.contains("дом",false))
            {
                tip_tren += " Домашняя"
            }
            else if (trening.opisanie.contains("улиц", false)
                || trening.title.contains("улиц",false))
            {
                tip_tren += " Уличная"
            }
            else if (trening.opisanie.contains("кардио", false)
                || trening.title.contains("кардио", false))
            {
                tip_tren += " Кардио"
            }

            else{
                if(tip_tren == "") {
                    tip_tren = "Неопределен"
                }
            }




            mImg?.downloadSetImage(trening.linkimg)
            mTip?.text = "Тип: $tip_tren"
            mTitle?.text = trening.title
            mOpisanie?.text= "Краткое описание: " + trening.opisanie
            mCountex?.text = "Количество упражнений: " + trening.countex
            mTimetr?.text = "Время тренировки: " + trening.timetr



        }

    }

