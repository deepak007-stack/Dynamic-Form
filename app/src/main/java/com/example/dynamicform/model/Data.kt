package com.example.dynamicform.model

data class Data(
    val CoApplicantName: String,
    val FieldName: String,
    val FieldType: String,
    var FieldValue: String,
    val Formtype: String,
    val ProcessID: Int,
    val SNo: Int,
    val Validate: String,
    val nid: Int
)