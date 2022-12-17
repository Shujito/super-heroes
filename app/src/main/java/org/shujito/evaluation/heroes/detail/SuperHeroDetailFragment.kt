package org.shujito.evaluation.heroes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.shujito.evaluation.databinding.SuperHeroDetailFragmentBinding

class SuperHeroDetailFragment : Fragment() {
	private lateinit var binding: SuperHeroDetailFragmentBinding
	private val superHeroDetailViewModel by lazy {
		ViewModelProvider(this)[SuperHeroDetailViewModel::class.java]
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		this.binding = SuperHeroDetailFragmentBinding.inflate(inflater)
		return this.binding.getRoot()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		this.superHeroDetailViewModel.superHeroDetail.observe(this.requireActivity()) {
			this.binding.hero = it
		}
	}

	private fun getHeroID(): String = this.requireArguments().getString("id").toString()
	override fun onResume() {
		super.onResume()
		val heroID: String = this.getHeroID()
		this.superHeroDetailViewModel.getHero(heroID)
	}
}
