package com.joe.data.json

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter : TypeAdapter<LocalDate>() {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    override fun write(out: JsonWriter, value: LocalDate?) {
        out.value(value?.format(formatter))
    }

    override fun read(`in`: JsonReader): LocalDate? {
        val date = `in`.nextString()
        return try {
            LocalDate.parse(date, formatter)
        } catch (_: Exception) {
            null
        }
    }
}