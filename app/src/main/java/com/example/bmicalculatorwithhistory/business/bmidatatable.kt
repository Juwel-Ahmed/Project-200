package com.example.bmicalculatorwithhistory.business

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class bmidatatable(context: Context?) {
    var dbhelper: DBHelper
    var sqLiteDatabase: SQLiteDatabase? = null
    fun openDB() {
        sqLiteDatabase = dbhelper.writableDatabase
    }

    fun closeDB() {
        sqLiteDatabase!!.close()
    }

    fun insertRecord(date: String?, bmivalue: String?, bmitype: String?) {
        val contentValues = ContentValues()
        contentValues.put(DBHelper.Companion.TABLE_BMIDATE, date)
        contentValues.put(DBHelper.Companion.TABLE_BMIVALUE, bmivalue)
        contentValues.put(DBHelper.Companion.TABLE_BMISTATE, bmitype)
        sqLiteDatabase!!.insert(DBHelper.Companion.TABLE_BMIDATA, null, contentValues)
    }

    val allRecords: Cursor
        get() = sqLiteDatabase!!.rawQuery("select * from " + DBHelper.Companion.TABLE_BMIDATA, null)

    fun clearallRecords(): Cursor {
        return sqLiteDatabase!!.rawQuery("delete from " + DBHelper.Companion.TABLE_BMIDATA, null)
    }

    init {
        dbhelper = DBHelper(context)
    }
}