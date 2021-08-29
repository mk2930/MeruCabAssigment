package com.example.merucabassignment.ui.landing

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.merucabassignment.R
import com.example.merucabassignment.base.BaseActivity
import com.example.merucabassignment.data.database.AppDB
import com.example.merucabassignment.databinding.ActivityLandingBinding
import com.example.merucabassignment.ui.fav.FavActivity
import com.example.merucabassignment.ui.potato.PotatoViewModel
import com.example.merucabassignment.utils.CommonUtils
import com.example.merucabassignment.utils.ViewModelFactory

class LandingActivity : BaseActivity() {

    private lateinit var binding: ActivityLandingBinding
    private lateinit var vm:LandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        setnavigation()


    }

    private fun getData() {
        if (CommonUtils.isInternetAvailable(this))
            vm.getAllData()
        else
            showToast(CommonUtils.NO_INTERNET)


    }

    private fun setnavigation() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_landing)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.landing_activity_menu, menu);

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_fav->{
                FavActivity.launch(context as FragmentActivity)
            }
        }


        return super.onOptionsItemSelected(item)
    }



    override fun initViewModel() {

        val db=AppDB.getIntance(this)
        var factory=ViewModelFactory(db!!)
        vm =
            ViewModelProvider(this,factory).get(LandingViewModel::class.java)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_landing)
        binding?.lifecycleOwner=this
        binding?.vm=vm
        context=this
        setViewModel(vm)

    }

    override fun observeLivedata() {
        vm.showProgressBar.observe(this, Observer {isLoading->
            if(isLoading){
                binding.progressbar.visibility=View.VISIBLE
            }
            else{
                binding.progressbar.visibility=View.GONE
            }


        })
    }
}