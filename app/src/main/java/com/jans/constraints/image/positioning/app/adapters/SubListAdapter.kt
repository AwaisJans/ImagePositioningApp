package com.jans.constraints.image.positioning.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jans.constraints.image.positioning.app.R
import com.jans.constraints.image.positioning.app.adapters.viewHolders.SubListViewHolder

class SubListAdapter(private val childList: List<String>) :
        RecyclerView.Adapter<SubListViewHolder>() {



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.child_row, parent, false)
            return SubListViewHolder(view)
        }

        override fun onBindViewHolder(holder: SubListViewHolder, position: Int) {
            holder.title.text = childList[position]
        }

        override fun getItemCount(): Int {
            return childList.size
        }
    }
