package org.shujito.evaluation.heroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min
import org.shujito.evaluation.R
import org.shujito.evaluation.databinding.SuperHeroItemViewBinding
import org.shujito.evaluation.model.Hero

class SuperHeroAdapter(val loadingCallback: (loading: Boolean) -> Unit = {}) :
	ListAdapter<Hero, SuperHeroAdapter.SuperHeroViewHolder>(HeroDiff()) {
	private var lastItem = 15
	private var loading = false

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
		return min(super.getItemCount(), lastItem)
	}

	override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
		val hero = this.getItem(position)
		holder.bind(hero)
		// simulate infinite scrolling
		// TODO: should load more items instead of just increasing the list count
		if (!this.loading && position == this.itemCount - 1) {
			this.loading = true
			this.loadingCallback(true)
			holder.itemView.postDelayed({
				this.lastItem += 15
				this.loading = false
				this.loadingCallback(false)
				this.notifyItemRangeChanged(position + 1, this.itemCount)
			}, 3000)
		}
	}
}
