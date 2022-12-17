package org.shujito.evaluation

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApi {
	data class ApiResponse(
		@Expose val response: String,
		@Expose val results: List<Hero>
	)

	@GET("search/_")
	suspend fun getAllHeroes(): ApiResponse

	@GET("{id}")
	suspend fun getHeroById(@Path("id") id: String): Hero

	companion object {
		private val gson: Gson = GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
			.create()
		val instance: SuperHeroApi = Retrofit.Builder()
			.baseUrl("https://superheroapi.com/api.php/4596205637089738/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
			.create(SuperHeroApi::class.java)
	}
}
