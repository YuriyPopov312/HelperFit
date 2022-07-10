package com.example.vkr.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vkr.Activity.ClientActivity
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_add_dostigeniya.*
import kotlinx.android.synthetic.main.fragment_add_dostigeniya.btnCansel
import java.text.SimpleDateFormat
import java.util.*


class AddDostigeniya : Fragment() {

    val sdf_date = SimpleDateFormat("dd-MM-yyyy")
    val sdf_time = SimpleDateFormat("HH:mm")

    val l = Storage.login
    val d = sdf_date.format(Date()).toString()
    val t = sdf_time.format(Date()).toString()
    val title = "$l $d $t"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_dostigeniya, container, false)
    }


    override fun onStart() {
        super.onStart()

        btnCansel.setOnClickListener { navigator(Dostigeniya()) }

        ok.setOnClickListener { changePhotoDostig() }

  }



    private fun changePhotoDostig() {
        addDostigDB()
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .start((activity as ClientActivity), this)
    }

    private fun addDostigDB (){
        val dateMap= mutableMapOf<String,Any>()//Создаем для пердачи в бд разом


        if(link_video_dostig.text.isEmpty()){
            dateMap [CHIELD_LINKVIDEO] = "-"
        }
        else{
            dateMap [CHIELD_LINKVIDEO] = link_video_dostig.text.toString()
        }

        if(opis_dostig.text.isEmpty()){
            dateMap [CHIELD_OPISANIEDOSTIG] = "-"
        }
        else{
            dateMap [CHIELD_OPISANIEDOSTIG] = opis_dostig.text.toString()
        }

        if(tegs.text.isEmpty()){
            dateMap [CHIELD_TEGS] = "-"
        }
        else{
            dateMap [CHIELD_TEGS] = tegs.text.toString()
        }

        dateMap [CHIELD_LOGINCL] = Storage.login
        dateMap [CHIELD_NAMECL] = Storage.name
        dateMap [CHIELD_LINKPHOTOCL] = Storage.linkphotouser
        dateMap [CHIELD_LIKE] = "0"
        dateMap [CHIELD_DISLIKE] = "0"
        dateMap [CHIELD_TIMELOAD] = t.toString()
        dateMap [CHIELD_DATALOAD] = d.toString()
        dateMap [CHIELD_LIKELIST] = " "
        dateMap [CHIELD_DISLIKELIST] = " "



        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(title).updateChildren(dateMap)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode== Activity.RESULT_OK && data!=null)
        {
            val uri = CropImage.getActivityResult(data).uri
            val path= REF_STORAGE_ROOT.child(FOLDER_DOSTIG_IMG).child(title)
            path.putFile(uri).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    path.downloadUrl.addOnCompleteListener{
                        if(it.isSuccessful){
                            val photoUrl=it.result.toString()

                            REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(title).child(CHIELD_LINKPHOTODOSTIG).setValue(photoUrl).addOnCompleteListener { task2->
                                if(task2.isSuccessful)
                                {
                                    navigator(Dostigeniya())
                                }
                            }

                        }

                    }
                }

            }
        }

    }

    fun navigator(fragment: Fragment){
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .commitNow()
    }

}