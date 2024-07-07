package com.sangam.sangamportfolio.CV

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sangam.sangamportfolio.R
import com.sangam.sangamportfolio.RawResourcesBitmapProvider
import com.shevelev.page_turning_lib.page_curling.CurlView
import com.shevelev.page_turning_lib.page_curling.CurlViewEventsHandler
import com.shevelev.page_turning_lib.page_curling.textures_manager.PageLoadingEventsHandler
import com.shevelev.page_turning_lib.user_actions_managing.Area


class CVActivity : AppCompatActivity() {
    private var curlView: CurlView? = null

    private var currentPageIndex: Int? = null
    lateinit var id1: String

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_cvactivity)
        val textView = findViewById<TextView>(R.id.textViewCR)
        currentPageIndex =
            savedInstanceState?.getInt(CURRENT_PAGE) ?: intent.getIntExtra(START_PAGE, 0)
        id1 = intent.getStringExtra("ID") ?: ""

        if (id1 == "0") {
            textView.text = "CV"
        } else if (id1 == "1") {
            textView.text = "RESUME"
        } else {
            textView.text = "SKETCHES"
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
                        this@CVActivity,
                        "We've moved to the page with index 2",
                        Toast.LENGTH_SHORT
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
            val intent = Intent(parentActivity, CVActivity::class.java)
                .putExtra(START_PAGE, startPage)
                .putExtra("ID", id) // Pass the 'id' parameter as an extra
            parentActivity.startActivity(intent)
        }
    }
}