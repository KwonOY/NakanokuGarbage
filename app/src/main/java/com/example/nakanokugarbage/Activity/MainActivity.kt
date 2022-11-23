package com.example.nakanokugarbage.Activity

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nakanokugarbage.Fragment.SearchFragment
import com.example.nakanokugarbage.R
import com.example.nakanokugarbage.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        checkLocationPermission()

        val searchFragment = SearchFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(mBinding.MainFrameLayout.id, searchFragment)
        transaction.commit()
    }

    private fun checkLocationPermission() {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                resources.getInteger(R.integer.location_permission_request_code)
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            R.integer.location_permission_request_code -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Dilaog","is OK")
                }
            }
        }
    }

}