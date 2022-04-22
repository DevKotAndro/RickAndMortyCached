package com.shoshin.rickandmortycached.ui.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.shoshin.rickandmortycached.databinding.LoadStateHolderBinding
import com.shoshin.rickandmortycached.ui.characters.holders.LoadStateHolder
import com.shoshin.rickandmortycached.ui.characters.holders.TryAgainAction

class DefaultLoadStateAdapter (
    private val tryAgainAction: TryAgainAction
): LoadStateAdapter<LoadStateHolder>() {
    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LoadStateHolderBinding.inflate(inflater, parent, false)
        return LoadStateHolder(binding, tryAgainAction)
    }
}