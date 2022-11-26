package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nakanokugarbage.databinding.SettingLayoutBinding

class SettingFragment : BaseMainFragment() {

    private var mInstace : SettingFragment? = null
    private lateinit var mBinding: SettingLayoutBinding

    override fun getInstance(): SettingFragment {
        if (mInstace == null) {
            mInstace = SettingFragment()
        }
        return mInstace!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = SettingLayoutBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}