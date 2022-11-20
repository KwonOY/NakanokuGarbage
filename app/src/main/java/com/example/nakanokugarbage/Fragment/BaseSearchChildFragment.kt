package com.example.nakanokugarbage.Fragment

import androidx.fragment.app.Fragment

open class BaseSearchChildFragment: Fragment() {

    protected lateinit var mSearchText: String

    open fun setSearchResultText(searchText: String){
        mSearchText = searchText
    }

}