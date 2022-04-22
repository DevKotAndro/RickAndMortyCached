package com.shoshin.rickandmortycached.ui.characters.holders

import android.util.Log
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shoshin.rickandmortycached.databinding.LoadStateHolderBinding

typealias TryAgainAction = () -> Unit

class LoadStateHolder(
    private val binding: LoadStateHolderBinding,
    private val tryAgainAction: TryAgainAction,
    private val swipeRefreshLayout: SwipeRefreshLayout? = null,
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.tryAgainButton.setOnClickListener { tryAgainAction() }
    }

    fun bind(loadState: LoadState) = with(binding) {
        messageTextView.isVisible = loadState is LoadState.Error
        tryAgainButton.isVisible = loadState is LoadState.Error
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
            progressBar.isVisible = false
        } else {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}