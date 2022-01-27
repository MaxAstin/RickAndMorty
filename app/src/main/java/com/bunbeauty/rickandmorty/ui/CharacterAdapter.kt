package com.bunbeauty.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bunbeauty.rickandmorty.R
import com.bunbeauty.rickandmorty.databinding.ItemCharacterBinding
import com.bunbeauty.rickandmorty.ui.item.CharacterItem
import javax.inject.Inject

class CharacterAdapter @Inject constructor(characterDiffCallback: CharacterDiffCallback) :
    ListAdapter<CharacterItem, CharacterAdapter.CharacterViewHolder>(characterDiffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemCharacterBinding.inflate(inflater, viewGroup, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CharacterViewHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {

        fun bind(characterItem: CharacterItem) {
            itemCharacterBinding.itemCharacterTvName.text = characterItem.name
            itemCharacterBinding.itemCharacterIvPhoto.load(characterItem.photoLink) {
                placeholder(R.drawable.placeholder)
                val a = itemView.width
                println(a)
            }
        }
    }
}