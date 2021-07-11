package com.example.bmicalculatorwithhistory.business

class CalculateBMI(val inputfeet: Double, val inputinches: Double, val inputkg: Double) {
    private val FEET_TO_CM = 30.48
    private val INCH_TO_CM = 2.54
    fun camlculatebmi(inputkg: Double, inputinches: Double, inputfeet: Double): Double {
        var inputinches = inputinches
        var inputfeet = inputfeet
        var result = 0.0
        inputfeet = inputfeet * FEET_TO_CM
        inputinches = inputinches * INCH_TO_CM
        val txtheightm = (inputfeet + inputinches) / 100
        result = inputkg / (txtheightm * txtheightm)
        result = Math.round(result * 100).toDouble() / 100
        return result
    }

    fun getbmitype(bmi: Double): String {
        var type = "null"
        if (bmi <= 18.5) {
            type = "Underweight"
        } else if (bmi <= 24.9) {
            type = "Normal Weight"
        } else if (bmi <= 29.9) {
            type = "Over Weight"
        } else if (bmi <= 34.9) {
            type = "Obesity"
        } else if (bmi > 35) {
            type = "Extremely Obesity"
        }
        return type
    }
}