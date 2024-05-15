package com.ezyxip.veko.data

import com.ezyxip.veko.services.DateSerializer
import com.ezyxip.veko.services.format
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Event(
    val theme: String,
    val description: String,
    @Serializable(with = DateSerializer::class)
    val date: Date
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        if (theme != other.theme) return false
        if (description != other.description) return false
        return format.format(date) == format.format(other.date)
    }

    override fun hashCode(): Int {
        var result = theme.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }

    override fun toString(): String {
        return "Event(theme='$theme', description='$description', date=$date)"
    }


}
