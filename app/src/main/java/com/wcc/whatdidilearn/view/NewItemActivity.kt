package com.wcc.whatdidilearn.view

import android.content.Intent
import android.opengl.ETC1.isValid
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import com.wcc.whatdidilearn.data.DatabaseItems
import com.wcc.whatdidilearn.databinding.ActivityNewItemBinding
import com.wcc.whatdidilearn.repository.LearnedItemsRepository
import com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModel
import com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class NewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewItemBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.title = "New Learned Item"

        val dao = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO)).learnedItemDao()
        val repository = LearnedItemsRepository(dao)
        val viewModelFactory = NewLearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewLearnedItemViewModel::class.java)

        binding.saveButton.setOnClickListener {
            val title = (binding.boxItemTitle.text.toString())
            val description = (binding.boxItemDescription.text.toString())

            viewModel.insertNewLearnedItem(title, description)

            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}