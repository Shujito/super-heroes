package org.shujito.evaluation.heroes.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.shujito.evaluation.model.Hero
import org.shujito.evaluation.network.SuperHeroApi

/**
 * Handles fetching super hero list data
 */
class SuperHeroDetailViewModel : ViewModel() {
	val superHeroDetail = MutableLiveData<Hero>()
	val error = MutableLiveData<String>()
	fun getHero(id: String) {
		viewModelScope.launch {
			try {
				superHeroDetail.value = SuperHeroApi.instance.getHeroById(id)
			} catch (exception: Exception) {
				error.value = "whoops: ${exception.message}"
			}
		}
	}
}
