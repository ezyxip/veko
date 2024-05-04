package com.ezyxip.veko.data

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class FileStorageTest {
    @Test
    fun createFolderTest(){
        val storage = FileStorage("testdir")
        storage.addDir("notes")
        storage.addDir("events")
        storage.addDir("id")

        val noteDir = File("./testdir/notes")
        val eventDir = File("./testdir/events")
        val idDir = File("./testdir/id")

        assertEquals(true, noteDir.exists())
        assertEquals(true, noteDir.isDirectory)

        assertEquals(true, eventDir.exists())
        assertEquals(true, eventDir.isDirectory)

        assertEquals(true, idDir.exists())
        assertEquals(true, idDir.isDirectory)

        noteDir.delete()
        eventDir.delete()
        idDir.delete()

        File("./testdir").delete()
    }
}