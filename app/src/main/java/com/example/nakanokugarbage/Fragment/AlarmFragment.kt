package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nakanokugarbage.databinding.AlarmLayoutBinding

class AlarmFragment : BaseMainFragment() {

    private var mInstace : AlarmFragment? = null
    private lateinit var mBinding: AlarmLayoutBinding

    override fun getInstance(): AlarmFragment {
        if (mInstace == null) {
            mInstace = AlarmFragment()
        }
        return mInstace!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = AlarmLayoutBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}