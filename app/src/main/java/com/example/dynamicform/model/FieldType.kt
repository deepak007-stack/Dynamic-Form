package com.example.dynamicform.model

data class FieldType(
    val ExceptionMessage: Any,
    val HttpStatusCode: Int,
    val IsSuccess: Boolean,
    val `data`: List<Data>,
    val message: String
)