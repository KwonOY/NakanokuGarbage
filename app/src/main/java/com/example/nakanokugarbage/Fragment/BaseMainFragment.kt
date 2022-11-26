package com.example.nakanokugarbage.Fragment

import androidx.fragment.app.Fragment

abstract class BaseMainFragment: Fragment() {
    abstract fun getInstance(): BaseMainFragment
}