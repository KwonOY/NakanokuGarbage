package com.example.nakanokugarbage.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nakanokugarbage.Model.Block
import com.example.nakanokugarbage.Repository.BlockNameRepository


class BlockNameViewModel: ViewModel() {

    private val mRepository: BlockNameRepository = BlockNameRepository.getInstance()
    private var mBlockLiveData: LiveData<List<Block>>? = null

    init {
        mBlockLiveData = mRepository.getBlockList()
    }

    fun getBlockLiveData(): LiveData<List<Block>>? {
        return mBlockLiveData
    }

}