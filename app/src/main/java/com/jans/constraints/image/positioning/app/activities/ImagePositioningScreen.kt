package com.jans.constraints.image.positioning.app.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Constraints
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.jans.constraints.image.positioning.app.R
import com.jans.constraints.image.positioning.app.adapters.DashboardAdapter
import com.jans.constraints.image.positioning.app.databinding.ActivityImagePositioningBinding
import com.jans.constraints.image.positioning.app.model.DashboardModel
import com.jans.constraints.image.positioning.app.utils.JsonItemsSample
import com.jans.constraints.image.positioning.app.utils.JsonItemsSample.Companion.POSITION_IMAGE_LEFT
import com.jans.constraints.image.positioning.app.utils.JsonItemsSample.Companion.POSITION_IMAGE_RIGHT
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.getDrawableResourceId
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.readJsonFile


class ImagePositioningScreen : AppCompatActivity() {

    private lateinit var b: ActivityImagePositioningBinding

    private var screenHeight: Double = 0.0
    private var screenWidth: Double = 0.0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityImagePositioningBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Getting Screen Height and Screen Width
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        screenHeight = displayMetrics.heightPixels.toDouble()
        screenWidth = displayMetrics.widthPixels.toDouble()
        val widthAndHeightStr = "Screen Height: $screenHeight\nScreen Width: $screenWidth"
        b.tvDetails.text = widthAndHeightStr

        // declaring image view
        val imageView = b.imgTopLogo

        // reading image name from json
        imageView.setImageResource(getDrawableResourceId(this, JsonItemsSample.LOGO_IMAGE_NAME))

        // taking Logo Height & width
        val imageHeight = getLogoHeightOrWidth(JsonItemsSample.LOGO_HEIGHT).toInt()
        val imageWidth = getLogoHeightOrWidth(JsonItemsSample.LOGO_WIDTH).toInt()

        // assigning image height and width
        imageView.layoutParams.height = imageHeight
        imageView.layoutParams.width = imageWidth

        Log.d("task123","Calculated Height => $imageHeight")
        Log.d("task123","Calculated Width => $imageWidth")


        // Lets make a condition if we want to change the logo positions
        // if we want that the position should be left side of the view
        makeConstraintToHeaderLogo(imageView, ConstraintSet.START)


// Note:     if you want the right side then un comment below statement and comment above statement

        // but if we want that the position should be right side of the view
//         makeConstraintToHeaderLogo(imageView, ConstraintSet.END)

        setUpDashboardRV()
        buttonsInit()
    }

    private fun makeConstraintToHeaderLogo(imageView: ImageView, alignLeftOrRight:Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(b.lytConstraint)
        // make it positioned to left
        constraintSet.connect(imageView.id, alignLeftOrRight, ConstraintSet.PARENT_ID, alignLeftOrRight, 16)
        // make it center
        constraintSet.connect(imageView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        constraintSet.connect(imageView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        // apply the changes
        constraintSet.applyTo(b.lytConstraint)
    }

    private fun getLogoHeightOrWidth(stringForHeightOrWidth: String):Double {
        val parts = stringForHeightOrWidth.split(" ")
        val screenDimension = parts[0].trim()
        val operator = parts[1].trim()
        val value = parts[2].trim().toDoubleOrNull() ?: 1.0

        // final height from string
        return when (screenDimension) {
            "SCREENWIDTH" -> {
                when (operator) {
                    "+" -> screenWidth + value
                    "-" -> screenWidth - value
                    "*" -> screenWidth * value
                    "/" -> if (value != 0.0) screenWidth / value else screenWidth
                    else -> screenWidth // Default to original value if operator is not recognized
                }
            }

            else -> {
                when (operator) {
                    "+" -> screenHeight + value
                    "-" -> screenHeight - value
                    "*" -> screenHeight * value
                    "/" -> if (value != 0.0) screenHeight / value else screenHeight
                    else -> screenHeight // Default to original value if operator is not recognized
                }
            }
        }
    }

    private fun buttonsInit() {
        b.backBtn.setOnClickListener {
            finish()
        }

        // setup Nested Scroll View
        val nestedScrollView = b.nestedScrollView
        nestedScrollView.visibility = View.INVISIBLE

        // Setup Progress Bar
        val rvProgressBar = findViewById<LinearLayout>(R.id.rvProgressBar)
        Handler(Looper.getMainLooper()).postDelayed({
            nestedScrollView.visibility = View.VISIBLE
            rvProgressBar.visibility = GONE
        }, 50) // Delay in milliseconds

        // Setup bottom button
        b.btnBottom.setOnClickListener {
            nestedScrollView.fullScroll(View.FOCUS_DOWN)
        }

    }

    private fun setUpDashboardRV() {
        // populate the List
        val jsonString = readJsonFile(R.raw.dashboard_json)
        val dashboardResponse = Gson().fromJson(jsonString, DashboardModel::class.java)

        // setup RV
        val adapter = DashboardAdapter(dashboardResponse.dashboard)
        val dashboardRV = b.verticalRecyclerView
        val verticalLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        dashboardRV.layoutManager = verticalLayoutManager
        dashboardRV.adapter = adapter


    }

}
