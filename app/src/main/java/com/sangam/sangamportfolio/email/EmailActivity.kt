package com.sangam.sangamportfolio.email

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.app_utils.HideKeyboard
import com.sangam.sangamportfolio.app_utils.ToastUtil
import com.sangam.sangamportfolio.databinding.ActivityEmailBinding
import com.sangam.sangamportfolio.databinding.ErrorBottomDialogLayoutBinding

class EmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailBinding
    private lateinit var emailViewModel: EmailViewModel
    private val emailPattern =
        "[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com|outlook\\.com|hotmail\\.com|icloud\\.com|aol\\.com|protonmail\\.com|zoho\\.com|mail\\.com|gmx\\.com|yandex\\.com)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailViewModel = ViewModelProvider(this)[EmailViewModel::class.java]
        binding = ActivityEmailBinding.inflate(layoutInflater)
        window.statusBarColor = Color.WHITE
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }
        binding.gmailSentMail.setOnClickListener {
            sendEmailIntent()
        }


        binding.continueButton.setOnClickListener {
            val nameText = binding.nameEt.text.toString()
            val senderEmail = binding.mailEt.text.toString()
            val messageText = binding.messageEt.text.toString()

            if (nameText.trim().isEmpty()) {
                binding.nameEt.error = "Empty Field"
            } else if (senderEmail.trim().isEmpty()) {
                binding.mailEt.error = "Empty Field"
            } else if (!senderEmail.matches(emailPattern.toRegex())) {
                binding.mailEt.error = "Enter Valid Email"
            } else if (messageText.trim().isEmpty()) {
                binding.messageEt.error = "Empty Field"
            } else {
                val text = """
                    Name        : $nameText
                    Sender Mail : $senderEmail
                    Message     : $messageText
                """.trimIndent()



                HideKeyboard.hideKeyboard(this, binding.messageEt.windowToken)
                HideKeyboard.hideKeyboard(this, binding.mailEt.windowToken)
                HideKeyboard.hideKeyboard(this, binding.nameEt.windowToken)

                callSendEmail(
                    EmailRequestModel(
                        "Portfolio Email Mobile",
                        From("sangam.portfolio@demomailtrap.com", "Portfolio Mobile Sangam Garg"),
                        nameText,
                        text,
                        listOf(To("sangamgarg17@gmail.com"))
                    )
                )
            }
        }

        observeProgress()
        observerEmailApiResponse()
        observerErrorMessageApiResponse()

    }

    private fun sendEmailIntent() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("sangamgarg17@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
            putExtra(Intent.EXTRA_TEXT, "Body Here")
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun callSendEmail(emailRequestModel: EmailRequestModel) {
        emailViewModel.postEmail(emailRequestModel)
    }

    private fun observeProgress() {
        emailViewModel.showProgress.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.mainView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.mainView.visibility = View.VISIBLE
            }
        })
    }

    private fun observerErrorMessageApiResponse() {
        emailViewModel.errorMessage.observe(this, Observer {
            ToastUtil.makeToast(this, it)
        })
    }


    private fun observerEmailApiResponse() {
        emailViewModel.emailResponse.observe(this, Observer {
            showErrorBottomDialog(
                "Thank you for reaching me.\nI have received your mail."
            )
        })
    }

    private fun showErrorBottomDialog(message: String) {
        val bottomDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val dialogBinding = ErrorBottomDialogLayoutBinding.inflate(layoutInflater)
        bottomDialog.setContentView(dialogBinding.root)
        bottomDialog.setCancelable(false)
        bottomDialog.show()
        dialogBinding.messageTv.text = message
        dialogBinding.continueButton.setOnClickListener {
            bottomDialog.dismiss()
            finish()
        }
    }

}