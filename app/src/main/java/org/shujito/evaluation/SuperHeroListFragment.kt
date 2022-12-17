package org.shujito.evaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.shujito.evaluation.databinding.SuperHeroListFragmentBinding

class SuperHeroListFragment : Fragment() {
	private lateinit var binding: SuperHeroListFragmentBinding
	private var superHeroAdapter = SuperHeroAdapter()
	private val superHeroListViewModel by lazy {
		ViewModelProvider(this)[SuperHeroListViewModel::class.java]
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		this.binding = DataBindingUtil
			.inflate(inflater, R.layout.super_hero_list_fragment, container, false)
		return this.binding.getRoot()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		this.binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
		this.binding.recyclerView.adapter = this.superHeroAdapter
		this.superHeroListViewModel.superHeores.observe(this.requireActivity()) {
			this.superHeroAdapter.submitList(it.results)
		}
	}

	override fun onResume() {
		super.onResume()
		this.superHeroListViewModel.getAllHeroes()
	}
}
