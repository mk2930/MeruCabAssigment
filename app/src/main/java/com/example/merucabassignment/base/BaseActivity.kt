package com.example.merucabassignment.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity : AppCompatActivity(),BaseFunctions {

    private lateinit var basevm: BaseViewModel
    lateinit var context: Context

    fun setViewModel(basevm: BaseViewModel) {
        this.basevm = basevm
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observeLivedata()
        basevm.showToast.observe(this, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })



    }


}