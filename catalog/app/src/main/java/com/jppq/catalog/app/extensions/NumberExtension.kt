package com.jppq.catalog.app.extensions

import java.text.NumberFormat
import java.util.*

fun Double.priceFormat(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    format.maximumFractionDigits = 0
    return format.format(this)
}