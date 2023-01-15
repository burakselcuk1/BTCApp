package com.example.btcapp.services

import com.example.btcapp.model.BtcResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("api/rates")
    suspend fun getBtc(
    ): Response<BtcResponse>
}