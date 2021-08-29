package com.example.merucabassignment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseFunctions {

    private lateinit var basevm: BaseViewModel
    @get:JvmName("getContext_") lateinit var context: Context


    fun setViewModel(basevm: BaseViewModel) {
        this.basevm = basevm
    }

    abstract fun getCreateView(): View
    abstract fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflateView(inflater, container, savedInstanceState)

        observeLivedata()
        return getCreateView()
    }


}