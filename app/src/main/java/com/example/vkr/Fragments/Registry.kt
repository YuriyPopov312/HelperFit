package com.example.vkr.Fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.vkr.Activity.AuthenticationActivity
import com.example.vkr.Activity.ClientActivity
import com.example.vkr.R
import com.example.vkr.user_interface.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_registry.*
import kotlinx.android.synthetic.main.fragment_registry.btnCansel


class Registry : Fragment() {

    var flag: Boolean = false

//    var flagUnique = true
//    var login: String = ""
    //var countUsers = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registry, container, false)
    }


    override fun onStart() {
        super.onStart()



        btnRegister.setOnClickListener {
            if(editTextNameR.text.isNotEmpty() && editTextLoginR.text.isNotEmpty() && editTextPasswordR.text.isNotEmpty() && editTextWeight.text.isNotEmpty() && editTextHeight.text.isNotEmpty()){
                if (editTextHeight.text.length <= 3){
                    editTextHeight.setBackgroundColor(Color.parseColor("#283030"))
                    if (editTextWeight.text.length > 1 && editTextWeight.text.length < 4) {
                        editTextWeight.setBackgroundColor(Color.parseColor("#283030"))
                        if (editTextNameR.text.length >= 2 && editTextNameR.text.length < 10) {
                            editTextNameR.setBackgroundColor(Color.parseColor("#283030"))
                            if (editTextLoginR.text.length >= 5 && editTextLoginR.text.length <= 15 && editTextPasswordR.text.length >= 5 && editTextPasswordR.text.length <= 15) {
                                editTextLoginR.setBackgroundColor(Color.parseColor("#283030"))
                                editTextPasswordR.setBackgroundColor(Color.parseColor("#283030"))
                                var power = powerPass(editTextPasswordR.text.toString())
                                if (power >= 1 ){
                                    editTextPasswordR.setBackgroundColor(Color.parseColor("#283030"))
                                 if(prom.text == "true") {
                                    if(flag == true) {
                                        if(men.isChecked == true || women.isChecked == true) {
                                            //countUsers()
                                            addClientDB()
                                            Toast.makeText(activity,
                                                "Регистрация прошла успешно",
                                                Toast.LENGTH_SHORT).show()
                                        }
                                        else{
                                            Toast.makeText(activity,
                                                "Сначала нужно выбрать пол",
                                                Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    else{
                                        Toast.makeText(activity,
                                            "Сначала нужно выбрать фото",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                 }
                                 else{
                                     Toast.makeText(activity,
                                         "Такой логин уже используется",
                                         Toast.LENGTH_SHORT).show()
                                 }
                                }
                                else{
                                    editTextPasswordR.setBackgroundColor(Color.parseColor("#77FF0000"))
                                    Toast.makeText(activity,"Пароль слишком слабый, пароль должен начинатся с заглавной буквы и иметь минимум 2 спецсимвола (!,&,$)",Toast.LENGTH_LONG).show()
                                }
                            }
                            else{
                                editTextLoginR.setBackgroundColor(Color.parseColor("#77FF0000"))
                                editTextPasswordR.setBackgroundColor(Color.parseColor("#77FF0000"))
                                Toast.makeText(activity,"Длина логина и пароля должны быть в промежутке от 5 до 15",Toast.LENGTH_LONG).show()
                            }
                        }
                        else{
                            editTextNameR.setBackgroundColor(Color.parseColor("#77FF0000"))
                            Toast.makeText(activity,"Длина имени должна быть в промежутке от 2 до 10",Toast.LENGTH_LONG).show()
                        }
                    }
                    else{
                        editTextWeight.setBackgroundColor(Color.parseColor("#77FF0000"))
                        Toast.makeText(activity,"Введите вес в килограммах через точку",Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    editTextHeight.setBackgroundColor(Color.parseColor("#77FF0000"))
                    Toast.makeText(activity,"Введите рост в сантиметрах",Toast.LENGTH_LONG).show()
                }
                }
            else{
                Toast.makeText(activity, "Заполните все значения", Toast.LENGTH_SHORT).show()
            }
        }
        btnCansel.setOnClickListener { navigationFF(Avtorisation()) }

        editTextLoginR.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // действия, когда вводится какой то текст
                // s - то, что вводится, для преобразования в строку - s.toString()
                if (editTextLoginR.text.isNotEmpty()) {
                    checkLogin()
                }
            }
            override fun afterTextChanged(editable: Editable) {
                // действия после того, как что то введено
                // editable - то, что введено. В строку - editable.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // действия перед тем, как что то введено
            }
        })

        edit_foto_r.setOnClickListener {
                if (editTextNameR.text.isNotEmpty() && editTextLoginR.text.isNotEmpty() && editTextPasswordR.text.isNotEmpty() && editTextWeight.text.isNotEmpty() && editTextHeight.text.isNotEmpty() && (men.isChecked == true || women.isChecked == true)) {
                    Toast.makeText(activity, "После выбора фото вы не сможете изменить логин. Если хотите изменить его сейчас нажмите Отмена, или вы сможете изменить свой логин позже в разделе настроек", Toast.LENGTH_LONG).show()

                    changePhotoUser()
                } else {
                    Toast.makeText(activity, "Заполните все значения", Toast.LENGTH_SHORT).show()
                }

            }

        if(flag == true){
            editTextLoginR.isEnabled = false
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

    fun powerPass(password: String):Int {
        var power: Int = 0
        var count = 0
        while (count < password.length) {
           if (password[0]==password[0].toUpperCase()) {
               if(password[count] == '!' || password[count] == '&' || password[count] == '$') {
                   power++
               }
            }
            else{
                break
            }
            count++
        }
        return power
    }


    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .start((activity as AuthenticationActivity), this)
    }



    private fun checkLogin() {
        var unique = ""
            REF_DABATABSE_ROOT.child(NODE_USERS).child(editTextLoginR.text.toString()).addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) { }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(flag == false) {

                            if (p0.exists()) {
                                unique = "false"
                            } else {
                                unique = "true"
                            }

                            prom.text = "$unique"

                            if (prom.text == "false") {
                                editTextLoginR.setBackgroundColor(Color.parseColor("#77FF0000"))
                                Toast.makeText(activity,
                                    "Пользователь с таким логином уже существует",
                                    Toast.LENGTH_SHORT).show()
                            } else {
                                editTextLoginR.setBackgroundColor(Color.parseColor("#283030"))
                            }
                        }
                }
                }
            )

        }





//    private fun countUsers() {
//        var count = 0
//
//        REF_DABATABSE_ROOT.child(NODE_USERS).addValueEventListener(object: ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) { }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if (p0.exists()) {
//                    p0.children.forEach {
//                        count++
//                    }
//
//                }
//                countUsers = count + 1
//                count = 0
//
//            }
//        })
//    }

    private fun addClientDB (){
        val dateMap= mutableMapOf<String, Any>()//Создаем для пердачи в бд разом
        val sex:String

        dateMap[CHIELD_NAME]=editTextNameR.text.toString()
        dateMap[CHIELD_LOGIN]=editTextLoginR.text.toString()
        dateMap[CHIELD_PASSWORD]= BCrypt.withDefaults().hashToString(12, editTextPasswordR.text.toString().toCharArray())
        dateMap[CHIELD_WEIGHT]=editTextWeight.text.toString()
        dateMap[CHIELD_HEIGHT]=editTextHeight.text.toString()
        dateMap[CHIELD_STEPMAX] = "8000"

        Storage.name = editTextNameR.text.toString()
        Storage.height = editTextHeight.text.toString()
        Storage.weight = editTextWeight.text.toString()
        Storage.login = editTextLoginR.text.toString()
        Storage.password = editTextPasswordR.text.toString()
        StorageSetting.stepMax = "8000"

        if (men.isChecked == true){
            sex = "men"
        }
        else{
            sex = "women"
        }
        dateMap[CHIELD_SEX]=sex

        Storage.sex = sex

        REF_DABATABSE_ROOT.child(NODE_USERS).child(editTextLoginR.text.toString()).updateChildren(
            dateMap)

        startActivity(Intent(activity, ClientActivity::class.java))

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode== Activity.RESULT_OK && data!=null)
        {
            flag = true
            val uri = CropImage.getActivityResult(data).uri
            val path= REF_STORAGE_ROOT.child(FOLDER_USER_PHOTO).child(editTextLoginR.text.toString())
            path.putFile(uri).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    path.downloadUrl.addOnCompleteListener{
                        if(it.isSuccessful){
                            val photoUrl=it.result.toString()
                            Storage.linkphotouser = photoUrl


                            REF_DABATABSE_ROOT.child(NODE_USERS).child(editTextLoginR.text.toString()).child(
                                CHIELD_LINKPHOTO).setValue(photoUrl).addOnCompleteListener { task2->
                                if(task2.isSuccessful)
                                {
                                    edit_foto_r.setBackgroundResource(R.drawable.ic_baseline_photo_camera_done)

                                    Toast.makeText(activity, "Ваше фото успешно добавлено", Toast.LENGTH_SHORT).show()
                                }

                            }
                        }
                    }
                }
            }
        }

        //TimeUnit.SECONDS.sleep(3L)
    }
}