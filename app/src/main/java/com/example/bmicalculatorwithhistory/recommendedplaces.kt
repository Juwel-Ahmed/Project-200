package com.example.bmicalculatorwithhistory

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.view.LayoutInflater as LayoutInflater1

class recommendedplaces : Fragment(), OnMapReadyCallback {
    lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater1,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mapFragment = getChildFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
        if (mapFragment == null) {
            val fm: FragmentManager? = fragmentManager
            val ft: FragmentTransaction = fm!!.beginTransaction()
            mapFragment = SupportMapFragment.newInstance()
            ft.replace(R.id.map, mapFragment).commit()
        }
        mapFragment.getMapAsync(this)
        return inflater.inflate(R.layout.fragment_gymlocation, container, false)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}