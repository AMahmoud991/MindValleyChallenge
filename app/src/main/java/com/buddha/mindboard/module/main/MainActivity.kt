package com.buddha.mindboard.module.main


import android.net.Uri
import android.os.Bundle

import com.buddha.mindboard.R
import com.buddha.mindboard.module.base.DaggerBaseActivity
import com.buddha.mindboard.util.ActivityUtils

class MainActivity : DaggerBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainFragment = MainFragment.newInstance()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, mainFragment, R.id.container)
    }
}
