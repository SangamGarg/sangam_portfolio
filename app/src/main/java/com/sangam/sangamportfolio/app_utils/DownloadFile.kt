package com.sangam.sangamportfolio.app_utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object DownloadFile {

    fun downloadFile(context: Context, fileResId: Int, fileName: String) {
        val inputStream: InputStream = context.resources.openRawResource(fileResId)

        // Get the public Downloads directory
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs()
        }

        val file = File(downloadsDir, fileName)
        val outputStream = FileOutputStream(file)

        val buffer = ByteArray(1024)
        var length: Int

        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }

        outputStream.close()
        inputStream.close()

        Toast.makeText(context, "Downloaded to: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    }
}
