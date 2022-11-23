package com.example.nakanokugarbage.Interface

import android.location.Address
import android.location.Location
import android.location.LocationListener

interface GoogleMapListener: LocationListener{
    fun onAddressesReceive(addresses: List<Address>)

    fun onFailedLocation()
}