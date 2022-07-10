package com.example.vkr.user_interface.recycler_trening

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.user_interface.ClickAllTrening
import com.example.vkr.user_interface.ClickTrening
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageTrening
import kotlinx.android.synthetic.main.item_all_trens.view.*
import kotlinx.android.synthetic.main.item_client_trening.view.*
import kotlinx.android.synthetic.main.item_client_trening.view.this_trening

class AdapterAllTrens (private val list: MutableList<Trening>, private val clickAllTrening: ClickAllTrening)
        : RecyclerView.Adapter<HolderAllTrens>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAllTrens {

            val inflater = LayoutInflater.from(parent.context)
            return HolderAllTrens(inflater, parent)
        }
        override fun onBindViewHolder(holder: HolderAllTrens, position: Int) {

            val trening: Trening = list[position]
            holder.bind(trening)
            StorageTrening.countex = trening.countex
            StorageTrening.link = trening.link
            StorageTrening.linkimg = trening.linkimg
            StorageTrening.opisanie = trening.opisanie
            StorageTrening.tip = trening.tip
            StorageTrening.title = trening.title
            StorageTrening.timetr = trening.timetr
            StorageTrening.trening = trening.trening



            holder.itemView.this_trening_all.setOnClickListener{
                val trening: Trening = list[position]
                holder.bind(trening)
                StorageTrening.countex = trening.countex
                StorageTrening.link = trening.link
                StorageTrening.linkimg = trening.linkimg
                StorageTrening.opisanie = trening.opisanie
                StorageTrening.tip = trening.tip
                StorageTrening.title = trening.title
                StorageTrening.timetr = trening.timetr
                StorageTrening.trening = trening.trening
                clickAllTrening.ViewTreningAll()

            }



        }
        override fun getItemCount(): Int = list.size

    }
