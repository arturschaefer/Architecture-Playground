package com.schaefer.architectureplayground.ui.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.schaefer.architectureplayground.databinding.ItemTileTextBinding
import com.schaefer.architectureplayground.model.Episode

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.TileViewHolder>() {

    var episodeList = emptyList<Episode>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                EpisodesDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val binding = ItemTileTextBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TileViewHolder(binding)
    }

    override fun getItemCount() = episodeList.size

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        with(holder) {
            with(episodeList[position]) {
                binding.tvItemTileTitle.text = this.name

                holder.itemView.setOnClickListener {
                    // TODO
                }
            }
        }
    }

    inner class TileViewHolder(val binding: ItemTileTextBinding) :
        RecyclerView.ViewHolder(binding.root)

}