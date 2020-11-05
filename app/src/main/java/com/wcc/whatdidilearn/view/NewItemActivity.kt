package com.wcc.whatdidilearn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wcc.whatdidilearn.R
import com.wcc.whatdidilearn.databinding.ActivityMainBinding

class NewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "New Learned Item"


    }
}