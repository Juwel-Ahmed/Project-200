package com.example.bmicalculatorwithhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserProfile : Fragment() {
    lateinit var fname: EditText
    lateinit var lname: EditText
    lateinit var email: EditText
    lateinit var btnUpdateProfile: Button
    lateinit var mname: TextView
    lateinit var memail: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val v2: View = inflater.inflate(R.layout.nav_header, container, false)
        fname = v.findViewById<View>(R.id.txtfname) as EditText
        lname = v.findViewById<View>(R.id.txtlname) as EditText
        email = v.findViewById<View>(R.id.txtemail) as EditText
        btnUpdateProfile = v.findViewById<View>(R.id.btnUpdateProfile) as Button
        mname = v2.findViewById<View>(R.id.txtNamefl) as TextView
        // memail = (TextView) v.findViewById(R.id.txtEmailAdd);
        btnUpdateProfile!!.setOnClickListener { //mname.setText(fname.getText().toString()+" "+lname.getText().toString());
            // memail.setText(email.getText().toString());
            val shared = SharedPreferences2()
            shared.saveInfo(
                requireContext(),
                "UserProfileData",
                "FirstName",
                fname.getText().toString()
            )
            shared.saveInfo(requireContext(), "UserProfileData", "LastName", lname.getText().toString())
            shared.saveInfo(requireContext(), "UserProfileData", "Email", email.getText().toString())
            shared.loadData(requireContext(), "UserProfileData", "FirstName")
            mname.setText("Hi")
        }
        return v
    }

    companion object {
        const val SHARED_PREFS_CATEGORY = "sharedPrefs"
        const val SHARED_PREFS_NAME = "name"
        const val SHARED_PREFS_VALUE = "value"
    }
}