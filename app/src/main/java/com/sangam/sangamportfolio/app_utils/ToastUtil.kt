package com.sangam.sangamportfolio.app_utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
//    fun makeToast2(
//        context: Context, message: String, layoutInflater: LayoutInflater, parentView: ViewGroup
//    ) {
//        val layout: View = layoutInflater.inflate(R.layout.custom_toast_layout, parentView, false)
//        val toastMessage: TextView = layout.findViewById(R.id.custom_toast_text)
//        toastMessage.text = message
//        val toast = Toast(context)
//        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 100)
//        toast.duration = Toast.LENGTH_SHORT
//        toast.view = layout
//        toast.show()
//    }

    fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}