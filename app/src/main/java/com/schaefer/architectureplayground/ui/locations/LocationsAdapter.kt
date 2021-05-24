package com.schaefer.architectureplayground.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.schaefer.architectureplayground.databinding.ItemTileTextBinding
import com.schaefer.architectureplayground.model.Location

class LocationsAdapter(diffUtil: DiffUtil.ItemCallback<Location>) :
    PagingDataAdapter<Location, LocationsAdapter.TileViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val binding = ItemTileTextBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.tvItemTileTitle.text = this?.name

                holder.itemView.setOnClickListener {
                    // TODO
                }
            }
        }
    }

    inner class TileViewHolder(val binding: ItemTileTextBinding) :
        RecyclerView.ViewHolder(binding.root)

}