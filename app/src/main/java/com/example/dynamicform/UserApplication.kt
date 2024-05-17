package com.example.dynamicform

import android.app.Application
import com.example.dynamicform.network.RetrofitReq
import com.example.dynamicform.repository.FormRepository

class UserApplication : Application() {

    lateinit var repository : FormRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        val apiServices = RetrofitReq.services
        repository = FormRepository(apiServices)
    }
}