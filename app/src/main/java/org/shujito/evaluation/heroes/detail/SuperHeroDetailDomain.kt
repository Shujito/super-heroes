package org.shujito.evaluation.heroes.detail

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.shujito.evaluation.model.Hero

/**
 * Handles ViewModel to UI logic.
 */
class SuperHeroDetailDomain(
	owner: Fragment,
	callback: (hero: Hero) -> Unit = {},
	error: (error: String) -> Unit = {}
) {
	private val superHeroDetailViewModel by lazy {
		ViewModelProvider(owner)[SuperHeroDetailViewModel::class.java]
	}

	init {
		this.superHeroDetailViewModel.superHeroDetail.observe(owner.requireActivity()) {
			callback.invoke(it)
		}
		this.superHeroDetailViewModel.error.observe(owner.requireActivity()) {
			error.invoke(it)
		}
	}

	fun getHero(id: String) = this.superHeroDetailViewModel.getHero(id)
}
