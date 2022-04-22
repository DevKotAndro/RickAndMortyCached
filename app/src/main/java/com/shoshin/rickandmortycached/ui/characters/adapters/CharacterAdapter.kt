package com.shoshin.rickandmortycached.ui.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.shoshin.domain.entities.CharacterDomain
import com.shoshin.rickandmortycached.databinding.CharacterHolderBinding
import com.shoshin.rickandmortycached.ui.characters.holders.CharacterHolder

class CharacterAdapter(
    private val onCharacterClick: (character: CharacterDomain) -> Unit = {}
): PagingDataAdapter<CharacterDomain, CharacterHolder>(CharacterDiffCallback()) {
    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position) ?: return
        holder.bind(character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterHolderBinding.inflate(inflater, parent, false)
        return CharacterHolder(binding, onCharacterClick)
    }
}
