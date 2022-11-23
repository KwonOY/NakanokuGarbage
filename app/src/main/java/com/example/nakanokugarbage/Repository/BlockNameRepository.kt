package com.example.nakanokugarbage.Repository

import androidx.lifecycle.MutableLiveData
import com.example.nakanokugarbage.Constant
import com.example.nakanokugarbage.Model.Block
import com.google.firebase.database.*


open class BlockNameRepository {

    companion object mInstance: BlockNameRepository()

    private val mBlockListLiveData: MutableLiveData<List<Block>> =
        MutableLiveData<List<Block>>()

    fun getInstance(): BlockNameRepository{
        return mInstance;
    }

    fun getBlockList(): MutableLiveData<List<Block>>{
        val blockDb = FirebaseDatabase.getInstance().reference.child(Constant.FIREBASE_DB_BLOCK)
        blockDb.addValueEventListener(BlcokDatabaseValueEventListener())
        return mBlockListLiveData;
    }

    inner class BlcokDatabaseValueEventListener : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val type = object : GenericTypeIndicator<List<Block>>() {}
            val blocks = snapshot.getValue(type)
            blocks?.let {
                mBlockListLiveData.value = it
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }


}