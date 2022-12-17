package org.shujito.evaluation

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SuperHeroApiTests {
	@Test
	fun getAllHeroesTest() = runBlocking {
		val response = SuperHeroApi.instance.getAllHeroes()
		println("${response.results.size} heroes")
		println("first = ${response.results.first().name}")
		println("last = ${response.results.last().name}")
		response.results.forEach {
			assertNotNull(it)
		}
	}

	@Test
	fun getOneHeroTest() = runBlocking {
		val response = SuperHeroApi.instance.getHeroById("1")
		println("response = ${response.name}")
		assertNotNull(response)
	}
}
