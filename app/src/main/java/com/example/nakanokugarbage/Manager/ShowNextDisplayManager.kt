package com.example.nakanokugarbage.Manager

import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.nakanokugarbage.Activity.MainActivity
import com.example.nakanokugarbage.Fragment.GoogleMapFragment
import com.example.nakanokugarbage.Fragment.SearchFragment
import com.example.nakanokugarbage.Helper.SharedPreferenceHelper

open class ShowNextDisplayManager(context: Context) {

    private val mContext = context

    fun getNextActivity() : Class<*>?{
        val blockInfo = SharedPreferenceHelper(mContext).getUserLocation()
        if (blockInfo.index > -1){
            return MainActivity::class.java
        }
        return null
    }

    fun getNextFragment(currentFragment: Fragment?) : Fragment?{
        val blockInfo = SharedPreferenceHelper(mContext).getUserLocation()
        if (blockInfo.index == -1){
            return SearchFragment()
        }
        return null
    }

    fun getChildFragment(currentFragment: Fragment?): Fragment?{
        val blockInfo = SharedPreferenceHelper(mContext).getUserLocation()
        if (currentFragment is SearchFragment) {
            if (blockInfo.index == -1) {
                return GoogleMapFragment()
            }
        }
        return null
    }

}