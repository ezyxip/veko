package com.ezyxip.veko.data

import com.ezyxip.celli.services.IdResolver
import com.ezyxip.celli.services.SimpleIdResolver
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoteCRUDTest{
    val storage = FileStorage("testdir")
    val idResolver: IdResolver = SimpleIdResolver("testdir")

    @AfterAll
    fun clear() {
        File("testdir").deleteRecursively()
    }

    @Test
    fun createNote(){
        val noteCRUD = NoteCRUD(storage, idResolver)
        val note = Note("Theme", "Description")

        val int = noteCRUD.add(note)

        val resNote = noteCRUD.get(int)

        assertEquals(note, resNote)
    }
}