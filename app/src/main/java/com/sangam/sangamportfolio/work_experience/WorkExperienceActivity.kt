package com.sangam.sangamportfolio.work_experience

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.databinding.ActivityWorkExperienceBinding

class WorkExperienceActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWorkExperienceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkExperienceBinding.inflate(layoutInflater)
        window.statusBarColor = Color.WHITE
        setContentView(binding.root)
        initListener()

    }

    private fun initListener() {
        binding.androidIntern.setOnClickListener {
            dialogBox(
                getString(R.string.talentitan),
                R.drawable.androidinterntalentitan
            )
        }
    }

    fun dialogBox(msg: String, imageSource: Int) {
        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.certificate_layout_dialog, null)
        dialog.setView(view)
        val alert = dialog.create()
        val detail = view.findViewById<TextView>(R.id.textDetail)
        detail.text = msg
        val certificate = view.findViewById<TextView>(R.id.textCertificateView)
        certificate.setOnClickListener {
            alert.dismiss()
            binding.frameLayout.visibility = View.VISIBLE
            binding.imageViewCertificate.setImageResource(imageSource)
        }
        alert.show()
    }


    override fun onBackPressed() {
        if (binding.frameLayout.visibility == View.VISIBLE) {
            binding.frameLayout.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}