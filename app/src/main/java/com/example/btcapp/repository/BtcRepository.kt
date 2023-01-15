package com.example.btcapp.repository

import com.example.btcapp.common.handleRequestFlow
import com.example.btcapp.services.ApiImpl
import javax.inject.Inject


class BtcRepository @Inject constructor(private val btcApiImple: ApiImpl) {

    suspend fun getUsers() =  handleRequestFlow { btcApiImple.getBtc() }

}