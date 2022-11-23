package com.example.nakanokugarbage.Controller

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Criteria
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.nakanokugarbage.Interface.GoogleMapListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class GoogleMapController(context: Context, googleMap: GoogleMap, googleMapListener: GoogleMapListener) {

    private val mContext = context
    private val mGoogleMap = googleMap
    private val mGoogleMapListener = googleMapListener

    private val mNakanoLat = 35.7073985
    private val mNakanoLng = 139.6638354
    private val mNakanoCenterLatLng = LatLng(mNakanoLat, mNakanoLng)

    // TODO 데이터형으로 가질지 고민중
    private val mTokyoLowerLatitude = 35.513
    private val mTokyoLowerLongitude = 139.555
    private val mTokyoUpperLatitude = 35.821
    private val mTokyoUpperLongitude = 139.923

    private val mFirstZoomLevel = 13f
    private val mZoomLevel = 17f

    private val MIN_TIME_OUT: Long = 1000 * 10;
    private val MIN_DISTANCE: Float = 10f;

    fun updateCurrentLocation() {

        Log.d("myTest","updateCurrentLocation")

        val locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val hasFineLocationPermission =
            ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
        val hasCoarseLocationPermission =
            ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if ((hasFineLocationPermission == PackageManager.PERMISSION_GRANTED || hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) &&
            (isGpsEnable || isNetworkEnable)) {

            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_COARSE
            criteria.powerRequirement = Criteria.POWER_LOW
            var provider = locationManager.getBestProvider(criteria, true)
            if (provider == null) provider = LocationManager.NETWORK_PROVIDER
            Log.d("myTest","provider: " + provider)
            locationManager.requestLocationUpdates(
                provider,
                MIN_TIME_OUT,
                MIN_DISTANCE,
                mGoogleMapListener
            )
        } else {
            mGoogleMapListener.onFailedLocation()
        }
    }

    fun moveCameraGoogleMap(addresses: List<Address>?){
        var address: Address? = null
        addresses?.let {
            address = addresses[0]
        }
        moveCameraGoogleMap(address)
    }

    fun moveCameraGoogleMap(address: Address? = null){
        var cameraUpdateFactory = CameraUpdateFactory.newLatLngZoom(mNakanoCenterLatLng, mFirstZoomLevel)
        address?.let {
            val latLng = LatLng(address.latitude, address.longitude)
            cameraUpdateFactory = CameraUpdateFactory.newLatLngZoom(latLng, mZoomLevel)
        }
        Handler(Looper.getMainLooper()).post {
            mGoogleMap.animateCamera(cameraUpdateFactory)
        }
    }

    fun makeMarkGoogleMap(latLng: LatLng){
        mGoogleMap.addMarker(MarkerOptions().position(latLng).title("your hear"))
    }

    fun searchAddressFromLocationName(locationName: String) {
        val geocoder = Geocoder(mContext, Locale.JAPAN)
        if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
            geocoder.getFromLocationName(locationName, 5,
                mTokyoLowerLatitude, mTokyoLowerLongitude,
                mTokyoUpperLatitude, mTokyoUpperLongitude
            ) { addresses ->
                mGoogleMapListener.onAddressesReceive(addresses)
            }
        } else {
            val addresses = geocoder.getFromLocationName(locationName, 5,
                mTokyoLowerLatitude, mTokyoLowerLongitude,
                mTokyoUpperLatitude, mTokyoUpperLongitude)
            if (addresses != null) {
                mGoogleMapListener.onAddressesReceive(addresses)
            }
        }
    }

    fun searchAddressFromLocationLatLng(latLng: LatLng){
        val geocoder = Geocoder(mContext, Locale.JAPAN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(latLng.latitude, latLng.longitude,
                5) { addresses ->
                mGoogleMapListener.onAddressesReceive(addresses)
            }
        } else {
            val addresses =
                geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5)
            if (addresses != null) {
                mGoogleMapListener.onAddressesReceive(addresses)
            }
        }
    }

}