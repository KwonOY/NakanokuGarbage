package com.example.nananokugarbage.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nananokugarbage.Fragment.SearchFragment
import com.example.nananokugarbage.R
import com.example.nananokugarbage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchFragment = SearchFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.Search_FrameLayout, searchFragment)
        transaction.commit()
    }

}