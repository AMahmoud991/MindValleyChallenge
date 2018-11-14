package com.buddha.mindboard.module.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.buddha.mindboard.module.home.HomeActivity

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
