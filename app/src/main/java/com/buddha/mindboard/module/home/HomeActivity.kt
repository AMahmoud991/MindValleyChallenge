package com.buddha.mindboard.module.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.buddha.mindboard.R
import com.buddha.mindboard.module.base.DaggerBaseActivity
import com.buddha.mindboard.util.ActivityUtils
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : DaggerBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.abc_shrink_fade_out_from_bottom, R.anim.abc_grow_fade_in_from_bottom)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.abc_grow_fade_in_from_bottom,R.anim.abc_shrink_fade_out_from_bottom)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Search feature is not implemented.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val mainFragment = HomeFragment.newInstance()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, mainFragment, R.id.container)
    }
}
