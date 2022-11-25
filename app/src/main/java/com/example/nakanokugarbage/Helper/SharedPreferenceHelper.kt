package com.example.nakanokugarbage.Helper

import android.content.Context
import com.example.nakanokugarbage.Constant
import com.example.nakanokugarbage.Model.BlockModel

class SharedPreferenceHelper(context: Context) {

    val mContext = context

    fun setUserLocation(blockNo: Int, blockName: String) {
        val sharedPreference = mContext.getSharedPreferences(Constant.SheardPreferenceName.UserInfo.name, 0)
        val editor = sharedPreference.edit()
        editor.putInt(Constant.SheardPreferenceName.UserInfoKey.BlockNo.name, blockNo)
        editor.putString(Constant.SheardPreferenceName.UserInfoKey.BlockName.name, blockName)
        editor.apply()
    }

    fun getUserLocation(): BlockModel{
        val sharedPreference = mContext.getSharedPreferences(Constant.SheardPreferenceName.UserInfo.name, 0)
        val blockNo = sharedPreference.getInt(Constant.SheardPreferenceName.UserInfoKey.BlockNo.name, -1)
        var blockName = sharedPreference.getString(Constant.SheardPreferenceName.UserInfoKey.BlockName.name, "")
        if (blockName == null) blockName = ""
        return BlockModel(blockNo, blockName)
    }

}