package com.example.nakanokugarbage.Fragment

import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.example.nakanokugarbage.databinding.GooglemapLayoutBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng

class GoogleMapFragment : BaseSearchChildFragment(), OnMapReadyCallback {

    private lateinit var mBinding: GooglemapLayoutBinding
    private lateinit var mMapView: MapView
    private lateinit var mGoogleMap: GoogleMap

    private var mNakanoLat = 35.7073985
    private var mNakanoLng = 139.6638354
    private var mNakanoCenterLatLng = LatLng(mNakanoLat, mNakanoLng)

    // TODO 데이터형으로 가질지 고민중
    private var mTokyoLowerLatitude = 35.513
    private var mTokyoLowerLongitude = 139.555
    private var mTokyoUpperLatitude = 35.821
    private var mTokyoUpperLongitude = 139.923

    private var mFirstZoomLevel = 13f
    private var mZoomLevel = 17f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = GooglemapLayoutBinding.inflate(layoutInflater, container, false)
        mMapView = mBinding.mapView
        mMapView.onCreate(savedInstanceState)
        mMapView.getMapAsync(this)

        return mBinding.root
    }

    // TODO 버전 낮춰서도 할것
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setSearchResultText(searchText: String) {
        context?.let {
            val geocoder = Geocoder(it)
            geocoder.getFromLocationName(
                searchText, 5,
                mTokyoLowerLatitude, mTokyoLowerLongitude, mTokyoUpperLatitude, mTokyoUpperLongitude
            ) { addresses ->
                val address = addresses.get(0)
                val latLng = LatLng(address.latitude, address.longitude)
                Handler(Looper.getMainLooper()).post {
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, mZoomLevel))
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                mNakanoCenterLatLng,
                mFirstZoomLevel
            )
        )
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

    override fun onDestroy() {
        mMapView.onDestroy()
        super.onDestroy()
    }

}