package com.buddha.mindboard.module.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.buddha.mindboard.R
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.module.base.DaggerBaseActivity
import com.buddha.mindboard.util.ActivityUtils
import com.buddha.mindboard.util.Config

class DetailsActivity : DaggerBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val datum: Datum? = intent?.extras?.getParcelable(Config.Extras.DATUM)
        val fragment: DetailsFragment? = DetailsFragment.newInstance(datum)
        ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment ?: return, R.id.container)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun start(context: Context?, datum: Datum) {
            if (context == null) return
            val intent = Intent(context, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(Config.Extras.DATUM, datum)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}
