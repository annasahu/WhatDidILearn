package com.wcc.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcc.whatdidilearn.data.LearnedItemDao
import com.wcc.whatdidilearn.entities.LearnedItem
import com.wcc.whatdidilearn.entities.Level
import com.wcc.whatdidilearn.repository.LearnedItemsRepository
import kotlinx.coroutines.launch

class LearnedItemViewModel(private var repository: LearnedItemsRepository): ViewModel() {
    val learnedItems: LiveData<List<LearnedItem>>

    init {
        learnedItems = repository.learnedItems
    }

    fun insertNewLearnedItem(item: LearnedItem) {
        viewModelScope.launch {
            repository.insertNewLearnedItem(item)
        }
    }

}