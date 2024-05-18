package com.jans.constraints.image.positioning.app.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService

class ScreenUtil {

    companion object{









        fun calculateScreenHeightWidth(context: Context):List<Int>{
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val width: Int
            val height: Int
            if (SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics = wm.currentWindowMetrics
                val windowInsets: WindowInsets = windowMetrics.windowInsets

                val insets = windowInsets.getInsetsIgnoringVisibility(
                    WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout()
                )
                val insetsWidth = insets.right + insets.left
                val insetsHeight = insets.top + insets.bottom

                val b = windowMetrics.bounds
                width = b.width() - insetsWidth
                height = b.height() - insetsHeight
            } else {
                val size = Point()
                val display = wm.defaultDisplay // deprecated in API 30
                display?.getSize(size) // deprecated in API 30
                width = size.x
                height = size.y
            }

            return listOf(width,height)

        }



    }

}