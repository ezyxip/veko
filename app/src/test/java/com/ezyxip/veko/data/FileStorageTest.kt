package com.ezyxip.veko.data

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileStorageTest {

    fun setup(){
        File("./testdir").mkdir()
        File("./testdir/notes").mkdir()
        File("./testdir/events").mkdir()
        File("./testdir/id").mkdir()
        File("./testdir/notes/note1.note").createNewFile()
        File("./testdir/notes/note2.note").createNewFile()
        File("./testdir/notes/note3.note").createNewFile()
    }
    @AfterEach
    fun clear(){
        File("./testdir").deleteRecursively()
    }

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
    }

    @Test
    fun createFileWithCurrentDir(){
        setup()
        val storage = FileStorage("testdir")
        storage.currentDir = "notes"
        storage.addFile("test.note")
        assertEquals(true, File("./testdir/notes/test.note").exists())
        assertEquals(true, File("./testdir/notes/test.note").isFile)
    }

    @Test
    fun createFileWithoutCurrentDir(){
        setup()
        val storage = FileStorage("testdir")
        storage.addFile("test.event", "events")
        assertEquals(true, File("./testdir/events/test.event").exists())
        assertEquals(true, File("./testdir/events/test.event").isFile)
    }

    @Test
    fun fileExistsWithCurrentDir(){
        setup()
        val storage = FileStorage("testdir")
        storage.currentDir = "notes"

        assertEquals(true, storage.exists("note1.note"))
        assertEquals(true, storage.exists("note2.note"))
        assertEquals(true, storage.exists("note3.note"))

        assertEquals(false, storage.exists("note4"))
        assertEquals(false, storage.exists("notfsde2"))
        assertEquals(false, storage.exists("fsd"))
    }

    @Test
    fun fileExists(){
        setup()
        val storage = FileStorage("testdir")

        assertEquals(true, storage.exists("note1.note", "notes"))
        assertEquals(true, storage.exists("note2.note", "notes"))
        assertEquals(true, storage.exists("note3.note", "notes"))

        assertEquals(false, storage.exists("note4", "notes"))
        assertEquals(false, storage.exists("notfsde2", "notes"))
        assertEquals(false, storage.exists("fsd", "notes"))
    }

    @Test
    fun getFile(){
        setup()
        val storage = FileStorage("testdir")
        val note = storage.getFile("note1.note", "notes")

        assertEquals(true, note.exists())
        assertEquals(true, note.isFile)

        storage.currentDir = "notes"
        val note2 = storage.getFile("note1.note", "notes")

        assertEquals(true, note2.exists())
        assertEquals(true, note2.isFile)

        assertThrows(Exception::class.java){storage.getFile("fsd", "fsd")}
    }

    @Test
    fun getFiles(){
        setup()
        val storage = FileStorage("testdir")
        val expected = listOf(
            File("./testdir/notes/note1.note"),
            File("./testdir/notes/note2.note"),
            File("./testdir/notes/note3.note")
        )

        val found = storage.getFiles("notes")

        assertEquals(expected.map { e -> e.canonicalPath }, found.map { e -> e.canonicalPath })
    }
}