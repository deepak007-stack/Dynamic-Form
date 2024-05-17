package com.example.dynamicform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.dynamicform.repository.FormRepository

class FormViewModelFactory(private val repository: FormRepository) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DynamicFormViewModel(repository) as T
    }
}