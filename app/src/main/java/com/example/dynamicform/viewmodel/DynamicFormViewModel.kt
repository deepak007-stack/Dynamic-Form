package com.example.dynamicform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicform.model.FieldType
import com.example.dynamicform.network.Response
import com.example.dynamicform.repository.FormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DynamicFormViewModel(private val repository: FormRepository) : ViewModel() {

    private val _userDataFlow = MutableStateFlow<Response<FieldType?>>(Response.Loading())
    val userDataFlow = _userDataFlow.asStateFlow()


    fun getUser() {
        viewModelScope.launch {
            try {
                _userDataFlow.value = Response.Loading()
                val response = repository.getUser()
                _userDataFlow.value = Response.Success(response)
            } catch (e: Exception) {
                _userDataFlow.value = Response.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}