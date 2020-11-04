package com.wcc.whatdidilearn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wcc.whatdidilearn.data.DatabaseItems
import com.wcc.whatdidilearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycleView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()

        recycleView.adapter = adapter

        val learnedItemsList = DatabaseItems.getAll()
            adapter.data = learnedItemsList
    }
}