package com.schaefer.architectureplayground.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.schaefer.architectureplayground.databinding.ItemCharacterBinding
import com.schaefer.architectureplayground.model.Character

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    var characterList = emptyList<Character>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                CharactersDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        with(holder) {
            with(characterList[position]) {
                binding.tvCharacterName.text = this.name

                Glide.with(holder.itemView.context)
                    .load(this.image)
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