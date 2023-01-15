package com.example.btcapp.ui.btcFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btcapp.R
import com.example.btcapp.adapter.BtcAdapter
import com.example.btcapp.base.BaseFragment
import com.example.btcapp.common.enums.Status
import com.example.btcapp.common.extensions.observe
import com.example.btcapp.common.extensions.observeEvent
import com.example.btcapp.common.tryOrLog
import com.example.btcapp.common.utils.ProgressDialogUtil
import com.example.btcapp.databinding.FragmentBTCBinding


class BTCFragment : BaseFragment<FragmentBTCBinding, BTCViewModel>(
    layoutId = R.layout.fragment_b_t_c, viewModelClass = BTCViewModel::class.java
) {
    var isProgress = false

    override fun onInitDataBinding() {

        binding.reflesh.setOnClickListener {
            RefreshApiReload()
        }

        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        if (!isProgress) {
                            ProgressDialogUtil.showLoadingProgress(context = requireContext())
                            ProgressDialogUtil.start()
                        }
                    }

                    Status.SUCCESS -> {
                        observe(viewModel.btc) {
                            binding.name.text = it.name.toString()
                            binding.price.text = it.rate.toString()
                        }
                        isProgress = false
                        ProgressDialogUtil.stop()
                    }

                    Status.ERROR -> {
                        ProgressDialogUtil.stop()
                    }
                }
            }
        }
    }


    private fun RefreshApiReload() {

        isProgress = true;
        ProgressDialogUtil.showLoadingProgress(context = requireContext())
        ProgressDialogUtil.start()

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getBtc()
        }, 3000)
    }

    private fun getToken() {
        viewModel.getBtc()
    }
}