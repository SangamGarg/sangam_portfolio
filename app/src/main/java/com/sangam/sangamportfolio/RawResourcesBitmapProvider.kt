package com.sangam.sangamportfolio

import android.content.Context
import com.shevelev.page_turning_lib.page_curling.textures_manager.repository.BitmapProvider
import java.io.InputStream

/**
 * Provides bitmaps from Raw resources of the app
 */
class RawResourcesBitmapProvider(private val context: Context, var id: String) : BitmapProvider {

    private var bitmapIds: List<Int> = ArrayList()

    init {
        // Conditionally set the bitmapIds based on the value of id
        if (id == "0") {
            bitmapIds = listOf(R.raw.cv1, R.raw.cv2, R.raw.cv3)
        } else if (id == "1") {
            bitmapIds = listOf(R.raw.sangamresume)
        } else {
            bitmapIds = listOf(
                R.raw.sketch1,
                R.raw.sketch2,
                R.raw.sketch3,
                R.raw.sketch4,
                R.raw.sketch5,
                R.raw.sketch6,
                R.raw.sketch7,
                R.raw.sketch8,
                R.raw.sketch9,
                R.raw.sketch10,
                R.raw.sketch11
            )
        }
    }

    /**
     * Total quantity of bitmap
     */
    override val total: Int
        get() = bitmapIds.size

    /**
     * Returns a stream of bitmap data for given bitmap index
     * The stream will be closed by caller
     */
    override fun getBitmapStream(index: Int): InputStream =
        context.resources.openRawResource(bitmapIds[index])
}