package com.example.vkr.user_interface.recycler_trening

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.user_interface.ClickProgTrens
import com.example.vkr.user_interface.ClickTrening
import com.example.vkr.user_interface.Model.ProgTrens
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.StorageTrening
import kotlinx.android.synthetic.main.item_client_trening.view.*
import kotlinx.android.synthetic.main.item_prog_trens.view.*

class AdapterProgTrens (private val list: MutableList<ProgTrens>, private val clickProgTrens: ClickProgTrens)
        : RecyclerView.Adapter<HolderProgTrens>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderProgTrens {

            val inflater = LayoutInflater.from(parent.context)
            return HolderProgTrens(inflater, parent)
        }
        override fun onBindViewHolder(holder: HolderProgTrens, position: Int) {
            val prog_trens: ProgTrens = list[position]
            holder.bind(prog_trens)

            StorageTrening.pt_opisanie = prog_trens.opisanie
            StorageTrening.pt_tip = prog_trens.tip
            StorageTrening.pt_title = prog_trens.title


            holder.itemView.this_prog_trens.setOnClickListener{
                val prog_trens: ProgTrens = list[position]
                holder.bind(prog_trens)
                StorageTrening.pt_opisanie = prog_trens.opisanie
                StorageTrening.pt_tip = prog_trens.tip
                StorageTrening.pt_title = prog_trens.title
                clickProgTrens.ViewThisProgTrens()

            }

        }
        override fun getItemCount(): Int = list.size

    }
