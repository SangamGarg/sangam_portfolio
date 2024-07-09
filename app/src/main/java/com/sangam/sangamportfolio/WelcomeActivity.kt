package com.sangam.sangamportfolio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sangam.sangamportfolio.app_utils.HideStatusBarUtil
import com.sangam.sangamportfolio.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HideStatusBarUtil.hideStatusBar(this)
        binding.textWelcome.animate().apply {
            duration = 1500
            rotationXBy(360f)
        }
        binding.continueButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}