package org.shujito.evaluation.heroes

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * Handles ViewModel logic to UI
 */
class SuperHeroDomain(
	owner: Fragment,
	error: (error: String) -> Unit = {},
	val listLoadingCallback: (loading: Boolean) -> Unit = {}
) {
	private val superHeroListViewModel by lazy {
		ViewModelProvider(owner)[SuperHeroListViewModel::class.java]
	}
	val superHeroAdapter = SuperHeroAdapter(loadingCallback = listLoadingCallback)

	init {
		this.superHeroListViewModel.superHeores.observe(owner.requireActivity()) {
			this.superHeroAdapter.submitList(it.results)
			this.listLoadingCallback(false)
		}
		this.superHeroListViewModel.error.observe(owner.requireActivity()) {
			error.invoke(it)
			this.listLoadingCallback(false)
		}
	}

	fun getAllHeroes() {
		this.listLoadingCallback(true)
		this.superHeroListViewModel.getAllHeroes()
	}
}
