package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.example.nakanokugarbage.Helper.DataBaseHelper
import com.example.nakanokugarbage.databinding.SearchLayoutBinding
class SearchFragment : Fragment() {

    private lateinit var mBinding: SearchLayoutBinding
    private lateinit var mSearchEditTextView: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SearchLayoutBinding.inflate(inflater, container, false)
        mSearchEditTextView = mBinding.SearchTextView
        val db = context?.let { DataBaseHelper.getInstance(it) }
        val cityBlocks = db?.let { db.cityBlockDaoDao().getAll()}
        cityBlocks?.let {
            val textArray : ArrayList<String> = ArrayList()
            for (cityBlock in it) {
                textArray.add(cityBlock.japanese_block_name)
            }
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, textArray)
            mSearchEditTextView.setAdapter(adapter)
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val googleMapFragment = GoogleMapFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(mBinding.SearchContentsLayout.id, googleMapFragment)
        transaction.commit()
    }
}