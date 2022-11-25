package com.example.nakanokugarbage.View

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.example.nakanokugarbage.Interface.AfterActionListener

class ActionHideKeyboardTextView(context: Context, attr: AttributeSet): AppCompatAutoCompleteTextView(context, attr) {

    var mListener: AfterActionListener? = null

    init {
        this.setOnItemClickListener { parent, view, position, id ->
            hideKeyboard(this.rootView)
            mListener?.run { this.onAfterActionListener(view) }
        }
        this.setOnKeyListener { v, keyCode, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard(this.rootView)
                mListener?.run { this.onAfterActionListener(v) }
            }
            false
        }
    }

    fun setAfterActionListener(listener: AfterActionListener){
        mListener = listener
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0);
        view.clearFocus()
    }
}