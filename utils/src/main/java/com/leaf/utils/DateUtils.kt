package com.leaf.utils

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.text.ParseException
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd hh:mm:ss"
    private const val DATE_FORMAT_PATTERN = "yyyy-MM-dd"

    fun getSimpleDateString(): String {
        val formatter: DateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN)
        val date = Date()
        return formatter.format(date)
    }

    fun getDayOfWeek(date: String): String? {
        return try {
            val formatter: DateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN)
            val date: Date = formatter.parse(date) as Date
            SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime())
        } catch (e: ParseException) {
            null
        }
    }

    fun getUnixDate(): Long? {
        return try {
            Date().time
        } catch (e: Exception) {
            null
        }
    }

    fun getFormattedDate(): String {
        val sdf: SimpleDateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN)
        return sdf.format(Date())
    }
}