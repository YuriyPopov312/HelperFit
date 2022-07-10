package com.example.vkr.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.recycler_trening.AdapterAllTrens
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_all_trens.*
import kotlinx.android.synthetic.main.fragment_avtorisation.*
import kotlinx.android.synthetic.main.fragment_edit_trening.*
import kotlinx.android.synthetic.main.fragment_main_client.*
import kotlinx.android.synthetic.main.fragment_your_trens.*

private val listTreningAll= mutableListOf<Trening>()

class LookAllTrensFragment : Fragment(), ClickAllTrening {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_trens, container, false)
    }

    override fun onStart() {
        super.onStart()
        if(StorageSetting.paramentFragment  == "") {
            search_panel.setVisibility(View.VISIBLE)
        }
        else{
            search_panel.setVisibility(View.GONE)
        }
        StorageSetting.flagAllFragment = ""
        loadDataBase()

        btnSearchAll.setOnClickListener { loadDataBase() }

        btn_back_alltrens.setOnClickListener {
            if(StorageSetting.paramentFragment == "") {
                navigationFF(MainClientFragment())
                StorageSetting.paramentFragment  = ""
            }
            else{
                navigationFF(Kategorii())
                StorageSetting.paramentFragment  = ""
            }
        }


        search_textAll.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // действия, когда вводится какой то текст
                // s - то, что вводится, для преобразования в строку - s.toString()
            }
            override fun afterTextChanged(editable: Editable) {
                // действия после того, как что то введено
                // editable - то, что введено. В строку - editable.toString()
                if(editable.isEmpty()){
                    loadDataBase()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // действия перед тем, как что то введено
            }
        })
    }

    fun create_recycle() {

        if (activity != null)
            activity!!.rv_all_tren.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = AdapterAllTrens(listTreningAll,this@LookAllTrensFragment)
            }
    }

    private fun loadDataBase() {
        listTreningAll.removeAll { true }
        REF_DABATABSE_ROOT.child(NODE_TRENINGS).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Нет подключения к базе", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {

                        if(StorageSetting.paramentFragment  == "") {

                            for (snapshot: DataSnapshot in dataSnapshot.children) {
                                val trenings = snapshot.getValue(Trening::class.java) ?: Trening()

                                if (search_textAll.text.isEmpty()) {
                                    listTreningAll.add(trenings)
                                }
                                else if (search_textAll.text.isNotEmpty() && trenings.title.contains(
                                        search_textAll.text.toString()))
                                {
                                    listTreningAll.add(trenings)
                                }
                                else
                                {
                                    Toast.makeText(activity,
                                        "Ничего не найдено",
                                        Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            create_recycle()
                        }
                        else{
                            for (snapshot: DataSnapshot in dataSnapshot.children) {
                                val trenings = snapshot.getValue(Trening::class.java) ?: Trening()

                                if (StorageSetting.paramentFragment == "Растяжка" &&
                                    (trenings.opisanie.contains(StorageSetting.paramentFragment,true) ||
                                            trenings.title.contains(StorageSetting.paramentFragment,true)))
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "Дома" &&
                                    (trenings.opisanie.contains("дом", true)
                                            || trenings.title.contains(StorageSetting.paramentFragment,true)))
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "Улица" &&
                                    (trenings.opisanie.contains("улиц", true)
                                            || trenings.title.contains(StorageSetting.paramentFragment,true)))
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "Кардио" &&
                                    (trenings.opisanie.contains("кардио", true)
                                            || trenings.title.contains(StorageSetting.paramentFragment, true)))
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "sheya_front" && trenings.tip == "sheya_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "grud_front" && trenings.tip == "grud_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "delta_front" && trenings.tip == "delta_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "biceps_front" && trenings.tip == "biceps_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "predplecho_front" && trenings.tip == "predplecho_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "abs_front" && trenings.tip == "abs_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "bedro_front" && trenings.tip == "bedro_front")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "trapeciya_back" && trenings.tip == "trapeciya_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "spin_back" && trenings.tip == "spin_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "delta_back" && trenings.tip == "delta_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "triceps_back" && trenings.tip == "triceps_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "ikra_back" && trenings.tip == "ikra_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "poyasnica_back" && trenings.tip == "poyasnica_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else if (StorageSetting.paramentFragment == "bedro_back" && trenings.tip == "bedro_back")
                                {
                                    listTreningAll.add(trenings)
                                }
                                else
                                {

                                }
                            }
                            if(listTreningAll.isEmpty()){
                                Toast.makeText(activity, "Пусто", Toast.LENGTH_SHORT).show()
                            }
                            create_recycle()
                        }
                }
                else{
                    Toast.makeText(activity, "Пусто", Toast.LENGTH_SHORT).show()
                    create_recycle()
                }
            }
        })
    }

    override fun ViewTreningAll() {
        navigationFF( ViewThisTreningFragment())

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