package com.jans.constraints.image.positioning.app.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jans.constraints.image.positioning.app.R

class R2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }