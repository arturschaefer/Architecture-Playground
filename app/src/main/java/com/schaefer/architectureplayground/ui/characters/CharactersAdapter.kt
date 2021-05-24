package com.schaefer.architectureplayground.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.schaefer.architectureplayground.databinding.ItemCharacterBinding
import com.schaefer.architectureplayground.model.Character

class CharactersAdapter(diffUtil: DiffUtil.ItemCallback<Character>) :
    PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.tvCharacterName.text = this?.name

                Glide.with(holder.itemView.context)
                    .load(this?.image)
                    .into(binding.ivCharacter)

                holder.itemView.setOnClickListener {
                    // TODO
                }
            }
        }
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

}