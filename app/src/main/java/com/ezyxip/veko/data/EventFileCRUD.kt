package com.ezyxip.veko.data

import com.ezyxip.celli.services.IdResolver
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream

class EventFileCRUD(
    val storage: FileStorage = FileStorage(),
    val idResolver: IdResolver = IdResolver.bean
) : CRUDManager<Event, Int> {

    init{
        storage.addDir("events")
        storage.currentDir = "events"
    }
    @OptIn(ExperimentalSerializationApi::class)
    override fun add(entity: Event): Int {
        val id = idResolver.getNextId().toString()
        val file = storage.addFile("$id.event")
        val stream = file.outputStream()
        Json.encodeToStream(entity, stream)
        stream.close()
        return id.toInt()
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun get(id: Int): Event {
        val file = storage.getFile("$id.event")
        val stream = file.inputStream()
        val res = Json.decodeFromStream<Event>(stream)
        stream.close()
        return res
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun update(entity: Identifiable<Event>) {
        val file = storage.getFile("${entity.id}.event")
        val stream = file.outputStream()
        Json.encodeToStream(entity.data, stream)
        stream.close()
    }

    override fun delete(id: Int) {
        storage.getFile("$id.event").delete()
    }

    override fun all(where: (Identifiable<Event>) -> Boolean): List<Identifiable<Event>> {
        return storage.getFiles("events")
            .map { e -> Identifiable(e.name.replace(".event", "").toInt(), e )}
            .sortedBy { e -> e.data.lastModified() }
            .map { e -> Identifiable(e.id, e.data.readText()) }
            .map {e -> Identifiable(e.id, Json.decodeFromString<Event>(e.data))}
            .filter(where)
            .reversed()
    }
}