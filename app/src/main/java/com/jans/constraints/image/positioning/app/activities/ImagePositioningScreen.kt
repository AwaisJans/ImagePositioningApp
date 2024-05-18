package com.jans.constraints.image.positioning.app.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.GONE
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.jans.constraints.image.positioning.app.R
import com.jans.constraints.image.positioning.app.adapters.DashboardAdapter
import com.jans.constraints.image.positioning.app.databinding.ActivityImagePositioningBinding
import com.jans.constraints.image.positioning.app.model.DashboardModel
import com.jans.constraints.image.positioning.app.utils.ConfigApp.Companion.readJsonFile


class ImagePositioningScreen : AppCompatActivity() {

    private lateinit var b: ActivityImagePositioningBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityImagePositioningBinding.inflate(layoutInflater)
        setContentView(b.root)

        setUpDashboardRV()
        buttonsInit()


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
