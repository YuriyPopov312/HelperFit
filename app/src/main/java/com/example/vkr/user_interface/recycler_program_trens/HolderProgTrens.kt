package com.example.vkr.user_interface.recycler_trening

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R
import com.example.vkr.user_interface.Model.ProgTrens
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.downloadSetImage
import kotlinx.android.synthetic.main.item_prog_trens.view.*

class HolderProgTrens (inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_prog_trens, parent, false)) {
        private var mTip: TextView? = null
        private var mTitle: TextView? = null

        init {
            mTip=itemView.findViewById(R.id.tip_pt)
            mTitle=itemView.findViewById(R.id.title_pt)
        }

        fun bind(prog_trens: ProgTrens) {
            mTip?.text = "Уровень: " + prog_trens.tip
            mTitle?.text = prog_trens.title
        }
    }

