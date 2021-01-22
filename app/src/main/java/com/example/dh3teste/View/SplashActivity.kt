package com.example.dh3teste.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dh3teste.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000// 1sec
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)




        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))

            finish()
        }, splashTimeOut)
    }

}