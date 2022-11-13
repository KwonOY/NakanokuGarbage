package com.example.nananokugarbage.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nananokugarbage.R

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val googleMapFragment = GoogleMapFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.Search_ContentsLayout, googleMapFragment)
        transaction.commit()
    }
}