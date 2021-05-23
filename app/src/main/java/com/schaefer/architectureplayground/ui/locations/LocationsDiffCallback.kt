package com.schaefer.architectureplayground.ui.locations

import androidx.recyclerview.widget.DiffUtil
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location

class LocationsDiffCallback(
    private val oldList: List<Location>,
    private val newList: List<Location>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}