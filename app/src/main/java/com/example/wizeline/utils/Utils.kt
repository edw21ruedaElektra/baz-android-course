package com.example.wizeline.utils

import java.text.DecimalFormat

fun Boolean?.orFalse() = this ?:false

fun String.formatMXN():String{
    val amount: Double = this.toDouble()
    val formatter = DecimalFormat("#,###.00")
    return formatter.format(amount)
}