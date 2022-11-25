package com.example.nakanokugarbage.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface.OnClickListener

class CustomSelectDialog(context: Context) : Dialog(context) {
    private val mContext = context
    internal lateinit var builder: AlertDialog.Builder

    fun createDialog(title: String, message: String): AlertDialog.Builder {
        builder = AlertDialog.Builder(mContext)
        builder.setTitle(title)
        builder.setMessage(message)
        return builder
    }

    fun createDialog(title: String, message: String, iconId: Int): AlertDialog.Builder {
        builder = createDialog(title, message)
        builder.setIcon(iconId)
        return builder
    }

    fun createDialog(titleId: Int, messageId: Int, iconId: Int): AlertDialog.Builder {
        builder = createDialog(mContext.getString(titleId), mContext.getString(messageId), iconId)
        builder.setIcon(iconId)
        return builder
    }

    fun createDialog(titleId: Int, message: String, iconId: Int): AlertDialog.Builder {
        builder = createDialog(mContext.getString(titleId), message, iconId)
        builder.setIcon(iconId)
        return builder
    }

    fun setButtonListener(listener: OnClickListener, positive: Boolean = true, negative: Boolean = true){
        if (positive) builder.setPositiveButton("OK", listener)
        if (negative) builder.setNegativeButton("Cancel", listener)
    }

    override fun show() {
        builder.show()
    }
}