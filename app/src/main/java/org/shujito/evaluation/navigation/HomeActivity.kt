package org.shujito.evaluation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.shujito.evaluation.R
import org.shujito.evaluation.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {
	private lateinit var binding: HomeActivityBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		this.binding = DataBindingUtil.setContentView(this, R.layout.home_activity)
		this.setSupportActionBar(this.binding.toolbar)
	}
}
