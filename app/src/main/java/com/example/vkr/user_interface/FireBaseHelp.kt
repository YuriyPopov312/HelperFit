package com.example.vkr.user_interface

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var REF_DABATABSE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference

const val NODE_USERS = "users"
const val CHIELD_NAME = "name"
const val CHIELD_LOGIN = "login"
const val CHIELD_PASSWORD = "password"
const val CHIELD_WEIGHT = "weight"
const val CHIELD_HEIGHT = "height"
const val CHIELD_SEX = "sex"
const val CHIELD_LINKPHOTO = "linkphoto"
const val CHIELD_STEPMAX = "stepmax"

const val NODE_TRENINGS = "trenings"
const val CHIELD_TITLE = "title"
const val CHIELD_LINK = "link"
const val CHIELD_TIP = "tip"
const val CHIELD_TIME = "timetr"
const val CHIELD_COUNTEX = "countex"
const val CHIELD_OPISANIE = "opisanie"
const val CHIELD_TRENING = "trening"
const val CHIELD_LINKIMG = "linkimg"

const val NODE_PROGTRENS = "progs"
const val CHIELD_PTTITLE = "title"
const val CHIELD_PTTIP = "tip"
const val CHIELD_RTOPISANIE = "opisanie"


const val FOLDER_TRENING_IMG = "teningimg"

const val FOLDER_USER_PHOTO = "userphoto"

const val FOLDER_DOSTIG_IMG= "dostigphoto"

const val NODE_DOSTIGENIYA = "dostigeniya"
const val CHIELD_LOGINCL = "login_cl"
const val CHIELD_NAMECL = "name_cl"
const val CHIELD_LINKVIDEO = "linkvideo"
const val CHIELD_LINKPHOTOCL = "linkphoto_cl"
const val CHIELD_OPISANIEDOSTIG = "opisanie_dostig"
const val CHIELD_LINKPHOTODOSTIG = "linkphotodostig"
const val CHIELD_TEGS = "tegs"
const val CHIELD_LIKE = "like"
const val CHIELD_DISLIKE = "dislike"
const val CHIELD_LIKELIST = "like_list"
const val CHIELD_DISLIKELIST = "dislike_list"
const val CHIELD_TIMELOAD = "time_load"
const val CHIELD_DATALOAD = "data_load"




fun initFirebase() {

    REF_DABATABSE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}