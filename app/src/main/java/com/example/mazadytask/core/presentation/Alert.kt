package com.example.mazadytask.core.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.mazadytask.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Alert {


    fun showDialog(
        context: Context,
        message: String
    ) {
        val factory = LayoutInflater.from(context)
        val deleteDialogView: View = factory.inflate(R.layout.alert, null)
        val dialog = MaterialAlertDialogBuilder(context).create()
        dialog.setView(deleteDialogView)
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val alertMessage = deleteDialogView.findViewById<TextView>(R.id.alert_message)
        val alertPostiveBtn = deleteDialogView.findViewById<TextView>(R.id.alert_positive_btn)
        alertMessage.text = message

        alertPostiveBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}