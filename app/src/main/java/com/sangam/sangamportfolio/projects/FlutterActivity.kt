package com.sangam.sangamportfolio.projects

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.databinding.ActivityAndroidBinding
import com.sangam.sangamportfolio.databinding.ActivityFlutterBinding

class FlutterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFlutterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        binding.bmiLayout.apply {
            image.setImageResource(R.drawable.bmi)
            textName.text = "BMI APP"
            cardLayout.setOnClickListener {
                dialogBox(getText(R.string.bmi).toString(),"https://github.com/SangamGarg/bmi_app_flutter")
            }
        }

    }

    fun dialogBox(msg: String, string: String) {
        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.projects_layout_dialog, null)
        dialog.setView(view)
        val alert = dialog.create()
        val detail = view.findViewById<TextView>(R.id.textDetailProject)
        detail.text = msg
        val certificate = view.findViewById<TextView>(R.id.textViewGoGithub)
        certificate.setOnClickListener {
            alert.dismiss()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(string)
            startActivity(intent)
        }
        alert.show()
    }
}