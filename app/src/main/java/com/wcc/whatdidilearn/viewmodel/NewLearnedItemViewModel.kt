package com.wcc.whatdidilearn.viewmodel

import android.text.TextUtils
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcc.whatdidilearn.data.LearnedItemDao
import com.wcc.whatdidilearn.entities.LearnedItem
import com.wcc.whatdidilearn.entities.Level
import com.wcc.whatdidilearn.repository.LearnedItemsRepository
import com.wcc.whatdidilearn.view.NewItemActivity
import kotlinx.coroutines.launch

class NewLearnedItemViewModel(private var repository: LearnedItemsRepository): ViewModel() {

    fun insertNewLearnedItem(itemTitle: String, itemDescription: String) {
        viewModelScope.launch {
            val item = LearnedItem(itemTitle, itemDescription, Level.HIGH)
            repository.insertNewLearnedItem(item)
        }
    }

    //DESAFIO 1) NÃO DEIXAR CRIAR COM VAZIO

}