package com.example.vkr.user_interface.recycler_trening

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.user_interface.ClickTrening
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.StorageTrening
import kotlinx.android.synthetic.main.item_client_trening.view.*

class AdapterTreningAdmin (private val list: MutableList<Trening>, private val clickTrening: ClickTrening)
        : RecyclerView.Adapter<HolderTreningAdmin>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTreningAdmin {

            val inflater = LayoutInflater.from(parent.context)
            return HolderTreningAdmin(inflater, parent)
        }
        override fun onBindViewHolder(holder: HolderTreningAdmin, position: Int) {
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

            holder.itemView.btn_editTrening.setOnClickListener{

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

                clickTrening.EditThisTrening()

            }

            holder.itemView.btn_delTrening.setOnClickListener{
                StorageTrening.title = trening.title
                clickTrening.DeleteThisTrening()

            }

            holder.itemView.this_trening.setOnClickListener{
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
                clickTrening.ViewThisTrening()

            }



        }
        override fun getItemCount(): Int = list.size

    }
