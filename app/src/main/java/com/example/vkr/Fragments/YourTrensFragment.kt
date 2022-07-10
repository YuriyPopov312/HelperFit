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
import com.example.vkr.user_interface.recycler_trening.AdapterTreningAdmin
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_avtorisation.*
import kotlinx.android.synthetic.main.fragment_edit_trening.*
import kotlinx.android.synthetic.main.fragment_main_client.*
import kotlinx.android.synthetic.main.fragment_registry.*
import kotlinx.android.synthetic.main.fragment_your_trens.*
import kotlinx.android.synthetic.main.item_client_trening.*


private val listTrening= mutableListOf<Trening>()

class YourTrensFragment : Fragment(), ClickTrening {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_trens, container, false)
    }

    override fun onStart() {
        super.onStart()
        loadDataBase()

        btnSearch.setOnClickListener { loadDataBase() }

        btn_back_this_yout_trens.setOnClickListener { navigationFF(MainClientFragment()) }


        btn_addtrening.setOnClickListener { navigationFF(AddTreningFragment()) }

        search_text.addTextChangedListener(object : TextWatcher {
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
            activity!!.rv_your_tren.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = AdapterTreningAdmin(listTrening, this@YourTrensFragment)
            }
    }

    private fun loadDataBase() {
        listTrening.removeAll { true }
        REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Нет подключения к базе", Toast.LENGTH_SHORT).show()
            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val trenings = snapshot.getValue(Trening::class.java) ?: Trening()

                            if(search_text.text.isEmpty()){
                                listTrening.add(trenings)
                                create_recycle()
                            }
                            else if (search_text.text.isNotEmpty() && trenings.title.contains(search_text.text.toString(),true)) {
                                listTrening.add(trenings)
                                create_recycle()
                            }
                            else{
                                Toast.makeText(activity, "Ничего не найдено", Toast.LENGTH_SHORT).show()
                                create_recycle()
                            }
                        }
                    }
                else{
                    Toast.makeText(activity, "Вы не добавили еще ниодной тренировки", Toast.LENGTH_SHORT).show()
                    create_recycle()
                }
            }
        })
    }


    override fun ViewThisTrening() {
        StorageSetting.flagAllFragment = "0"
        navigationFF( ViewThisTreningFragment())
    }

    override fun EditThisTrening() {
        navigationFF( EditTreningFragment())
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

    override fun DeleteThisTrening() {
        REF_DABATABSE_ROOT.child(NODE_TRENINGS + " - " + Storage.login).child(StorageTrening.title).removeValue()
        loadDataBase()
        Toast.makeText(activity, "Удалено", Toast.LENGTH_SHORT).show()
    }

}