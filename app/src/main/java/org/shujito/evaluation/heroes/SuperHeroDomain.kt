package org.shujito.evaluation.heroes

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * Handles ViewModel logic to UI
 */
class SuperHeroDomain(
	owner: Fragment,
	error: (error: String) -> Unit = {}
) {
	private val superHeroListViewModel by lazy {
		ViewModelProvider(owner)[SuperHeroListViewModel::class.java]
	}
	val superHeroAdapter = SuperHeroAdapter()

	init {
		this.superHeroListViewModel.superHeores.observe(owner.requireActivity()) {
			this.superHeroAdapter.submitList(it.results)
		}
		this.superHeroListViewModel.error.observe(owner.requireActivity()) {
			error.invoke(it)
		}
	}

	fun getAllHeroes() = this.superHeroListViewModel.getAllHeroes()
}
