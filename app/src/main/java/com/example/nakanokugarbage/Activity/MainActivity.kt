package com.example.nakanokugarbage.Activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.nakanokugarbage.Fragment.*
import com.example.nakanokugarbage.R
import com.example.nakanokugarbage.databinding.MainLayoutBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainLayoutBinding

    private val selectedNaviItemListener =
        NavigationBarView.OnItemSelectedListener { item ->
            var nowFragment: Fragment?
            when (item.itemId) {
                R.id.menu2 -> {
                    nowFragment = AlarmFragment().getInstance()
                }
                R.id.menu3 -> {
                    nowFragment = GarbageChartFragment().getInstance()
                }
                R.id.menu4 -> {
                    nowFragment = SearchGarbageFragment().getInstance()
                }
                R.id.menu5 -> {
                    nowFragment = SettingFragment().getInstance()
                }
                else -> {
                    nowFragment = TodayGarbageFragment().getInstance()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.main_frame, nowFragment).commit()
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.navView.setOnItemSelectedListener(selectedNaviItemListener)
        mBinding.navView.selectedItemId = R.id.menu1
    }
}