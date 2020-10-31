package com.wcc.whatdidilearn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.wcc.whatdidilearn.R
import com.wcc.whatdidilearn.data.DataBaseItems

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView = findViewById<RecyclerView>(R.id.items_recycler_view)
        val adapter = LearnedItemsAdapter()

        recycleView.adapter = adapter

        val learnedItemsList = DataBaseItems().getAll()
            adapter.data = learnedItemsList
    }
}