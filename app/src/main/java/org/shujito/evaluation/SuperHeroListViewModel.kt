package org.shujito.evaluation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SuperHeroListViewModel : ViewModel() {
	val superHeores = MutableLiveData<SuperHeroApi.ApiResponse>()
	fun getAllHeroes() {
		viewModelScope.launch {
			superHeores.value = SuperHeroApi.instance.getAllHeroes()
		}
	}
}
