package com.example.btcapp.services

import com.example.btcapp.services.Api
import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: Api) {

    suspend fun getBtc() = api.getBtc()

}