package com.joe.core.entity

import java.time.LocalDate

fun String.toLocalDateOrNull(): LocalDate? = try {
    LocalDate.parse(this)
} catch (_: Exception) {
    null
}