package com.example.nakanokugarbage.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nakanokugarbage.Fragment.SearchFragment
import com.example.nakanokugarbage.Helper.DataBaseHelper
import com.example.nakanokugarbage.Model.CityBlock
import com.example.nakanokugarbage.databinding.MainLayoutBinding
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainLayoutBinding

    var db : DataBaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        db = DataBaseHelper.getInstance(this)
        dbTest()

        CoroutineScope(Dispatchers.IO).launch {
            val client = HttpClient()
            val response: HttpResponse = client.get("https://maps.googleapis.com/maps/api/geocode/xml?place_id=ChIJeRpOeF67j4AR9ydy_PIzPuM&key=AIzaSyDI4-ug5uLrcWSam3X0r_iFT9bF6BLs-n4")
            val stringBody: String = response.receive()
            Log.d("myTest", stringBody)
            client.close()
        }

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
            db!!.cityBlockDaoDao().insertAll(cityblock)
        }
    }
}