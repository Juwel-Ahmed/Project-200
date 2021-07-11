package com.example.bmicalculatorwithhistory

import android.app.Fragment
import android.content.Context
import android.widget.Toast

class SharedPreferences2 : Fragment() {
    private val mContext: Context? = null
    fun saveInfo(
        view: Context,
        SHARED_PREFS_CATEGORY: String?,
        SHARED_PREFS_NAME: String?,
        SHARED_PREFS_VALUE: String?
    ) {
        val sharedPreferences =
            view.getSharedPreferences(SHARED_PREFS_CATEGORY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SHARED_PREFS_NAME, SHARED_PREFS_VALUE)
        editor.apply()
    }

    fun loadData(view: Context, SHARED_PREFS_CATEGORY: String?, SHARED_PREFS_NAME: String?) {
        val sharedPreferences =
            view.getSharedPreferences(SHARED_PREFS_CATEGORY, Context.MODE_PRIVATE)
        val text = sharedPreferences.getString(SHARED_PREFS_NAME, "")
        Toast.makeText(view, text, Toast.LENGTH_LONG).show()
    }

    fun DeleteData(view: Context, SHARED_PREFS_CATEGORY: String?, SHARED_PREFS_NAME: String?) {
        val sharedPreferences =
            view.getSharedPreferences(SHARED_PREFS_CATEGORY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SHARED_PREFS_NAME, "")
        editor.apply()
    }

    companion object {
        const val SHARED_PREFS_CATEGORY = "sharedPrefs"
        const val SHARED_PREFS_NAME = "name"
        const val SHARED_PREFS_VALUE = "value"
    }
}