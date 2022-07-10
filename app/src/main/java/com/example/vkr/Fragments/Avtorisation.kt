package com.example.vkr.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.vkr.Activity.AdminActivity
import com.example.vkr.Activity.ClientActivity
import com.example.vkr.R
import com.example.vkr.user_interface.Model.User
import com.example.vkr.user_interface.NODE_USERS
import com.example.vkr.user_interface.REF_DABATABSE_ROOT
import com.example.vkr.user_interface.Storage
import com.example.vkr.user_interface.StorageSetting
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_avtorisation.*
import kotlinx.android.synthetic.main.fragment_avtorisation.btnRegister
import kotlinx.android.synthetic.main.fragment_registry.*
import kotlinx.android.synthetic.main.fragment_setting.*


private var Array_Users = mutableListOf<User>()

class Avtorisation : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_avtorisation, container, false)
    }

    override fun onStart() {
        super.onStart()


        if(Storage.login.isNotEmpty()) {
            startActivity(Intent(activity, ClientActivity::class.java))
        }


        btnSingIn.setOnClickListener {
            if(editTextLoginA.text.isEmpty() && editTextPasswordA.text.isEmpty()) {
                Toast.makeText(activity, "Введите свой логин и пароль или зарегистрируйтесь", Toast.LENGTH_SHORT).show()
            }
            else {
                doneAvtrorisation()
            }
        }

        btnRegister.setOnClickListener {
            if(StorageSetting.isOnline == true){
            navigationFF(Registry())
            }
            else{
                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }
        }

        zabil.setOnClickListener {
            if(StorageSetting.isOnline == true){
                navigationFF(Zabil())
            }
            else{
                Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
            }

        }

    }

// Навигация фрагмента во фрагмент
    private fun navigationFF(fragment: Fragment)
    {
        activity!!
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainWindowAuthentication, fragment).commitNow()
    }
// конец

    private fun doneAvtrorisation(){
        if(editTextLoginA.text.toString()=="admin" && editTextPasswordA.text.toString()=="admin"){
            startActivity(Intent(activity, AdminActivity::class.java))
        }

        if(editTextLoginA.text.toString()!="admin" && editTextPasswordA.text.toString()!="admin"){
            loadDataBase()
        }
    }

    private fun loadDataBase() {
        if (StorageSetting.isOnline == true) {
            REF_DABATABSE_ROOT.child(NODE_USERS).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "Отсутствует интернет соединение", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var flag = false
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val user = snapshot.getValue(User::class.java) ?: User()
                        Array_Users.add(user)

                        val result = BCrypt.verifyer().verify(editTextPasswordA.text.toString().toCharArray(), user.password)

                        if (editTextLoginA.text.toString() == user.login && result.verified == true)
                        {
                            Storage.name = user.name
                            Storage.height = user.height
                            Storage.weight = user.weight
                            Storage.linkphotouser = user.linkphoto
                            Storage.sex = user.sex
                            Storage.login = editTextLoginA.text.toString()
                            Storage.password = editTextPasswordA.text.toString()
                            StorageSetting.stepMax = user.stepmax

                            editTextLoginA.setText("")
                            editTextPasswordA.setText("")

                            flag = true

                            startActivity(Intent(activity, ClientActivity::class.java))
                            break
                        }

                    }
                    if (flag == false) {
                        Toast.makeText(activity, "Неверный логин или пароль", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })


        }
        else{
            Storage.name = "Пользователь"
            Storage.height = "1"
            Storage.weight = "1"
            Storage.linkphotouser = "000"
            Storage.sex = "men"
            Storage.login = editTextLoginA.text.toString()
            Storage.password = editTextPasswordA.text.toString()
            StorageSetting.stepMax = "8000"


            startActivity(Intent(activity, ClientActivity::class.java))
        }
    }
}