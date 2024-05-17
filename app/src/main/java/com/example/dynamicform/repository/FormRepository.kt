package com.example.dynamicform.repository

import com.example.dynamicform.model.FieldType
import com.example.dynamicform.network.ApiServices

class FormRepository(private val apiServices: ApiServices) {

    suspend fun getUser(): FieldType {
        return apiServices.getData("89340145D", "29", "BVR")
    }
}