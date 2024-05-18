package com.jans.constraints.image.positioning.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jans.constraints.image.positioning.app.databinding.ActivityFirstBinding

class FirstScreen : AppCompatActivity() {

    private lateinit var b: ActivityFirstBinding

    // Initial Branch - Saturday, 18th May 2024 - New Project
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.btnCalendarScreen.setOnClickListener {
            startActivity(Intent(this, ImagePositioningScreen::class.java))
        }

        b.practiseScreenBtn.setOnClickListener {

        }
    }
}


