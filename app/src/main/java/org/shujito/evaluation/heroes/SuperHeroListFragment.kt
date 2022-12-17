package org.shujito.evaluation.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.shujito.evaluation.databinding.SuperHeroListFragmentBinding

class SuperHeroListFragment : Fragment() {
	private lateinit var binding: SuperHeroListFragmentBinding
	private val superHeroDomain by lazy {
		SuperHeroDomain(
			owner = this,
			error = {
				this.binding.error = it
			}
		)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		this.binding = SuperHeroListFragmentBinding.inflate(inflater)
		return this.binding.getRoot()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		this.binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
		this.binding.recyclerView.adapter = this.superHeroDomain.superHeroAdapter
		this.binding.retry.setOnClickListener { this.superHeroDomain.getAllHeroes() }
	}

	override fun onResume() {
		super.onResume()
		this.superHeroDomain.getAllHeroes()
	}
}
