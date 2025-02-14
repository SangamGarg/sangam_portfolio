package com.sangam.sangamportfolio.CV

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.RawResourcesBitmapProvider
import com.sangam.sangamportfolio.app_utils.DownloadFile
import com.sangam.sangamportfolio.app_utils.HideStatusBarUtil
import com.shevelev.page_turning_lib.page_curling.CurlView
import com.shevelev.page_turning_lib.page_curling.CurlViewEventsHandler
import com.shevelev.page_turning_lib.page_curling.textures_manager.PageLoadingEventsHandler
import com.shevelev.page_turning_lib.user_actions_managing.Area
import java.io.File


class CVActivity : AppCompatActivity() {
    private var curlView: CurlView? = null

    private var currentPageIndex: Int? = null
    private lateinit var id1: String


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HideStatusBarUtil.hideStatusBar(this)
        window.statusBarColor = Color.WHITE
        setContentView(R.layout.activity_cvactivity)
        val textView = findViewById<TextView>(R.id.textViewCR)
        val imageView = findViewById<ImageView>(R.id.downlaod)
        currentPageIndex =
            savedInstanceState?.getInt(CURRENT_PAGE) ?: intent.getIntExtra(START_PAGE, 0)
        id1 = intent.getStringExtra("ID") ?: ""

        when (id1) {
            "0" -> {
                textView.text = "CV"

                imageView.setOnClickListener {
                    downloadImage2(
                        this,
                        "https://firebasestorage.googleapis.com/v0/b/sangamportfolio-60a25.appspot.com/o/SANGAMCV.pdf?alt=media&token=33f3cf19-3a24-4aff-8129-688bf3f93929",
                        "Sangam CV"
                    )
                }

            }

            "1" -> {
                textView.text = "RESUME"

                imageView.setOnClickListener {

                    downloadImage(
                        this,
                        "https://firebasestorage.googleapis.com/v0/b/sangamportfolio-60a25.appspot.com/o/SangamResumeAndroid.pdf?alt=media&token=bc68000f-90da-4fa7-9b3e-39f4b74b00af",
                        "Sangam Resume"
                    )
                }
            }

            else -> {
                textView.text = "SKETCHES"
                imageView.visibility = View.GONE
            }
        }
        curlView = (findViewById<View>(R.id.pageCurl) as? CurlView)?.also {
            it.setBitmapProvider(RawResourcesBitmapProvider(this, id1))
            it.initCurrentPageIndex(currentPageIndex!!)
            it.setBackgroundColor(Color.WHITE)
            it.setHotAreas(
                listOf(
                    Area(
                        0,
                        com.shevelev.page_turning_lib.user_actions_managing.Point(0, 0),
                        Size(100, 100)
                    )
                )
            )

            it.setExternalEventsHandler(object : CurlViewEventsHandler {
                override fun onPageChanged(newPageIndex: Int) {
                    currentPageIndex = newPageIndex
                }

                override fun onHotAreaPressed(areaId: Int) {
                    it.setCurrentPageIndex(2)
                    Toast.makeText(
                        this@CVActivity, "We've moved to the page with index 2", Toast.LENGTH_SHORT
                    ).show()
                }
            })

            it.setOnPageLoadingListener(object : PageLoadingEventsHandler {
                override fun onLoadingStarted() {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }

                override fun onLoadingCompleted() {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }

                override fun onLoadingError() {
                    Toast.makeText(this@CVActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        }


        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
    }

    private fun downloadImage2(context: Context, imageUrl: String, fileName: String) {

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val request = DownloadManager.Request(Uri.parse(imageUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Sangam CV Download")
        request.setDescription("Downloading...")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS, File.separator + fileName + ".pdf"
        )

        downloadManager.enqueue(request)

        Toast.makeText(this, "CV Downloaded", Toast.LENGTH_SHORT).show()

    }


    private fun downloadImage(context: Context, imageUrl: String, fileName: String) {

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val request = DownloadManager.Request(Uri.parse(imageUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Sangam Resume Download")
        request.setDescription("Downloading...")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS, File.separator + fileName + ".pdf"
        )

        downloadManager.enqueue(request)

        Toast.makeText(this, "Resume Downloaded", Toast.LENGTH_SHORT).show()

    }


    public override fun onPause() {
        super.onPause()
        curlView!!.onPause()
    }

    public override fun onResume() {
        super.onResume()
        curlView!!.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_PAGE, currentPageIndex ?: 0)
    }

    companion object {
        private const val START_PAGE = "START_PAGE"
        private const val CURRENT_PAGE = "CURRENT_PAGE"

        fun start(parentActivity: Activity, startPage: Int, id: String) {
            val intent =
                Intent(parentActivity, CVActivity::class.java).putExtra(START_PAGE, startPage)
                    .putExtra("ID", id) // Pass the 'id' parameter as an extra
            parentActivity.startActivity(intent)
        }
    }
}