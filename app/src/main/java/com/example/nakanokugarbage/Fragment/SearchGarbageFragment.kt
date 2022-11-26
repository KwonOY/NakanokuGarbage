package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nakanokugarbage.databinding.SearchGarbageLayoutBinding

class SearchGarbageFragment : BaseMainFragment() {

    private var mInstace : SearchGarbageFragment? = null
    private lateinit var mBinding: SearchGarbageLayoutBinding

    override fun getInstance(): SearchGarbageFragment {
        if (mInstace == null) {
            mInstace = SearchGarbageFragment()
        }
        return mInstace!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = SearchGarbageLayoutBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}