package com.example.vkr.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vkr.R
import com.example.vkr.user_interface.Model.User
import com.example.vkr.user_interface.NODE_USERS
import com.example.vkr.user_interface.REF_DABATABSE_ROOT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_zabil.*

class Zabil : Fragment() {


    private var Users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zabil, container, false)
    }

    override fun onStart() {
        super.onStart()
        vostPass.isEnabled = false


        btnBackZabil.setOnClickListener{navigationFF(Avtorisation())}

        btnVostPass.setOnClickListener{
            if(editTextNameZ.text.isNotEmpty() && editTextLoginZ.text.isNotEmpty() && editTextWeightZ.text.isNotEmpty() && editTextHeightZ.text.isNotEmpty()){
                vosstanovit()
                vostPass.isEnabled = true
            }
            else{
                Toast.makeText(activity, "Заполните все значения", Toast.LENGTH_SHORT).show()
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

    private fun vosstanovit() {
        var pass = "000"
        REF_DABATABSE_ROOT.child(NODE_USERS).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Отсутствует интернет соединение", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot: DataSnapshot in dataSnapshot.children) {
                    val user = snapshot.getValue(User::class.java) ?: User()
                    Users.add(user)

                    if (editTextLoginZ.text.toString() == user.login && editTextNameZ.text.toString() == user.name && editTextWeightZ.text.toString() == user.weight && editTextHeightZ.text.toString() == user.height) {
                        pass = user.password
                        vostPass.setText("Ваш пароль: $pass")
                        editTextLoginZ.isEnabled = false
                        editTextNameZ.isEnabled = false
                        editTextHeightZ.isEnabled = false
                        editTextWeightZ.isEnabled = false
                        break
                    }
                }
                if(pass == "000") {
                    vostPass.setText("Извините, вы ввели неверные данные для восстановления пароля. Пожалуйста, попробуйте еще раз.")

                }
            }
        })


    }



}