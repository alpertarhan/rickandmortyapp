package com.project.rickmortycaseapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import com.project.rickmortycaseapp.R

class LoadingIndicator(val mActivity: Activity) {
    private lateinit var isDialog:AlertDialog
    fun startLoadingIndicator(){
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_item,null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }
    fun isDismiss(){
        isDialog.dismiss()
    }

}