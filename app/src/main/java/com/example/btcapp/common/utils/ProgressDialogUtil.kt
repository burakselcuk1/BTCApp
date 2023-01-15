package com.example.btcapp.common.utils


import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.graphics.drawable.toDrawable
import com.example.btcapp.R


object ProgressDialogUtil {

    lateinit var progressDialog: Dialog
    fun showLoadingProgress(context: Context): Dialog {

        progressDialog = Dialog(context)
        progressDialog.let {
            //it.show()
            it.window!!.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog_view)
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(true)

            return it
        }
    }

    fun start(){
        progressDialog.show()
    }
    fun stop(){
        progressDialog.cancel()
        progressDialog.dismiss()
    }

}
