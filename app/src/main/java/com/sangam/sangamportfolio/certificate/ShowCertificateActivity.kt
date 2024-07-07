package com.sangam.sangamportfolio.certificate

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.databinding.ActivityShowCertificateBinding

class ShowCertificateActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShowCertificateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT

        binding.androidC.setOnClickListener {
            dialogBox(
                "Successfully completed a 8 weeks online certified training on Android App Development. The training consisted of Introduction to Android, World of Kotlin, Android Kick-Off, Higher Order Functionalities, and The Final Project modules.",
                R.drawable.androidc
            )
        }
        binding.CyberSecurityCEssential.setOnClickListener {
            dialogBox(
                "Describe the tactics, techniques and procedures used by cyber criminals. Describe the principles of confidentiality, integrity, and availability as they relate to date states and cybersecurity\n" +
                        "\n" +
                        "countermeasures Describe technologies, products and procedures used to protect confidentiality, ensure integrity and provide high\n" +
                        "\n" +
                        "Explain how cybersecurity professionals use technologies, processes and procedures to defend all components of the network",
                R.drawable.cyberessential
            )
        }
        binding.cyberCIntro.setOnClickListener {
            dialogBox(
                "Explain the basics of being safe online, including what cybersecurity and its potential impact.\n" +
                        "\n" +
                        "Explain the most common cyber threats, attacks, and vulnerabilities. Explain how to protect oneself while onine.\n" +
                        "\n" +
                        "Explain how organizations can protect their operations against these attacks Access a variety of information and resources to explore the different career options in cybersecurity",
                R.drawable.cyberintro
            )
        }
        binding.pythonC.setOnClickListener {
            dialogBox(
                "This is a python basics course. It is an online non_credit course authorized by University Of Michigan and offered through Coursera",
                R.drawable.python
            )
        }
        binding.mernC.setOnClickListener {
            dialogBox(
                "It was a one month internship program where I was trained in MERN and henceforth given a project of my own choice to be completed within deadline in MERN .",
                R.drawable.mern
            )
        }
        binding.tpcC.setOnClickListener {
            dialogBox("This was a 80 hrs Training", R.drawable.mern)
        }
        binding.flutterC.setOnClickListener {
            dialogBox(
                "Completed \"Learn Flutter & Dart to Build iOS & Android Apps\" on Udemy. Mastered Flutter and Dart for cross-platform app development.",
                R.drawable.flutterc
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