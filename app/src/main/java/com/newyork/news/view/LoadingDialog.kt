package com.newyork.news.view

import android.app.ProgressDialog
import android.content.Context
import kotlin.jvm.JvmOverloads
import com.newyork.news.R
import java.lang.Exception

class LoadingDialog(private val context: Context?) {
    private var progressDialog: ProgressDialog? = null
    @JvmOverloads
    fun show(msg: String? = null) {
        var msg = msg
        if (context == null) return
        if (msg == null) msg = context.getString(R.string.loading_dialog_loading_msg)
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setCancelable(false)
        } else if (isShowing) {
            progressDialog!!.setMessage(msg)
            return
        }
        progressDialog!!.setMessage(msg)
        progressDialog!!.show()
    }

    fun dismiss() {
        if (progressDialog == null) return
        try {
            if (progressDialog!!.isShowing) progressDialog!!.dismiss()
        } catch (e: Exception) {
        }
    }

    val isShowing: Boolean
        get() = if (progressDialog == null) false else progressDialog!!.isShowing
}