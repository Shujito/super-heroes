package org.shujito.evaluation.heroes.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.shujito.evaluation.model.Hero
import org.shujito.evaluation.network.SuperHeroApi

class SuperHeroDetailViewModel : ViewModel() {
	val superHeroDetail = MutableLiveData<Hero>()
	fun getHero(id: String) {
		viewModelScope.launch {
			superHeroDetail.value = SuperHeroApi.instance.getHeroById(id)
		}
	}
}
