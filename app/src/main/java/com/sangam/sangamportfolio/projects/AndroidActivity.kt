package com.sangam.sangamportfolio.projects

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.databinding.ActivityAndroidBinding
import androidx.core.net.toUri

class AndroidActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAndroidBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        binding.arappLayout.apply {
            image.setImageResource(R.drawable.ar)
            textName.text = "AR APP"
            cardLayout.setOnClickListener {
                val dialog = AlertDialog.Builder(this@AndroidActivity)
                val view = layoutInflater.inflate(R.layout.projects_layout_dialog, null)
                dialog.setView(view)
                val alert = dialog.create()
                val detail = view.findViewById<TextView>(R.id.textDetailProject)
                detail.text = getText(R.string.ar)
                val certificate = view.findViewById<TextView>(R.id.textViewGoGithub)
                certificate.setOnClickListener {
                    Toast.makeText(
                        this@AndroidActivity,
                        "Sorry!! This Project Is Not Pushed To Github Till Now", Toast.LENGTH_SHORT
                    ).show()
                }
                alert.show()
            }
        }
        binding.fitnessLayout.apply {
            image.setImageResource(R.drawable.muscleplayicon)
            textName.text = "FITNESS APPLICATION (MUSCLE PLAY)"
            cardLayout.setOnClickListener {
                dialogBox(
                    getText(R.string.fitness).toString(),
                    "https://github.com/SangamGarg/MusclePlayApp"
                )
            }
        }
        binding.quonoteLayout.apply {
            image.setImageResource(R.drawable.quonote)
            textName.text = "QUONOTE (PERSONAL PROJECT)"
            cardLayout.setOnClickListener {
                dialogBox(
                    getText(R.string.quonote).toString(),
                    "https://github.com/SangamGarg/QuoNoteApp"
                )
            }
        }

        binding.isgLayout.apply {
            image.setImageResource(R.drawable.igllogo)
            textName.text = "INDIAN STUDY GROUP (FREELANCING WORK)"
            cardLayout.setOnClickListener {
                dialogBox(
                    getString(R.string.isg),
                    "https://play.google.com/store/apps/details?id=com.indianstudygroup"
                )
            }
        }

    }

    private fun dialogBox(msg: String, string: String) {
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
            intent.data = string.toUri()
            startActivity(intent)
        }
        alert.show()
    }
}