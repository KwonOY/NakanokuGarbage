package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nakanokugarbage.databinding.AlarmLayoutBinding
import com.example.nakanokugarbage.databinding.TodayGarbageLayoutBinding

class TodayGarbageFragment : BaseMainFragment() {

    private var mInstace : TodayGarbageFragment? = null
    private lateinit var mBinding: TodayGarbageLayoutBinding

    override fun getInstance(): TodayGarbageFragment {
        if (mInstace == null) {
            mInstace = TodayGarbageFragment()
        }
        return mInstace!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = TodayGarbageLayoutBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}