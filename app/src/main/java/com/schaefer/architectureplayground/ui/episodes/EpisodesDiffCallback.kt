package com.schaefer.architectureplayground.ui.episodes

import androidx.recyclerview.widget.DiffUtil
import com.schaefer.architectureplayground.model.Episode

class EpisodesDiffCallback(
    private val oldList: List<Episode>,
    private val newList: List<Episode>
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