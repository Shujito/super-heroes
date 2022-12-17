package org.shujito.evaluation

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

data class Hero(
	@Expose val id: String,
	@Expose val response: String,
	@Expose val name: String,
	@Expose val powerstats: PowerStats,
	@Expose val biography: Biography,
	@Expose val appearance: Appearance,
	@Expose val work: Work,
	@Expose val connections: Connections,
	@Expose val image: Image,
) {
	object DataBindingAdapter {
		@JvmStatic
		@BindingAdapter("srcCompat", requireAll = false)
		fun setImageByUrl(imageView: ImageView, url: String?) = imageView.post {
			Picasso.get()
				.load(url)
				.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
				.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
				.placeholder(android.R.mipmap.sym_def_app_icon)
				.error(android.R.drawable.ic_delete)
				.centerCrop()
				.transform(PicassoCircleTransformation())
				.resize(imageView.width, imageView.height)
				.into(imageView)
		}
	}

	data class PowerStats(
		@Expose val intelligence: String,
		@Expose val strength: String,
		@Expose val speed: String,
		@Expose val durability: String,
		@Expose val power: String,
		@Expose val combat: String,
	)

	data class Biography(
		@Expose val fullName: String,
		@Expose val alterEgos: String,
		@Expose val aliases: List<String>,
		@Expose val placeOfBirth: String,
		@Expose val firstAppearance: String,
		@Expose val publisher: String,
		@Expose val alignment: Alignment,
	)

	enum class Alignment(
		val description: String,
		val color: Int
	) {
		@SerializedName("good")
		GOOD(description = "Good", color = Color.GREEN),

		@SerializedName("bad")
		BAD(description = "Bad", color = Color.RED),

		@SerializedName("neutral")
		NEUTRAL(description = "Neutral", color = Color.YELLOW),

		@SerializedName("-")
		NA(description = "N/A", color = Color.GRAY)
	}

	data class Appearance(
		@Expose val gender: String,
		@Expose val race: String,
		@Expose val height: List<String>,
		@Expose val weight: List<String>,
		@Expose val eyeColor: String,
		@Expose val hairColor: String,
	) {
		fun metricHeight() = height.find { it.lowercase().endsWith("cm") } ?: "Unknown"
		fun metricWeight() = weight.find { it.lowercase().endsWith("kg") } ?: "Unknown"
	}

	data class Work(
		@Expose val occupation: String,
		@Expose val base: String,
	)

	data class Connections(
		@Expose val groupAffiliation: String,
		@Expose val relatives: String,
	)

	data class Image(
		@Expose val url: String,
	)
}
