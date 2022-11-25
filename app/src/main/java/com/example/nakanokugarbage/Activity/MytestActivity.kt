package com.example.nakanokugarbage.Activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nakanokugarbage.databinding.IstestSplashLayoutBinding


class MytestActivity: AppCompatActivity() {

    private lateinit var mBinding: IstestSplashLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.d("myTest","is Splash")
        super.onCreate(savedInstanceState, persistentState)
        mBinding = IstestSplashLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        Log.d("myTest","is Splash")
//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            startActivity(intent)
//            finish()
//            Log.d("myTest","is Finish")
//        }, 3000)
    }

}