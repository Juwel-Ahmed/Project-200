package com.example.bmicalculatorwithhistory.business

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_Name, null, DB_VERSION) {
    var CREATE_TABLE = """CREATE TABLE `bmidata` (
	`bmiid`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`bmidate`	TEXT,
	`bmivalue`	TEXT,
	`bmitype`	TEXT
)"""

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val DB_Name = "bmi"
        private const val DB_VERSION = 2
        const val TABLE_BMIDATA = "bmidata"
        const val TABLE_BMIDATAID = "bmiid"
        const val TABLE_BMIDATE = "bmidate"
        const val TABLE_BMIVALUE = "bmivalue"
        const val TABLE_BMISTATE = "bmitype"
    }
}