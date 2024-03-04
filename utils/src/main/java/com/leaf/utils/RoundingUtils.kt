package com.leaf.utils

import java.math.RoundingMode

object RoundingUtils {
    fun roundValues(value : Double) : Int{
        return value.toBigDecimal().setScale(0, RoundingMode.HALF_EVEN).toInt()
    }
}