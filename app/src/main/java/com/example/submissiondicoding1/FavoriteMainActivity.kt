package com.example.submissiondicoding1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submissiondicoding1.databinding.ActivityFavoriteMainBinding

class FavoriteMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}