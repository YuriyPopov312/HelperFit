package com.example.vkr.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vkr.Activity.ClientActivity
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_add_trening.*
import kotlinx.android.synthetic.main.fragment_registry.btnCansel


class AddTreningFragment : Fragment() {


    lateinit var title_this:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_trening, container, false)
    }

    override fun onStart() {
        super.onStart()

        btnCansel.setOnClickListener { navigationFF(YourTrensFragment())}

        edit_foto.setOnClickListener {
            title_this = editTitle.text.toString()
            if(editTitle.text.isNotEmpty() && editTip.text.isNotEmpty() && editCountex.text.isNotEmpty() && editTime.text.isNotEmpty() && editOpisanie.text.isNotEmpty() && editTrening.text.isNotEmpty()) {
                changePhotoTren()
            }
            else{
                Toast.makeText(activity,"Заполните все обязательные поля (Все кроме ссылки на видео)",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changePhotoTren() {
        addTreningDB()
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .start((activity as ClientActivity), this)
    }

    private fun addTreningDB (){
        val dateMap= mutableMapOf<String,Any>()//Создаем для пердачи в бд разом

        if(editLink.text.isEmpty()){
            dateMap [CHIELD_LINK] = "-"
        }
        else{
            dateMap [CHIELD_LINK] = editLink.text.toString()
        }
        dateMap [CHIELD_TITLE] = editTitle.text.toString()

        dateMap [CHIELD_TIP] = editTip.text.toString()
        dateMap [CHIELD_TIME] = editTime.text.toString()
        dateMap [CHIELD_COUNTEX] = editCountex.text.toString()
        dateMap [CHIELD_OPISANIE] = editOpisanie.text.toString()
        dateMap [CHIELD_TRENING] = editTrening.text.toString()

        REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).child(title_this).updateChildren(dateMap)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode== Activity.RESULT_OK && data!=null)
        {
            val uri = CropImage.getActivityResult(data).uri
            val path= REF_STORAGE_ROOT.child(FOLDER_TRENING_IMG).child(title_this)
            path.putFile(uri).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    path.downloadUrl.addOnCompleteListener{
                        if(it.isSuccessful){
                            val photoUrl=it.result.toString()

                            REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).child(title_this).child(CHIELD_LINKIMG).setValue(photoUrl).addOnCompleteListener { task2->
                                    if(task2.isSuccessful)
                                    {
                                        navigationFF(YourTrensFragment())
                                    }
                                }

                        }

                    }
                }

            }
        }

    }


}