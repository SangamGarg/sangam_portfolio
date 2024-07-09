package com.sangam.sangamportfolio

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sangam.sangamportfolio.CV.CVActivity
import com.sangam.sangamportfolio.app_utils.HideStatusBarUtil
import com.sangam.sangamportfolio.app_utils.IntentUtil
import com.sangam.sangamportfolio.certificate.ShowCertificateActivity
import com.sangam.sangamportfolio.databinding.ActivityMainBinding
import com.sangam.sangamportfolio.projects.AndroidActivity
import com.sangam.sangamportfolio.projects.FlutterActivity
import com.sangam.sangamportfolio.projects.MernActivity
import com.sangam.sangamportfolio.work_experience.WorkExperienceActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HideStatusBarUtil.hideStatusBar(this)
        binding.cvLayout.image.setImageResource(R.drawable.cv)
        binding.cvLayout.textName.text = "CV"
        binding.cvLayout.cardLayout.setOnClickListener {
            CVActivity.start(this@MainActivity, 0, "0")
            Toast.makeText(this, "SWIPE TO SEE MORE", Toast.LENGTH_LONG).show()
        }

        binding.resumeLayout.image.setImageResource(R.drawable.resume)
        binding.resumeLayout.textName.text = "RESUME"
        binding.resumeLayout.cardLayout.setOnClickListener {
            CVActivity.start(this@MainActivity, 0, "1")

        }

        binding.internLayout.apply {
            image.setImageResource(R.drawable.internship)
            textName.text = "Internships"
            cardLayout.setOnClickListener {
                IntentUtil.startIntent(this@MainActivity, WorkExperienceActivity())
            }
        }

        binding.githubLayout.image.setImageResource(R.drawable.github)
        binding.githubLayout.textName.text = "GITHUB"
        binding.githubLayout.cardLayout.setOnClickListener {
            val appPackageName = "com.github.android"
            val webUrl = "https://github.com/SangamGarg"
            openIntentWeb(appPackageName, webUrl)
        }

        binding.linkedinLayout.image.setImageResource(R.drawable.linkedin)
        binding.linkedinLayout.textName.text = "LINKEDIN"
        binding.linkedinLayout.cardLayout.setOnClickListener {
            val appPackageName = "com.linkedin.android"
            val webUrl = "https://www.linkedin.com/in/sangam-garg-b81aaa165/"
            openIntentWeb(appPackageName, webUrl)

        }

        binding.instaLayout.image.setImageResource(R.drawable.insta)
        binding.instaLayout.textName.text = "INSTA"
        binding.instaLayout.cardLayout.setOnClickListener {
            val appPackageName = "com.instagram.android"
            val webUrl = "https://instagram.com/sangamgarg19?igshid=OTk0YzhjMDVlZA=="
            openIntentWeb(appPackageName, webUrl)

        }

        binding.youtubeLayout.image.setImageResource(R.drawable.youtube)
        binding.youtubeLayout.textName.text = "YOUTUBE"
        binding.youtubeLayout.cardLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/@sangamgarg19/featured")
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        }

        binding.androidLayout.apply {
            image.setImageResource(R.drawable.android)
            cardLayout.setOnClickListener {
                IntentUtil.startIntent(this@MainActivity, AndroidActivity())
            }
            textName.text = "ANDROID"
        }

        binding.flutterLayout.apply {
            image.setImageResource(R.drawable.flutter)
            cardLayout.setOnClickListener {
                IntentUtil.startIntent(this@MainActivity, FlutterActivity())
            }
            textName.text = "FLUTTER"
        }

        binding.mernLayout.apply {
            image.setImageResource(R.drawable.mern)
            cardLayout.setOnClickListener {
                IntentUtil.startIntent(this@MainActivity, MernActivity())
            }
            textName.text = "MERN"
        }





        binding.certificateLayout.apply {
            image.setImageResource(R.drawable.education)
            textName.text = "TRAININGS CERTIFICATES"
            cardLayout.setOnClickListener {
                IntentUtil.startIntent(this@MainActivity, ShowCertificateActivity())
            }
        }
        binding.achievementsLayout.apply {
            image.setImageResource(R.drawable.achievement)
            textName.text = "ACHIEVEMENTS"
            cardLayout.setOnClickListener {
                IntentUtil.startIntent(this@MainActivity, ShowExtraCertificateActivity())
            }
        }
        binding.sketchLayout.apply {
            image.setImageResource(R.drawable.sketch)
            textName.text = "SKETCH"
        }
        binding.sketchLayout.cardLayout.setOnClickListener {
            CVActivity.start(this@MainActivity, 0, "2")
            Toast.makeText(this, "SWIPE TO SEE MORE", Toast.LENGTH_LONG).show()
        }
        binding.more.setOnClickListener {
            val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.about_me_dialog, null)
            dialog.setView(view)
            val about = view.findViewById<TextView>(R.id.textViewAbout_me)
            about.text = getText(R.string.about_me)
            dialog.create().show()
        }

        binding.phone.setOnClickListener {
            val phoneNumber = "tel:7210581614"
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse(phoneNumber)
            }
            startActivity(callIntent)
        }

        binding.mail.setOnClickListener {
            val intent = Intent(this, EmailActivity::class.java)
            startActivity(intent)
        }

    }

    private fun openIntentWeb(appPackageName: String, webUrl: String) {
        try {
            val intent = packageManager.getLaunchIntentForPackage(appPackageName)
            if (intent != null) {
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(webUrl)
                startActivity(intent)
            } else {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                startActivity(webIntent)
            }
        } catch (e: ActivityNotFoundException) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(webIntent)
        }
    }


}