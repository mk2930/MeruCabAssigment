package com.example.merucabassignment.ui.fav

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merucabassignment.R
import com.example.merucabassignment.base.BaseActivity
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.data.model.Recipe
import com.example.merucabassignment.databinding.ActivityFavBinding
import com.example.merucabassignment.ui.adapter.RecipeAdapter
import com.example.merucabassignment.utils.ViewModelFactory

class FavActivity : BaseActivity() {
    var list = ArrayList<Recipe>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding: ActivityFavBinding
    lateinit var vm: FavViewModel

    companion object {
        fun launch(activity: Activity) {
            val intent = Intent(activity, FavActivity::class.java)
            activity.startActivity(intent)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun initViewModel() {

        val db = AppDB.getIntance(this)
        var factory = ViewModelFactory(db!!)
        vm =
            ViewModelProvider(this, factory).get(FavViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fav)
        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        binding?.lifecycleOwner = this
        binding?.vm = vm
        context = this
        setViewModel(vm)
        adapter = RecipeAdapter(list)
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.setHasFixedSize(true)
        binding.rvList.adapter = adapter

        vm.getAllData()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return true
    }


    override fun observeLivedata() {
        vm.data.observe(this, Observer { data ->
            if (data.size > 0) {
                binding.rvList.visibility = View.VISIBLE
                binding.tvNodata.visibility = View.GONE
                list.addAll(data)
                adapter.notifyDataSetChanged()

            } else {
                binding.rvList.visibility = View.GONE
                binding.tvNodata.visibility = View.VISIBLE
            }


        })

    }
}