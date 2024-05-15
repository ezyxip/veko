package com.ezyxip.veko.data

import com.ezyxip.celli.services.IdResolver
import com.ezyxip.celli.services.SimpleIdResolver
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File
import java.util.Date

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventFileCRUDTest{
    val storage = FileStorage("testdir")
    val idResolver: IdResolver = SimpleIdResolver("testdir")

    @AfterAll
    fun clear() {
        File("testdir").deleteRecursively()
    }

    @Test
    fun createNote(){
        val eventCRUD = EventFileCRUD(storage, idResolver)
        val now = Date()
        now.seconds = 0
        val event = Event("Theme", "Description", now)

        val int = eventCRUD.add(event)

        val resEvent = eventCRUD.get(int)

        assertEquals(event, resEvent)
    }
}