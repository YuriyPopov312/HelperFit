package com.example.vkr.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vkr.Activity.AdminActivity
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_add_trening.*
import kotlinx.android.synthetic.main.fragment_edit_trening.*
import kotlinx.android.synthetic.main.fragment_edit_trening.btnCansel



class EditTreningFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_trening, container, false)
    }


    override fun onStart() {
        super.onStart()
        editTitleE.isEnabled = false
        editTitleE.setText(StorageTrening.title)
        editTipE.setText(StorageTrening.tip)
        editCountexE.setText(StorageTrening.countex)
        editLinkE.setText(StorageTrening.link)
        editTimeE.setText(StorageTrening.timetr)
        editOpisanieE.setText(StorageTrening.opisanie)
        editTreningE.setText(StorageTrening.trening)

        btnEditE.setOnClickListener {
            if(editTitleE.text.isNotEmpty() || editTipE.text.isNotEmpty() || editCountexE.text.isNotEmpty() || editLinkE.text.isNotEmpty() || editTimeE.text.isNotEmpty() || editOpisanieE.text.isNotEmpty() || editTreningE.text.isNotEmpty()){
                addTreningDB()
            }
            else{
                Toast.makeText(activity,"Заполните все значения", Toast.LENGTH_SHORT).show()
            }
        }
        btnCansel.setOnClickListener { navigationFF(YourTrensFragment()) }
    }



    private fun addTreningDB (){
        val dateMap= mutableMapOf<String,Any>()//Создаем для пердачи в бд разом

        dateMap [CHIELD_TITLE] = editTitleE.text.toString()
        dateMap [CHIELD_LINK] = editLinkE.text.toString()
        dateMap [CHIELD_TIP] = editTipE.text.toString()
        dateMap [CHIELD_TIME] = editTimeE.text.toString()
        dateMap [CHIELD_COUNTEX] = editCountexE.text.toString()
        dateMap [CHIELD_OPISANIE] = editOpisanieE.text.toString()
        dateMap [CHIELD_TRENING] = editTreningE.text.toString()


        REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).child(editTitleE.text.toString()).updateChildren(dateMap)

        navigationFF(YourTrensFragment())
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