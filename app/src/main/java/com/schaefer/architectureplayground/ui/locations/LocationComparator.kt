package com.schaefer.architectureplayground.ui.locations

import androidx.recyclerview.widget.DiffUtil
import com.schaefer.architectureplayground.model.Location

object LocationComparator : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}