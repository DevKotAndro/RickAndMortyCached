package com.shoshin.rickandmortycached.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shoshin.domain.entities.CharacterDomain
import com.shoshin.rickandmortycached.R
import com.shoshin.rickandmortycached.common.simpleScan
import com.shoshin.rickandmortycached.databinding.CharactersFragmentBinding
import com.shoshin.rickandmortycached.ui.characters.adapters.CharacterAdapter
import com.shoshin.rickandmortycached.ui.characters.adapters.DefaultLoadStateAdapter
import com.shoshin.rickandmortycached.ui.characters.holders.LoadStateHolder
import com.shoshin.rickandmortycached.ui.characters.holders.TryAgainAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class CharactersFragment: Fragment(R.layout.characters_fragment) {
    private val binding by viewBinding(CharactersFragmentBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()
    private var adapter = CharacterAdapter(::onCharacterClick)
    private var mainLoadStateHolder: LoadStateHolder? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainLoadStateHolder = LoadStateHolder(
            binding.loadStateView,
            adapter::retry,
            binding.swipeRefreshLayout
        )
        setupCharacters()
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun setupCharacters() {
        val tryAgainAction: TryAgainAction = adapter::retry
        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)
        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.reviewsRecyclerView.adapter = adapterWithLoadState

        observeLoadState()
        handleListVisibility()
        observeCharacters()
    }

    private fun observeCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersFlow.collect { pagingData ->
//                Log.e("pagingData", "pagingData=$pagingData")
                adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }
    }

    private fun observeLoadState() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
//                mainLoadStateHolder?.bind(state.refresh)
            }
        }
//        adapter.addLoadStateListener {
//
//        }
    }

    private fun handleListVisibility() = lifecycleScope.launch {

        adapter.loadStateFlow.map { it.mediator?.refresh }
            .simpleScan(count = 3)
            .collectLatest { (beforePrevious, previous, current) ->
//                binding.reviewsRecyclerView.isInvisible = current is LoadState.Error
//                        || previous is LoadState.Error
//                        || (beforePrevious is LoadState.Error && previous is LoadState.NotLoading
//                        && current is LoadState.Loading)
//                Log.e("state", "state=$current")
                binding.reviewsRecyclerView.isVisible = true
                current?.let {
                    mainLoadStateHolder?.bind(current)
                }



//                            val refreshState =
//                                if (isMed   iator) {
//                                    loadState.mediator?.refresh
//                                } else {
//                                    loadState.source.refresh
//                                }
                //            binding.recyclerView.isVisible = refreshState is LoadState.NotLoading
                //            binding.progressBar.isVisible = refreshState is LoadState.Loading
                //            binding.buttonRetry.isVisible = refreshState is LoadState.Error
                //            handleError(loadState)
            }
    }

    private fun onCharacterClick(character: CharacterDomain) {
//        if(character.id != null) {
//            val directions = CharactersFragmentDirections.toCharacter(character.id!!)
//            findNavController().navigate(directions)
//        }
    }
}