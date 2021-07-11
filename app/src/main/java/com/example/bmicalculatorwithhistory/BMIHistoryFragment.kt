package com.example.bmicalculatorwithhistory

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.bmicalculatorwithhistory.business.bmidatatable
const val TAG = "emdad"
class BMIHistoryFragment : Fragment() {
    lateinit var bmidatatable: bmidatatable
    lateinit var cursor: Cursor
    lateinit var btnview: Button
    lateinit var btndelete: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_report, container, false)
        btndelete = v.findViewById<View>(R.id.btndelete) as Button
        try {
            bmidatatable = bmidatatable(requireContext())
            bmidatatable.openDB()
            cursor = bmidatatable.allRecords
            val listhistory = v.findViewById<View>(R.id.listview) as ListView
            val customAdapter = CustomAdapter()
            listhistory.adapter = customAdapter
            bmidatatable.closeDB()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
        btndelete.setOnClickListener {
            try {
                Log.d(TAG, "onCreateView: btnDelete PRESSED...............................")
                bmidatatable = bmidatatable(requireContext())
                bmidatatable.openDB()
                cursor = bmidatatable.allRecords
                Log.d(TAG, "onCreateView: btnDelete bmiDataTable = $bmidatatable  cursor = $cursor")
                if(cursor.count>0){
                    cursor = bmidatatable.clearallRecords()
                    cursor = bmidatatable.allRecords
                    Toast.makeText(requireContext(), "Deleting Records", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onCreateView: btnDelete IF........................")
                }
                else{
                    Toast.makeText(requireContext(), "There is no saved history", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onCreateView: btnDelte ELSE ...................................")
                }
                bmidatatable.closeDB()
            } catch (x: Exception) {
                Toast.makeText(requireContext(), "Delete Failed", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onCreateView: btnDelte CATCH ..................................")
                bmidatatable.closeDB()
            }
        }
        return v
    }

    inner class CustomAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return cursor.count
        }

        override fun getItem(position: Int): Any {
            return cursor
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = layoutInflater.inflate(R.layout.custom_layout, null)
            val txt_rowid: TextView = convertView.findViewById<View>(R.id.txtrowid) as TextView
            val txt_bmidate: TextView = convertView.findViewById<View>(R.id.txtbmidate) as TextView
            val txt_bmivalue: TextView = convertView.findViewById<View>(R.id.txtbmivalue) as TextView
            val txt_bmitype: TextView = convertView.findViewById<View>(R.id.txtbmitype) as TextView
            cursor!!.moveToPosition(position)
            txt_rowid.setText(cursor!!.getString(0))
            txt_bmidate.setText(cursor!!.getString(1))
            txt_bmivalue.setText(cursor!!.getString(2))
            txt_bmitype.setText(cursor!!.getString(3))
            return convertView
        }
    }
}