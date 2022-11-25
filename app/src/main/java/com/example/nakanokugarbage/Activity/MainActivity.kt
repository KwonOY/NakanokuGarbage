package com.example.nakanokugarbage.Activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nakanokugarbage.Manager.ShowFragmentManager
import com.example.nakanokugarbage.R
import com.example.nakanokugarbage.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("myTest","is Main")
        mBinding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        checkLocationPermission()

        val nextShowFragment = ShowFragmentManager(this).getNextFragment(null)
        nextShowFragment?.run {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(mBinding.MainFrameLayout.id, this)
            transaction.commit()
        }
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
                    Log.d("myTest","is OK")
                }
            }
        }
    }

}