package com.example.vkr.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.example.vkr.user_interface.Model.Dostig
import com.example.vkr.user_interface.recycler_dostigeniya.AdapterDostig
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_dostigeniya.*
import kotlinx.android.synthetic.main.item_client_dostig.*
import java.text.SimpleDateFormat
import java.util.*


private val listDostig = mutableListOf<Dostig>()



class Dostigeniya : Fragment(), ClickDostig {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dostigeniya, container, false)
    }

    override fun onStart() {
        super.onStart()

        btn_add_dostig.setOnClickListener{navigator(AddDostigeniya())}
        btn_back_dostig.setOnClickListener{navigator(MainClientFragment())}


        loadDataBase()

        btnSearchTeg.setOnClickListener { loadDataBase() }



        search_tegs.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // действия, когда вводится какой то текст
                // s - то, что вводится, для преобразования в строку - s.toString()

            }

            override fun afterTextChanged(editable: Editable) {
                // действия после того, как что то введено
                // editable - то, что введено. В строку - editable.toString()
                if (editable.isEmpty()) {
                    loadDataBase()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // действия перед тем, как что то введено
            }
        })

    }


    fun create_recycle() {

        val format1 = SimpleDateFormat("")
        val format2 = SimpleDateFormat("yyyy-MM-dd HH:mm")

        listDostig.sortBy {  format2.parse(it.data_load + " " + it.time_load) }
        listDostig.reverse()

        if (activity != null)
            activity!!.rv_dostig.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = AdapterDostig(listDostig, this@Dostigeniya)
            }
    }

    private fun loadDataBase() {
        listDostig.removeAll { true }
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Нет подключения к базе", Toast.LENGTH_SHORT).show()
            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val dostigs = snapshot.getValue(Dostig::class.java) ?: Dostig()

                        if (search_tegs.text.isEmpty()) {
                            listDostig.add(dostigs)
                            create_recycle()
                        } else if (search_tegs.text.isNotEmpty() && dostigs.tegs.contains(
                                search_tegs.text.toString(),
                                true)
                        ) {
                            listDostig.add(dostigs)
                            create_recycle()
                        } else {
                            Toast.makeText(activity, "Ничего не найдено", Toast.LENGTH_SHORT).show()
                            create_recycle()
                        }
                    }
                } else {
                    Toast.makeText(activity, "Пусто", Toast.LENGTH_SHORT).show()
                    create_recycle()
                }
            }
        })
    }



    fun navigator(fragment: Fragment){
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .commitNow()
    }

    override fun Like(){
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("like_list").setValue(StorageDostig.like_list)
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("like").setValue(StorageDostig.like)
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("dislike_list").setValue(StorageDostig.dislike_list)
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("dislike").setValue(StorageDostig.dislike)
        loadDataBase()
    }

    override fun Dislike(){
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("like_list").setValue(StorageDostig.like_list)
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("like").setValue(StorageDostig.like)
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("dislike_list").setValue(StorageDostig.dislike_list)
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig)
            .child("dislike").setValue(StorageDostig.dislike)
        loadDataBase()
    }

    override fun DelDostig() {
        REF_DABATABSE_ROOT.child(NODE_DOSTIGENIYA).child(StorageDostig.title_dostig).removeValue()
        loadDataBase()
        Toast.makeText(activity, "Удалено", Toast.LENGTH_SHORT).show()
    }

}