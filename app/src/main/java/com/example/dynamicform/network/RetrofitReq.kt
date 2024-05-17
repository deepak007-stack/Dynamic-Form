package com.example.dynamicform.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitReq {

    private const val BASE_URl = "https://avcv.svgcso.com/"

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services : ApiServices = retrofit.create(ApiServices::class.java)
}