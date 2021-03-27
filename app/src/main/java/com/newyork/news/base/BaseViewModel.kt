package com.newyork.news.base

import androidx.annotation.StringRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.newyork.domain.model.ErrorModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel @ViewModelInject constructor(): ViewModel() {

    private val _errorMsgLiveData = LiveEvent<String>()
    val errorMsgLiveData: LiveData<String> get() = _errorMsgLiveData

    private val _errorResourceMsgLiveData = LiveEvent<Int>()
    val errorResourceMsgLiveData: LiveData<Int> get() = _errorResourceMsgLiveData

    private val _successMsgLiveData = LiveEvent<String>()
    val successMsgLiveData: LiveData<String> get() = _successMsgLiveData

    private val _successResourceMsgLiveData = LiveEvent<Int>()
    val successResourceMsgLiveData: LiveData<Int> get() = _successResourceMsgLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData


    private val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    fun execute(task: suspend CoroutineScope.() -> Unit) =
            viewModelScope.launch(context = handler, block = task)

    fun showLoading() {
        _loadingLiveData.value = true
    }

    fun hideLoading() {
        _loadingLiveData.value = false
    }

    fun showSuccessMsg(msg: String) {
        _successMsgLiveData.value = msg
    }

    fun showSuccessMsg(@StringRes stringId: Int) {
        _successResourceMsgLiveData.value = stringId
    }

    fun showErrorMsg(msg: String) {
        if(msg.isNotEmpty())
            _errorMsgLiveData.value = msg
    }

    fun showErrorMsg(@StringRes stringId: Int) {
        _errorResourceMsgLiveData.value = stringId
    }

    fun handleError(t: Throwable?) {
        hideLoading()


        when(t) {
            is ErrorModel.Connection -> showErrorMsg("Check your internet connection")
            is ErrorModel.Network -> {
                when(t.code) {
                    500 -> {
                        showErrorMsg("Internal Server Error")
                    }
                    else ->{
                        if(t.serverMessage.isNullOrBlank()) {
                            showErrorMsg("Error occurred")
                        }
                        else {
                            showErrorMsg(t.serverMessage!!)
                        }
                    }
                }

            }

            else -> showErrorMsg("Error occurred")

        }
    }
}