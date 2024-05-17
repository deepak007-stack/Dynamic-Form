package com.example.dynamicform.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.dynamicform.network.Response
import com.example.dynamicform.viewmodel.DynamicFormViewModel

@Composable
fun MainScreen(vm: DynamicFormViewModel) {

    LaunchedEffect(key1 = Unit) {
        vm.getUser()
    }

    val state by vm.userDataFlow.collectAsState()

    when (val result = state) {
        is Response.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Error occurred, error = ${result.msg}", fontSize = 20.sp)
            }
        }

        is Response.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                androidx.compose.material3.CircularProgressIndicator()
            }
        }

        is Response.Success -> {
            result.data?.data?.let {
                RegisterScreen(dataList = result.data.data)
            }
        }
    }

}