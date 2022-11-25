package com.example.nakanokugarbage.Repository

import androidx.lifecycle.MutableLiveData
import com.example.nakanokugarbage.Constant
import com.example.nakanokugarbage.Model.BlockModel
import com.google.firebase.database.*


open class BlockNameRepository {

    companion object mInstance: BlockNameRepository()

    private val mBlockListLiveDataModel: MutableLiveData<List<BlockModel>> =
        MutableLiveData<List<BlockModel>>()

    fun getInstance(): BlockNameRepository{
        return mInstance;
    }

    fun getBlockList(): MutableLiveData<List<BlockModel>>{
        val blockDb = FirebaseDatabase.getInstance().reference.child(Constant.FIREBASE_DB_BLOCK)
        blockDb.addValueEventListener(BlcokDatabaseValueEventListener())
        return mBlockListLiveDataModel;
    }

    inner class BlcokDatabaseValueEventListener : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val type = object : GenericTypeIndicator<List<BlockModel>>() {}
            val blocks = snapshot.getValue(type)
            blocks?.let {
                mBlockListLiveDataModel.value = it
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }


}