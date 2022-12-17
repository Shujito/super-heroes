package org.shujito.evaluation.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.shujito.evaluation.network.SuperHeroApi

/**
 * Handles fetching single super hero data
 */
class SuperHeroListViewModel : ViewModel() {
	val superHeores = MutableLiveData<SuperHeroApi.ApiResponse>()
	val error = MutableLiveData("")
	fun getAllHeroes() {
		viewModelScope.launch {
			try {
				superHeores.value = SuperHeroApi.instance.getAllHeroes()
				error.value = ""
			} catch (exception: Exception) {
				error.value = "whoops: ${exception.message}"
			}
		}
	}
}
