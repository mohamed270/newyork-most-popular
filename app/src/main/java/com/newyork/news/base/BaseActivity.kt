package com.newyork.news.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.newyork.news.R
import com.newyork.news.view.LoadingDialog


abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : LocalizationActivity()  {

    abstract val viewModel: VM
    abstract val binding: VB

    abstract fun onActivityCreated()

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadingDialog = LoadingDialog(this)

        initObservers()
        onActivityCreated()
    }


    private fun initObservers() {
        viewModel.errorMsgLiveData.observe(this, { showErrorMsg(it) })
        viewModel.errorResourceMsgLiveData.observe(this, { showErrorMsg(getString(it)) })

        viewModel.successMsgLiveData.observe(this, { showSuccessMsg(it) })
        viewModel.successResourceMsgLiveData.observe(this, { showSuccessMsg(getString(it)) })

        viewModel.loadingLiveData.observe(this, {
            if (it)
                showLoading()
            else
                hideLoading()
        })


    }

    fun showErrorMsg(msg: String) {

        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_red, null)
        val text: TextView = layout.findViewById(R.id.text)
        text.text = msg
        with (Toast(applicationContext)) {
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }

    fun showSuccessMsg(msg: String) {

        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_green, null)
        val text: TextView = layout.findViewById(R.id.text)
        text.text = msg
        with (Toast(applicationContext)) {
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }


    fun showLoading(msg: String?) {
        hideKeyboard()
        loadingDialog.show(msg)
    }

    fun showLoading() {
        showLoading(getString(R.string.loading_dialog_loading_msg))
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }

    protected open fun setTitleWithBack(title: String) {
        setTitle(title)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    protected open fun showKeyboard(focusedView: View) {
        try {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(focusedView, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected open fun hideKeyboard() {
        try {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            var view = currentFocus
            if (view == null) {
                view = View(this)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isLandscape(): Boolean {
        val orientation = resources.configuration.orientation
        return  orientation == Configuration.ORIENTATION_LANDSCAPE
    }

//    fun isTablet(): Boolean {
//        return ((resources.configuration.screenLayout
//                and Configuration.SCREENLAYOUT_SIZE_MASK)
//                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            onBackPressed() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
            crossinline bindingInflater: (LayoutInflater) -> T) =
            lazy(LazyThreadSafetyMode.NONE) {
                bindingInflater.invoke(layoutInflater)
            }
}