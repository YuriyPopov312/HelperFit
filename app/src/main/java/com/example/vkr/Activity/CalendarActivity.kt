package com.example.vkr.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.vkr.Fragments.ClientPlanTreningFragment
import com.example.vkr.R
import kotlinx.android.synthetic.main.___calendar_layout.*

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        makeCurrentFragment(ClientPlanTreningFragment())

    }

    override fun onStart() {
        super.onStart()
        backBtn.setOnClickListener { onBackPressed() }
    }

    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.window, fragment)
            commit()
        }
    }
}