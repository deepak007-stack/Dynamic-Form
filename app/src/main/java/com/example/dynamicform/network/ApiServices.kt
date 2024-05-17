package com.example.dynamicform.network

import com.example.dynamicform.model.FieldType
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

//    val base_url = "https://avcv.svgcso.com/avcv.asmx/getCoApplicantFields"

    @GET("avcv.asmx/getCoApplicantFields")
    suspend fun getData(
        @Query("AcId") acId : String,
        @Query("PID") pid : String,
        @Query("FType") fType : String
    ) : FieldType

}