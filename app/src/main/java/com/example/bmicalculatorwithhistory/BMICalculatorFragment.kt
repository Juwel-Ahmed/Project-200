package com.example.bmicalculatorwithhistory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bmicalculatorwithhistory.business.CalculateBMI
import com.example.bmicalculatorwithhistory.business.bmidatatable
import java.text.SimpleDateFormat
import java.util.*

class BMICalculatorFragment : Fragment() {
    @SuppressLint("SimpleDateFormat")
    var formatter = SimpleDateFormat("dd/MM/yyyy")
    var date = Date()
    lateinit var lblBMI: TextView
    lateinit var btnCalculate: Button
    lateinit var txtHeightFeet: TextView
    lateinit var txtHeightInches: TextView
    lateinit var txtWeight: TextView
    lateinit var imgbmi: ImageView
    lateinit var calculateBMI: CalculateBMI
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_bmicalculator, container, false)
        btnCalculate = v.findViewById<View>(R.id.btnCalculate) as Button
        lblBMI = v.findViewById<View>(R.id.tv_BMI_result) as TextView
        txtHeightFeet = v.findViewById<View>(R.id.et_feet) as TextView
        txtHeightInches = v.findViewById<View>(R.id.et_inches) as TextView
        txtWeight = v.findViewById<View>(R.id.et_weight) as TextView
        imgbmi = v.findViewById<View>(R.id.img_BMI) as ImageView
        btnCalculate!!.setOnClickListener {
            try {
                val txthfeet: Double = txtHeightFeet.text.toString().toDouble()
                val txthinch: Double = txtHeightInches.text.toString().toDouble()
                val txtweight: Double = txtWeight.text.toString().toDouble()
                calculateBMI = CalculateBMI(txthfeet, txthinch, txtweight)
                // Calculating BMI
                val bmi: Double = calculateBMI.camlculatebmi(
                    calculateBMI.inputkg,
                    calculateBMI.inputinches,
                    calculateBMI.inputfeet
                )

                //Getting BMI Type
                val bmiType: String = calculateBMI.getbmitype(bmi)

                //Getting device Date
                val dat2e = formatter.format(date)


                // Adding Data to the Database
                val bmiDataTable = bmidatatable(getActivity())
                bmiDataTable.openDB()
                bmiDataTable.insertRecord(dat2e, java.lang.Double.toString(bmi), bmiType)
                bmiDataTable.closeDB()
                Toast.makeText(activity, "Your BMI$bmi $bmiType", Toast.LENGTH_SHORT).show()

                //Adding to Display Elements
                lblBMI.text = "Your BMI is $bmi"
                when (bmiType) {
                    "Underweight" -> imgbmi!!.setImageResource(R.drawable.underweight)
                    "Normal Weight" -> imgbmi!!.setImageResource(R.drawable.normal)
                    "Over Weight" -> imgbmi!!.setImageResource(R.drawable.overweight)
                    "Obesity" -> imgbmi!!.setImageResource(R.drawable.obese)
                    "Extremely Obesity" -> imgbmi!!.setImageResource(R.drawable.extremelyobese)
                    else -> imgbmi!!.setImageResource(R.drawable.maxresdefault)
                }
            } catch (x: Exception) {
                Toast.makeText(getActivity(), "Enter Valid Input$x", Toast.LENGTH_SHORT).show()
            }
        }
        return v
    }
}