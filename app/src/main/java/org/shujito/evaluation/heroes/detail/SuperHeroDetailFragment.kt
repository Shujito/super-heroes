package org.shujito.evaluation.heroes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.shujito.evaluation.databinding.SuperHeroDetailFragmentBinding

class SuperHeroDetailFragment : Fragment() {
	private lateinit var binding: SuperHeroDetailFragmentBinding
	private val superHeroDetailDomain by lazy {
		SuperHeroDetailDomain(
			owner = this,
			callback = { this.binding.hero = it },
			error = { /* TODO: handle error message */ }
		)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		this.binding = SuperHeroDetailFragmentBinding.inflate(inflater)
		return this.binding.getRoot()
	}

	override fun onResume() {
		super.onResume()
		this.superHeroDetailDomain.getHero(
			this.requireArguments().getString("id").toString()
		)
	}
}
