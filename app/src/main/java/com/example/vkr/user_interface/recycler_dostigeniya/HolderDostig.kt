package com.example.vkr.user_interface.recycler_dostigeniya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R
import com.example.vkr.user_interface.Model.Dostig
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.downloadSetImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import de.hdodenhof.circleimageview.CircleImageView


class HolderDostig (inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_client_dostig, parent, false)) {
        private var mPhoto_user: CircleImageView? = null
        private var mName_user: TextView? = null
        private var mLogin_user: TextView?=null
        private var mTime: TextView?=null
        private var mData: TextView?=null
        private var mDostig_photo_user: ImageView?=null
        private var mVideo_user: YouTubePlayerView?=null
        private var mText_tegs: TextView?=null
        private var mLike: TextView?=null
        private var mDislike: TextView?=null
        private var mOpis_dostig: TextView?=null
        private var mDel_dostig: Button?=null



        init {

            mPhoto_user = itemView.findViewById(R.id.photo_user)
            mName_user = itemView.findViewById(R.id.name_user)
            mLogin_user = itemView.findViewById(R.id.login_user)
            mTime = itemView.findViewById(R.id.time_dostig)
            mData = itemView.findViewById(R.id.data_dostig)
            mDostig_photo_user = itemView.findViewById(R.id.dostig_photo_user)
            mVideo_user = itemView.findViewById(R.id.video_user)
            mText_tegs = itemView.findViewById(R.id.text_tegs)
            mLike = itemView.findViewById(R.id.like)
            mDislike = itemView.findViewById(R.id.dislike)
            mOpis_dostig = itemView.findViewById(R.id.opis_dostig)
            mDel_dostig = itemView.findViewById(R.id.del_dostig)

        }

        fun bind(dostig: Dostig) {

            if(dostig.like_list.contains(Storage.login)) {
                mLike?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like, 0, 0, 0)
            }
            else{
                mLike?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.off_like, 0, 0, 0)
            }

            if(dostig.dislike_list.contains(Storage.login)) {
                mDislike?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dislike, 0, 0, 0)
            }
            else{
                mDislike?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.off_dislike, 0, 0, 0)
            }

            if(Storage.login == dostig.login_cl){
                mDel_dostig?.setVisibility(View.VISIBLE)
            }
            else{
                mDel_dostig?.setVisibility(View.GONE)
            }

            if(dostig.linkvideo == "-"){
                mVideo_user?.setVisibility(View.GONE)
                mDostig_photo_user?.setVisibility(View.VISIBLE)
                mDostig_photo_user?.downloadSetImage(dostig.linkphotodostig)
            }
            else{
                mVideo_user?.setVisibility(View.VISIBLE)
                mDostig_photo_user?.setVisibility(View.GONE)

                mVideo_user?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId: String = dostig.linkvideo
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }

            if(dostig.opisanie_dostig == "-"){
                mOpis_dostig?.setVisibility(View.GONE)
            }
            else{
                mOpis_dostig?.setVisibility(View.VISIBLE)
                mOpis_dostig?.text = dostig.opisanie_dostig
            }

            if(dostig.tegs == "-"){
                mText_tegs?.setVisibility(View.GONE)
            }
            else{
                mText_tegs?.setVisibility(View.VISIBLE)
                mText_tegs?.text = dostig.tegs
            }



            mPhoto_user?.downloadSetImage(dostig.linkphoto_cl)
            mName_user?.text = dostig.name_cl
            mLogin_user?.text = dostig.login_cl
            mTime?.text = dostig.time_load
            mData?.text = dostig.data_load
            mLike?.text = dostig.like
            mDislike?.text = dostig.dislike


        }

    }

