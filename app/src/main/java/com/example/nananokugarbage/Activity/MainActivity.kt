package com.example.nananokugarbage.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nananokugarbage.Fragment.SearchFragment
import com.example.nananokugarbage.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        val searchFragment = SearchFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Main_FrameLayout, searchFragment)
        transaction.commit()
    }

}