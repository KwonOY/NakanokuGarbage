package com.example.nakanokugarbage.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nakanokugarbage.Fragment.SearchFragment
import com.example.nakanokugarbage.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val searchFragment = SearchFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(mBinding.MainFrameLayout.id, searchFragment)
        transaction.commit()
    }
}