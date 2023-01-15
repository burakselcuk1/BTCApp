package com.example.btcapp.ui.btcFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.btcapp.base.BaseViewModel
import com.example.btcapp.common.enums.Status
import com.example.btcapp.model.BtcItemResponsse
import com.example.btcapp.model.BtcResponse
import com.example.btcapp.repository.BtcRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BTCViewModel @Inject constructor(private val repository: BtcRepository) : BaseViewModel() {

    private val _btc = MutableLiveData<BtcItemResponsse>()
    val btc: LiveData<BtcItemResponsse> = _btc

    private var _statusData = MutableLiveData<Status>()
    val statusData: LiveData<Status> = _statusData

    init {
        getBtc()
    }

    fun getBtc() {
        GlobalScope.launch {
            repository.getUsers()
                .catch {
                    _statusData.postValue(Status.ERROR)
                }
                .collect {
                    when (it.status) {
                        Status.LOADING -> {
                            _statusData.postValue(Status.LOADING)
                        }
                        Status.SUCCESS -> {
                            it.data!!.body()!!.forEach { item ->
                                if (item.code == "USD") {
                                    _btc.postValue(item)
                                    _statusData.postValue(Status.SUCCESS)
                                }
                            }
                        }
                        Status.ERROR -> {
                            _statusData.postValue(Status.ERROR)
                        }
                    }
                }
        }
    }
}