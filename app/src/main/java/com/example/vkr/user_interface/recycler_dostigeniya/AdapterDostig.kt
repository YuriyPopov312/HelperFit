package com.example.vkr.user_interface.recycler_dostigeniya

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.user_interface.*
import com.example.vkr.user_interface.Model.Dostig
import kotlinx.android.synthetic.main.item_client_dostig.view.*


class AdapterDostig(private val list: MutableList<Dostig>, private val clickDostig: ClickDostig)
        : RecyclerView.Adapter<HolderDostig>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderDostig {

            val inflater = LayoutInflater.from(parent.context)
            return HolderDostig(inflater, parent)
        }
        override fun onBindViewHolder(holderDostig: HolderDostig, position: Int) {

            val dostigeniya: Dostig = list[position]
            holderDostig.bind(dostigeniya)

            holderDostig.itemView.like.setOnClickListener{
                val dostigeniya: Dostig = list[position]
                holderDostig.bind(dostigeniya)
                val l = dostigeniya.login_cl
                val d = dostigeniya.data_load
                val t = dostigeniya.time_load
                StorageDostig.title_dostig = "$l $d $t"

                if(dostigeniya.like_list.contains(Storage.login)){
                    StorageDostig.like = (dostigeniya.like.toInt() - 1).toString()
                    StorageDostig.like_list = (dostigeniya.like_list).replace(Storage.login,"")
                    }
                else{
                    StorageDostig.like = (dostigeniya.like.toInt() + 1).toString()
                    StorageDostig.like_list = dostigeniya.like_list + Storage.login

                    if(dostigeniya.dislike_list.contains(Storage.login)){
                        StorageDostig.dislike = (dostigeniya.dislike.toInt() - 1).toString()
                        StorageDostig.dislike_list = (dostigeniya.dislike_list).replace(Storage.login,"")
                    }
                    else{
                        StorageDostig.dislike = dostigeniya.dislike
                        StorageDostig.dislike_list = dostigeniya.dislike_list
                    }
                }
                clickDostig.Like()
            }

            holderDostig.itemView.dislike.setOnClickListener{
                val dostigeniya: Dostig = list[position]
                holderDostig.bind(dostigeniya)
                val l = dostigeniya.login_cl
                val d = dostigeniya.data_load
                val t = dostigeniya.time_load
                StorageDostig.title_dostig = "$l $d $t"

                if(dostigeniya.dislike_list.contains(Storage.login)){
                    StorageDostig.dislike = (dostigeniya.dislike.toInt() - 1).toString()
                    StorageDostig.dislike_list = (dostigeniya.dislike_list).replace(Storage.login,"")
                }
                else{
                    StorageDostig.dislike = (dostigeniya.dislike.toInt() + 1).toString()
                    StorageDostig.dislike_list = dostigeniya.dislike_list + Storage.login

                    if(dostigeniya.like_list.contains(Storage.login)){
                        StorageDostig.like = (dostigeniya.like.toInt() - 1).toString()
                        StorageDostig.like_list = (dostigeniya.like_list).replace(Storage.login,"")
                    }
                    else{
                        StorageDostig.like = dostigeniya.like
                        StorageDostig.like_list = dostigeniya.like_list
                    }
                }
                clickDostig.Dislike()
            }

            holderDostig.itemView.del_dostig.setOnClickListener{
                val dostigeniya: Dostig = list[position]
                holderDostig.bind(dostigeniya)
                val l = dostigeniya.login_cl
                val d = dostigeniya.data_load
                val t = dostigeniya.time_load
                val title = "$l $d $t"
                StorageDostig.title_dostig = title
                StorageDostig.like = dostigeniya.dislike
                StorageDostig.dislike = dostigeniya.dislike
                
                clickDostig.DelDostig()
            }




        }
        override fun getItemCount(): Int = list.size

    }
