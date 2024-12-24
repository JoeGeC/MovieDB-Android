package com.joe.core.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun LocalDate.formatLocalDate(
    locale: Locale = Locale.getDefault(),
    style: FormatStyle = FormatStyle.SHORT
): String {
    val dateFormatter = DateTimeFormatter.ofLocalizedDate(style).withLocale(locale)
    return this.format(dateFormatter)
}