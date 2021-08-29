package com.example.merucabassignment.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel:ViewModel() {
    var showProgressBar=MutableLiveData<Boolean>()
     var showToast=MutableLiveData<String>();
    val uiScope = CoroutineScope(Dispatchers.IO + Job())
}