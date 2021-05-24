package com.schaefer.architectureplayground.ui.episodes

import androidx.recyclerview.widget.DiffUtil
import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode

object EpisodeComparator : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}