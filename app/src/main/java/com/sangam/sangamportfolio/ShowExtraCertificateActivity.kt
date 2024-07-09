package com.sangam.sangamportfolio

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sangam.sangamportfolio.databinding.ActivityShowExtraCertificateBinding

class ShowExtraCertificateActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShowExtraCertificateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE

        binding.netball.setOnClickListener {
            dialogBox(
                "Our team achieved a notable accomplishment by securing the silver medal two times consecutively in district-level netball competitions, which featured a competitive field of ten participating teams. This consistent success underscores our dedication, teamwork and challenging competitive environment.",
                R.drawable.netball
            )
        }
        binding.movieMaking.setOnClickListener {
            dialogBox(
                "Our team of three secured first place in a college movie-making contest featuring over 20 teams. Our win highlights our creative teamwork and filmmaking prowess.",
                R.drawable.moviemaking
            )
        }
        binding.bioTech.setOnClickListener {
            dialogBox(
                "Our team of Two secured first place in a college over 20 teams. Our win highlights our creative teamwork and entrepreneurship prowess.",
                R.drawable.biotech
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