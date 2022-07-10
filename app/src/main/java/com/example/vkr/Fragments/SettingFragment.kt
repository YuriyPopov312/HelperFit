package com.example.vkr.Fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.vkr.Activity.ClientActivity
import com.example.vkr.R
import com.example.vkr.helper.GeneralHelper
import com.example.vkr.service.StepDetectorService
import com.example.vkr.user_interface.*
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_registry.*
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }


    override fun onStart() {
        super.onStart()



            editTextNameE.setText(Storage.name)
            editTextHeightE.setText(Storage.height)
            editTextWeightE.setText(Storage.weight)
            editTextPasswordE.setText(Storage.password)

            var flag = false
            edit_forma.setVisibility(View.GONE)


            var flag1 = false
            forma_stepmax.setVisibility(View.GONE)

        if (StorageSetting.isOnline == true) {

            edit_info.setOnClickListener {
                if (!flag) {
                    edit_forma.setVisibility(View.VISIBLE)
                    flag = true
                } else {
                    edit_forma.setVisibility(View.GONE)
                    flag = false
                }
            }


            edit_stepmax.setOnClickListener {
                if (!flag1) {
                    forma_stepmax.setVisibility(View.VISIBLE)
                    flag1 = true
                } else {
                    forma_stepmax.setVisibility(View.GONE)
                    flag1 = false
                }
            }

            edit_info_ok.setOnClickListener {
                if (editTextNameE.text.isNotEmpty() && editTextPasswordE.text.isNotEmpty() && editTextWeightE.text.isNotEmpty() && editTextHeightE.text.isNotEmpty()) {
                    if (editTextHeightE.text.length <= 3) {
                        editTextHeightE.setBackgroundColor(Color.parseColor("#283030"))
                        if (editTextWeightE.text.length > 1 && editTextWeightE.text.length < 4) {
                            editTextWeightE.setBackgroundColor(Color.parseColor("#283030"))
                            if (editTextNameE.text.length >= 2 && editTextNameE.text.length < 10) {
                                editTextNameE.setBackgroundColor(Color.parseColor("#283030"))
                                if (editTextPasswordE.text.length >= 5 && editTextPasswordE.text.length <= 15) {
                                    editTextPasswordE.setBackgroundColor(Color.parseColor("#283030"))
                                    editInfo()
                                } else {

                                    editTextPasswordE.setBackgroundColor(Color.parseColor("#77FF0000"))
                                    Toast.makeText(activity,
                                        "Длина пароля должна быть в промежутке от 5 до 15",
                                        Toast.LENGTH_LONG).show()
                                }
                            } else {
                                editTextNameE.setBackgroundColor(Color.parseColor("#77FF0000"))
                                Toast.makeText(activity,
                                    "Длина имени должна быть в промежутке от 2 до 10",
                                    Toast.LENGTH_LONG).show()
                            }
                        } else {
                            editTextWeightE.setBackgroundColor(Color.parseColor("#77FF0000"))
                            Toast.makeText(activity,
                                "Введите вес в килограммах через точку",
                                Toast.LENGTH_LONG).show()
                        }
                    } else {
                        editTextHeightE.setBackgroundColor(Color.parseColor("#77FF0000"))
                        Toast.makeText(activity, "Введите рост в сантиметрах", Toast.LENGTH_LONG)
                            .show()
                    }
                } else {
                    Toast.makeText(activity, "Заполните все значения", Toast.LENGTH_SHORT).show()
                }

            }

            setting_ok.setOnClickListener {

                if (step_max.text.toString() == "") {
                    Toast.makeText(activity, "Введите целочисленное значение", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    StorageSetting.stepMax = step_max.text.toString().toInt().toString()
                    REF_DABATABSE_ROOT.child(NODE_USERS).child(Storage.login)
                        .child("stepmax").setValue(step_max.text.toString().toInt().toString())
                    step_max.setText("")
                    forma_stepmax.setVisibility(View.GONE)
                    Toast.makeText(activity, "Настройки применены", Toast.LENGTH_SHORT).show()
                }
            }



            edit_photo.setOnClickListener { changePhoto() }

        }
        else{

            edit_photo.setBackgroundResource(R.drawable.slytle_for_push_button)
            edit_stepmax.setBackgroundResource(R.drawable.slytle_for_push_button)
            edit_info.setBackgroundResource(R.drawable.slytle_for_push_button)

            Toast.makeText(activity, "Нет подключения к интеренету", Toast.LENGTH_SHORT).show()
        }

        exit_app.setOnClickListener {
            StorageSetting.previewClientLogin = Storage.login
            Storage.name = ""
            Storage.height = ""
            Storage.weight = ""
            Storage.linkphotouser = ""
            Storage.sex = ""
            Storage.login = ""
            Storage.password = ""

            activity!!.finish()

        }
    }

        private fun editInfo() {
            val dateMap = mutableMapOf<String, Any>()//Создаем для пердачи в бд разом

            dateMap[CHIELD_NAME] = editTextNameE.text.toString()
            dateMap[CHIELD_PASSWORD] = BCrypt.withDefaults().hashToString(12, editTextPasswordE.text.toString().toCharArray());
            dateMap[CHIELD_WEIGHT] = editTextWeightE.text.toString()
            dateMap[CHIELD_HEIGHT] = editTextHeightE.text.toString()

            Storage.name = editTextNameE.text.toString()
            Storage.password = editTextPasswordE.text.toString()
            Storage.weight = editTextWeightE.text.toString()
            Storage.height = editTextHeightE.text.toString()


            REF_DABATABSE_ROOT.child(NODE_USERS).child(Storage.login).updateChildren(dateMap)
            Toast.makeText(activity, "Ваши данные успешно изменены", Toast.LENGTH_SHORT).show()
            edit_forma.setVisibility(View.GONE)

        }

        private fun changePhoto() {

            CropImage.activity()
                .setAspectRatio(1, 1)
                .setRequestedSize(600, 600)
                .start((activity as ClientActivity), this)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

                val uri = CropImage.getActivityResult(data).uri
                val path = REF_STORAGE_ROOT.child(FOLDER_USER_PHOTO).child(Storage.login)
                path.putFile(uri).addOnCompleteListener {
                    if (it.isSuccessful) {
                        path.downloadUrl.addOnCompleteListener {
                            if (it.isSuccessful) {
                                val photoUrl = it.result.toString()
                                Storage.linkphotouser = photoUrl

                                REF_DABATABSE_ROOT.child(NODE_USERS).child(Storage.login).child(
                                    CHIELD_LINKPHOTO).setValue(photoUrl)
                                    .addOnCompleteListener { task2 ->
                                        if (task2.isSuccessful) {


                                            Toast.makeText(activity,
                                                "Ваше фото успешно изменено",
                                                Toast.LENGTH_SHORT).show()
                                        }

                                    }
                            }
                        }
                    }
                }
            }

        }


}