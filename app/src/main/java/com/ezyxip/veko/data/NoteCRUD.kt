package com.ezyxip.veko.data

import com.ezyxip.celli.services.IdResolver
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream

class NoteCRUD(
    val storage: FileStorage = FileStorage(),
    val idResolver: IdResolver = IdResolver.bean
) : CRUDManager<Note, Int> {
    init{
        storage.addDir("notes")
        storage.currentDir = "notes"
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun add(entity: Note): Int {
        val id = idResolver.getNextId().toString()
        val file = storage.addFile("$id.note")
        val stream = file.outputStream()
        Json.encodeToStream(entity, stream)
        stream.close()
        return id.toInt()
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun get(id: Int): Note {
        val file = storage.getFile("$id.note")
        val stream = file.inputStream()
        val res = Json.decodeFromStream<Note>(stream)
        stream.close()
        return res
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun update(entity: Identifiable<Note>) {
        val file = storage.getFile("${entity.id}.note")
        val stream = file.outputStream()
        Json.encodeToStream(entity.data, stream)
        stream.close()
    }

    override fun delete(id: Int) {
        storage.getFile("$id.note").delete()
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun all(where: (Identifiable<Note>) -> Boolean): List<Identifiable<Note>> {
        return storage.getFiles("notes")
            .map { e -> Identifiable(e.name.replace(".note", "").toInt(), e )}
            .map { e -> Identifiable(e.id, e.data.readText()) }
            .map {e -> Identifiable(e.id, Json.decodeFromString<Note>(e.data))}
            .filter(where)
    }

}