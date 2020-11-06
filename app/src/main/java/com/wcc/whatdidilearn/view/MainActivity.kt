package com.wcc.whatdidilearn.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wcc.whatdidilearn.data.DatabaseItems
import com.wcc.whatdidilearn.databinding.ActivityMainBinding
import com.wcc.whatdidilearn.repository.LearnedItemsRepository
import com.wcc.whatdidilearn.viewmodel.LearnedItemViewModel
import com.wcc.whatdidilearn.viewmodel.LearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycleView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()
        recycleView.adapter = adapter

        val database = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val dao = database.learnedItemDao()
        val repository = LearnedItemsRepository(dao)

        val viewModelFactory = LearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LearnedItemViewModel::class.java)

        val learnedItems = viewModel.learnedItems
        learnedItems.observe(this, Observer {
            adapter.data = it
        })

        binding.addButton.setOnClickListener {
            val intent = Intent(this, NewItemActivity::class.java)
            startActivity(intent)
        }
    }

    //ADICIONAR BOTÃO DE EXCLUIR PARA CADA ITEM
}