package com.wcc.whatdidilearn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wcc.whatdidilearn.data.DatabaseItems
import com.wcc.whatdidilearn.databinding.ActivityMainBinding
import com.wcc.whatdidilearn.viewmodel.LearnedItemViewModel
import com.wcc.whatdidilearn.viewmodel.LearnedItemViewModelFactory
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
        val viewModelFactory = LearnedItemViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LearnedItemViewModel::class.java)
        val itemsList = viewModel.learnedItemsList
        itemsList.observe(this, Observer { items ->
            adapter.data = items
        })
    }
}