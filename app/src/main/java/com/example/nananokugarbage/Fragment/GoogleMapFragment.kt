package com.example.nananokugarbage.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.example.nananokugarbage.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng

class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMapView: MapView

    private var mNakanoLat = 35.7073985
    private var mNakanoLng = 139.6638354
    private var mNakanoCenterLatLng = LatLng(mNakanoLat, mNakanoLng)

    private var mFirstZoomLevel = 13f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.googlemap_layout, container, false)
        mMapView = rootView.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)
        mMapView.getMapAsync(this)
        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mNakanoCenterLatLng, mFirstZoomLevel))
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