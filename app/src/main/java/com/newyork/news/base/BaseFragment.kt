package com.newyork.news.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.newyork.news.R
import com.newyork.news.view.LoadingDialog

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(@LayoutRes layoutId: Int)
    : Fragment(layoutId) {

    abstract val viewModel: VM
    abstract val binding: VB

    protected abstract fun onViewCreated()

    private lateinit var loadingDialog: LoadingDialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadingDialog = LoadingDialog(context)

        initObservers()
        onViewCreated()
    }

    private fun initObservers() {
        viewModel.errorMsgLiveData.observe(viewLifecycleOwner, { showErrorMsg(it) })
        viewModel.errorResourceMsgLiveData.observe(viewLifecycleOwner, { showErrorMsg(getString(it)) })

        viewModel.successMsgLiveData.observe(viewLifecycleOwner, { showSuccessMsg(it) })
        viewModel.successResourceMsgLiveData.observe(viewLifecycleOwner, { showSuccessMsg(getString(it)) })

        viewModel.loadingLiveData.observe(viewLifecycleOwner, {
            if (it)
                showLoading()
            else
                hideLoading()
        })


    }

    fun showErrorMsg(msg: String) {

        if(isAdded) {
            val inflater = layoutInflater
            val layout = inflater.inflate(R.layout.custom_toast_red, null)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = msg
            with(Toast(requireContext())) {
                duration = Toast.LENGTH_LONG
                view = layout
                show()
            }
        }
    }

    fun showSuccessMsg(msg: String) {

        if(isAdded) {
            val inflater = layoutInflater
            val layout = inflater.inflate(R.layout.custom_toast_green, null)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = msg
            with(Toast(requireContext())) {
                duration = Toast.LENGTH_LONG
                view = layout
                show()
            }
        }
    }

    fun showLoading(msg: String?) {
        hideKeyboard()
        loadingDialog.show(msg)
    }

    open fun showLoading() {
        showLoading(requireContext().getString(R.string.loading_dialog_loading_msg))
    }

    open fun hideLoading() {
        loadingDialog.dismiss()
    }

    protected open fun showKeyboard(focusedView: View) {
        try {
            val imm = activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(focusedView, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected open fun hideKeyboard() {
        try {
            val imm = activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity?.currentFocus
            if (view == null) {
                view = View(activity)
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

    fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
            FragmentViewBindingDelegate(this, viewBindingFactory)

}