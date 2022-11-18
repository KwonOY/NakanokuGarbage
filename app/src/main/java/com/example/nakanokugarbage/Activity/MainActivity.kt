package com.example.nakanokugarbage.Activity

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nakanokugarbage.Fragment.SearchFragment
import com.example.nakanokugarbage.Helper.DataBaseHelper
import com.example.nakanokugarbage.Model.Block
import com.example.nakanokugarbage.Model.CityBlock
import com.example.nakanokugarbage.databinding.MainLayoutBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainLayoutBinding

    var db : DataBaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        db = DataBaseHelper.getInstance(this)
        //dbTest()

        val dbref = FirebaseDatabase.getInstance().getReference()
        val childref = dbref.child("block_jp")

        val geocoder = Geocoder(this)
        val nakanolist = geocoder.getFromLocationName("nakanoku nakano 5", 10)
        if (nakanolist != null) {
            Log.d("myTest",nakanolist.size.toString())
            for(i in 0 until nakanolist.size) {
                Log.d("myTest",i.toString())
                Log.d("myTest", nakanolist[i].toString())
            }
        }

        childref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val test = snapshot.getValue(Block::class.java)

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        val searchFragment = SearchFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(mBinding.MainFrameLayout.id, searchFragment)
        transaction.commit()
    }

    private fun dbTest() {
        val city = "中野"
        db!!.cityBlockDaoDao().deleteAll()
        for (i : Int in 1..10) {
            val block = "nakano" + i + "chome"
            val cityblock = CityBlock(i, city, city, city, block, block, block)
            db!!.cityBlockDaoDao().insert(cityblock)
        }
    }
}