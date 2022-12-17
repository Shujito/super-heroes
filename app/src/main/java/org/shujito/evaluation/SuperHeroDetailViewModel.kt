package org.shujito.evaluation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SuperHeroDetailViewModel : ViewModel() {
	val superHeroDetail = MutableLiveData<Hero>()
	fun getHero(id: String) {
		viewModelScope.launch {
			superHeroDetail.value = SuperHeroApi.instance.getHeroById(id)
		}
	}
}
