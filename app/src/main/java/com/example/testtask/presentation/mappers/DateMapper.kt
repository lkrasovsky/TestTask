package com.example.testtask.presentation.mappers

import android.text.format.DateUtils
import com.example.testtask.core.Mapper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateMapper : Mapper<Date, String> {

    private val outputTime = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val outputFullDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    override fun map(input: Date): String {
        val result = mapDateToText(input)
        return result.getOrDefault("Unknown")
    }

    private fun mapDateToText(inputDate: Date) = runCatching {
        val formattedTimeOnlyDate = outputTime.format(inputDate) ?: error("Wrong format date")
        val formattedFullDate = outputFullDate.format(inputDate) ?: error("Wrong format date")
        if (isToday(inputDate.time)) return@runCatching "Today, $formattedTimeOnlyDate"
        if (isYesterday(inputDate.time)) return@runCatching "Yesterday, $formattedTimeOnlyDate"
        if (isTomorrow(inputDate.time)) return@runCatching "Tomorrow, $formattedTimeOnlyDate"
        formattedFullDate
    }

    private fun isToday(whenInMillis: Long): Boolean {
        return DateUtils.isToday(whenInMillis)
    }

    private fun isTomorrow(whenInMillis: Long): Boolean {
        return DateUtils.isToday(whenInMillis - DateUtils.DAY_IN_MILLIS)
    }

    private fun isYesterday(whenInMillis: Long): Boolean {
        return DateUtils.isToday(whenInMillis + DateUtils.DAY_IN_MILLIS)
    }
}