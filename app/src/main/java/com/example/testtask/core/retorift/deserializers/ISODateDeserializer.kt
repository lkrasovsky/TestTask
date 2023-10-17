package com.example.testtask.core.retorift.deserializers

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class ISODateDeserializer : JsonDeserializer<ISODate> {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ISODate {
        try {
            return ISODate(dateFormat.parse(json.asString)!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            throw e
        }
    }
}

fun main() {
    val gson = GsonBuilder()
        .registerTypeAdapter(ISODate::class.java, ISODateDeserializer())
        .create()
    val parsed = gson.fromJson("{date: \"2023-10-17T01:21:10.576Z\"}", Dto::class.java)
    println(parsed.date)
}

class Dto(
    val date: ISODate
)
