package com.example.nakanokugarbage.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nakanokugarbage.Model.BlockModel
import com.example.nakanokugarbage.Repository.BlockNameRepository


class BlockNameViewModel: ViewModel() {

    private val mRepository: BlockNameRepository = BlockNameRepository.getInstance()
    private var mBlockLiveDataModel: LiveData<List<BlockModel>>? = null

    init {
        mBlockLiveDataModel = mRepository.getBlockList()
    }

    fun getBlockLiveData(): LiveData<List<BlockModel>>? {
        return mBlockLiveDataModel
    }

}