package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nakanokugarbage.databinding.GarbageChartLayoutBinding

class GarbageChartFragment : BaseMainFragment() {

    private var mInstace : GarbageChartFragment? = null
    private lateinit var mBinding: GarbageChartLayoutBinding

    override fun getInstance(): GarbageChartFragment {
        if (mInstace == null) {
            mInstace = GarbageChartFragment()
        }
        return mInstace!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = GarbageChartLayoutBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}