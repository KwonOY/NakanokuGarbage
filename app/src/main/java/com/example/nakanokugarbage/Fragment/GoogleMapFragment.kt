package com.example.nakanokugarbage.Fragment

import android.location.Address
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nakanokugarbage.Controller.GoogleMapController
import com.example.nakanokugarbage.Interface.GoogleMapListener
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.example.nakanokugarbage.databinding.GooglemapLayoutBinding
import com.google.android.gms.maps.model.LatLng

class GoogleMapFragment(listener: SearchFragment.ChildResultListener) : BaseSearchChildFragment(), OnMapReadyCallback {

    private lateinit var mBinding: GooglemapLayoutBinding
    private lateinit var mMapView: MapView
    private lateinit var mGoogleMapController: GoogleMapController
    private val mListener = listener
    private var needParentNotify = false

    private val googleMapListener = object: GoogleMapListener {
        override fun onAddressesReceive(addresses: List<Address>) {
            mGoogleMapController.moveCameraGoogleMap(addresses)
            if(needParentNotify) {
                mListener.onReceiveResult(addresses[0].getAddressLine(0))
                needParentNotify = false
            }
        }

        override fun onFailedLocation() {
            mGoogleMapController.moveCameraGoogleMap()
            Toast.makeText(context, "cant search last LatLng", Toast.LENGTH_SHORT).show()
        }

        override fun onLocationChanged(location: Location) {
            needParentNotify = true
            val currentLatLng = LatLng(location.latitude, location.longitude)
            mGoogleMapController.searchAddressFromLocationLatLng(currentLatLng)
            mGoogleMapController.makeMarkGoogleMap(currentLatLng)
        }
    }

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

    override fun onMapReady(googleMap: GoogleMap) {
        context?.let {
            mGoogleMapController = GoogleMapController(it, googleMap, googleMapListener)
            mGoogleMapController.moveCameraGoogleMap()
            mGoogleMapController.updateCurrentLocation()
        }
    }

    override fun setSearchResultText(searchText: String) {
        super.setSearchResultText(searchText)
        mGoogleMapController.searchAddressFromLocationName(searchText)
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