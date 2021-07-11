package com.example.bmicalculatorwithhistory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class mapswebview : Fragment() {
    private lateinit var webView: WebView
    lateinit var fname: EditText
    lateinit var lname: EditText
    lateinit var email: EditText
    lateinit var btnUpdateProfile: Button
    lateinit var mname: TextView
    lateinit var memail: TextView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_map, container, false)
        webView = v.findViewById<View>(R.id.webview) as WebView
        webView.setWebViewClient(WebViewClient())
        val webSettings: WebSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView.loadUrl("https://www.google.com/maps/search/gym/")
        return v
    }

    companion object {
        const val SHARED_PREFS_CATEGORY = "sharedPrefs"
        const val SHARED_PREFS_NAME = "name"
        const val SHARED_PREFS_VALUE = "value"
    }
}