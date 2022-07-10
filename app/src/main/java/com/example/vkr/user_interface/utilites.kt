package com.example.vkr.user_interface

import android.widget.ImageView
import com.example.vkr.R
import com.squareup.picasso.Picasso



fun ImageView.downloadSetImage(url: String)
{
    if(url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.person_login) ////// запись в картинку
            .into(this)
    }

}