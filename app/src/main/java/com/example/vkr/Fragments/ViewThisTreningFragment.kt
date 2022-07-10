package com.example.vkr.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_view_this_trening.*


class ViewThisTreningFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_this_trening, container, false)
    }

    override fun onStart() {
        super.onStart()

        if(StorageSetting.flagAllFragment == "0"){
            add_sebe.setVisibility(GONE)
        }

        var flag = false
        vsya_trena.setVisibility(GONE)

        if(StorageTrening.link == "-"){
            cvv.setVisibility(GONE)
            open_opisanie.setVisibility(GONE)
            vsya_trena.setVisibility(VISIBLE)
        }


        val link_video: String = StorageTrening.link

        lifecycle.addObserver(video)
        video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId: String = link_video
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        btn_back_this_one_trens.setOnClickListener{
            if(StorageSetting.flagAllFragment == "0") {
                navigationFF(YourTrensFragment())
            }
            else{
                navigationFF(LookAllTrensFragment())
            }
        }


        open_opisanie.setOnClickListener {
            if (!flag) {
                vsya_trena.setVisibility(VISIBLE)
                flag = true
            } else {
                vsya_trena.setVisibility(GONE)
                flag = false
            }
        }

        StorageTrening.countex
        StorageTrening.link
        StorageTrening.linkimg
        opisanie_this_trening.text = StorageTrening.opisanie
        StorageTrening.tip
        title_this_trening.text = StorageTrening.title
        StorageTrening.timetr
        vsya_trena.text = StorageTrening.trening


        add_sebe.setOnClickListener {
            val dateMap= mutableMapOf<String,Any>()//Создаем для пердачи в бд разом

            dateMap [CHIELD_TITLE] = StorageTrening.title
            dateMap [CHIELD_LINK] = StorageTrening.link
            dateMap [CHIELD_TIP] = StorageTrening.tip
            dateMap [CHIELD_TIME] = StorageTrening.timetr
            dateMap [CHIELD_COUNTEX] = StorageTrening.countex
            dateMap [CHIELD_OPISANIE] = StorageTrening.opisanie
            dateMap [CHIELD_TRENING] = StorageTrening.trening

            REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).child(StorageTrening.title).updateChildren(dateMap)
            REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).child(StorageTrening.title).child(CHIELD_LINKIMG).setValue(Storage.linkphotouser)

            Toast.makeText(activity,
                "Успешно добавлено в Личные тренировки",
                Toast.LENGTH_SHORT).show()
        }

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