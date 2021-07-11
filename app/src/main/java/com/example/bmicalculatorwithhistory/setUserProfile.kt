package com.example.bmicalculatorwithhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class setUserProfile : Fragment() {
    lateinit var mname: TextView
    lateinit var memail: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.nav_header, container, false)
    }

    fun setuser(v: FragmentActivity?) {
        mname.setText("Hi")
        memail.setText("bye")
    }
}