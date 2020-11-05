package com.wcc.whatdidilearn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.Observer
import com.wcc.whatdidilearn.data.DatabaseItems
import com.wcc.whatdidilearn.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycleView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()

        recycleView.adapter = adapter

        val database = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val dao = database.learnedItemDao()
        val itemsList = dao.getAll()
        itemsList.observe(this, Observer { items ->
            adapter.data = items
        })

        val fabButton = binding.fab
    }
}