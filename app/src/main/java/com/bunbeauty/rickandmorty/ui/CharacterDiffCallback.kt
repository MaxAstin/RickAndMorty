package com.bunbeauty.rickandmorty.ui

import androidx.recyclerview.widget.DiffUtil
import com.bunbeauty.rickandmorty.ui.item.CharacterItem
import javax.inject.Inject

class CharacterDiffCallback @Inject constructor() : DiffUtil.ItemCallback<CharacterItem>() {

    override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem == newItem
    }
}