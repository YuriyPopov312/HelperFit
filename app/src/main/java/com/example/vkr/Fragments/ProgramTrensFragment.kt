package com.example.vkr.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.example.vkr.user_interface.Model.ProgTrens
import com.example.vkr.user_interface.Model.Trening
import com.example.vkr.user_interface.recycler_trening.AdapterAllTrens
import com.example.vkr.user_interface.recycler_trening.AdapterProgTrens
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_all_trens.*
import kotlinx.android.synthetic.main.fragment_program_trens.*

private val listProgTres= mutableListOf<ProgTrens>()

class ProgramTrensFragment : Fragment(), ClickProgTrens {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_program_trens, container, false)
    }

    override fun onStart() {
        super.onStart()

        btn_back_progtrens.setOnClickListener {
            navigationFF(MainClientFragment())
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                loadDataBase()
                Toast.makeText(activity, spinner.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
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

    fun create_recycle() {

        if (activity != null)
            activity!!.rv_progtrens.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = AdapterProgTrens(listProgTres,this@ProgramTrensFragment)
            }
    }

    private fun loadDataBase() {
        listProgTres.removeAll { true }
        REF_DABATABSE_ROOT.child(NODE_PROGTRENS).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Нет подключения к базе", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            val prog_trens = snapshot.getValue(ProgTrens::class.java) ?: ProgTrens()

                            if (spinner.selectedItem.toString() == "Любой") {
                                listProgTres.add(prog_trens)
                            }
                            else if (spinner.selectedItem.toString() == "Начинающий" && prog_trens.tip == "Начинающий")
                            {
                                listProgTres.add(prog_trens)
                            }
                            else if (spinner.selectedItem.toString() == "Средний" && prog_trens.tip == "Средний")
                            {
                                listProgTres.add(prog_trens)
                            }
                            else if (spinner.selectedItem.toString() == "Продвинутый" && prog_trens.tip == "Продвинутый")
                            {
                                listProgTres.add(prog_trens)
                            }
                            else
                            {
                                Toast.makeText(activity,
                                    "Ничего не найдено",
                                    Toast.LENGTH_SHORT)
                                    .show()
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

    override fun ViewThisProgTrens() {
        navigationFF(ThisProgTrensFragment())
    }

}