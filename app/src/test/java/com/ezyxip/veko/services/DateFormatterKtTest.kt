package com.ezyxip.veko.services

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.Date

class DateFormatterKtTest {

    @Test
    fun date() {
        val now = Date()
        val tom = SimpleDateFormat("dd.MM.yyyy HH:mm").parse("9.05.2024 17:44")
        val repres = SimpleDateFormat("dd.MM.yyyy HH:mm").format(now)
        println(now)
        println(tom)
        println(repres)
    }
}