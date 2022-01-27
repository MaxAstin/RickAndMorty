package com.bunbeauty.rickandmorty.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bunbeauty.rickandmorty.R
import com.bunbeauty.rickandmorty.databinding.ActivityCaharacterListBinding
import com.bunbeauty.rickandmorty.state.State
import com.bunbeauty.rickandmorty.ui.CharacterAdapter
import com.bunbeauty.rickandmorty.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCaharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityCharacterListRvMain.adapter = characterAdapter
        mainViewModel.characterItemListState.launchWhenStarted { state ->
            when (state) {
                is State.Loading -> {
                    binding.activityCharacterListPbLoading.visibility = View.VISIBLE
                    binding.activityCharacterListRvMain.visibility = View.GONE
                    binding.activityCharacterListTvError.visibility = View.GONE
                }
                is State.Error -> {
                    binding.activityCharacterListPbLoading.visibility = View.GONE
                    binding.activityCharacterListRvMain.visibility = View.GONE
                    binding.activityCharacterListTvError.visibility = View.VISIBLE
                    binding.activityCharacterListTvError.text = state.message
                }
                is State.Success -> {
                    binding.activityCharacterListPbLoading.visibility = View.GONE
                    binding.activityCharacterListRvMain.visibility = View.VISIBLE
                    binding.activityCharacterListTvError.visibility = View.GONE
                    characterAdapter.submitList(state.data)
                }
            }
        }
    }
}