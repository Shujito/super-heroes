package org.shujito.evaluation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min
import org.shujito.evaluation.databinding.SuperHeroItemViewBinding

class SuperHeroAdapter :
	ListAdapter<Hero, SuperHeroAdapter.SuperHeroViewHolder>(HeroDiff()) {
	class SuperHeroViewHolder(
		parent: View,
		private val binding: SuperHeroItemViewBinding = SuperHeroItemViewBinding.inflate(
			LayoutInflater.from(parent.context)
		),
	) : RecyclerView.ViewHolder(binding.root) {
		init {
			this.binding.card.setOnClickListener {
				it.findNavController().navigate(
					R.id.action_start_to_detail,
					bundleOf("id" to this.binding.hero?.id)
				)
			}
		}

		fun bind(hero: Hero) {
			this.binding.hero = hero
		}
	}

	class HeroDiff : ItemCallback<Hero>() {
		override fun areItemsTheSame(old: Hero, new: Hero): Boolean {
			return old == new
		}

		override fun areContentsTheSame(
			old: Hero,
			new: Hero
		): Boolean {
			return old.id == new.id
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
		return SuperHeroViewHolder(parent)
	}

	override fun getItemCount(): Int {
		return min(super.getItemCount(), 15)
	}

	override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
		val hero = this.getItem(position)
		holder.bind(hero)
	}
}
