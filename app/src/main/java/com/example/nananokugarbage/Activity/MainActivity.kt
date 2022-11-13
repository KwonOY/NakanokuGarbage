package com.example.nananokugarbage.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nananokugarbage.Fragment.SearchFragment
import com.example.nananokugarbage.Helper.DataBaseHelper
import com.example.nananokugarbage.Model.CityBlock
import com.example.nananokugarbage.R

class MainActivity : AppCompatActivity() {

    var db : DataBaseHelper? = null
    var cityBlockList = mutableListOf<CityBlock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        db = DataBaseHelper.getInstance(this)
        dbTest()
        val searchFragment = SearchFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Main_FrameLayout, searchFragment)
        transaction.commit()
    }

    private fun dbTest() {
        for (i : Int in 1..10) {
            var cityblock = CityBlock(i, i.toString(), i.toString(), i.toString(), i.toString(), i.toString(), i.toString(), i.toString())
            db!!.contactsDao().insertAll(cityblock)
        }
        val savedContacts = db!!.contactsDao().getAll()
//        db!!.contactsDao().insertAll(cityBlockList)
    }
}