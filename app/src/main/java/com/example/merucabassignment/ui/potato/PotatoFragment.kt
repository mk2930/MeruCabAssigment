package com.example.merucabassignment.ui.potato

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.merucabassignment.R
import com.example.merucabassignment.base.BaseFragment
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.Recipe
import com.example.merucabassignment.databinding.FragmentPotatoBinding
import com.example.merucabassignment.ui.RecipeFragment
import com.example.merucabassignment.utils.CommonUtils
import com.example.merucabassignment.utils.ViewModelFactory

class PotatoFragment : RecipeFragment() {

    private lateinit var vm: PotatoViewModel
    private var _binding: FragmentPotatoBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun getCreateView(): View {
        return _binding?.root!!

    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_potato, container, false)
        _binding?.lifecycleOwner = this
        _binding?.vm = vm
        setRecylerView(_binding?.rvList!!,requireActivity())
        vm.getAllData()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initViewModel() {
        Log.e("adsadsa", "Sadsa")
        val db = AppDB.getIntance(requireActivity())
        var factory = ViewModelFactory(db!!)
        vm =
            ViewModelProvider(this, factory).get(PotatoViewModel::class.java)
        context = requireActivity()

        setViewModel(vm)


    }



    override fun observeLivedata() {
        vm.data?.observe(this, Observer { data ->
            addList(data as ArrayList<Recipe>)

        })
        activityVm.potatoUpdated.observe(this, Observer {
            vm.getAllData()
        })
    }
}