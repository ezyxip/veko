package com.ezyxip.veko.data

import com.ezyxip.celli.services.IdResolver

class NoteCRUD(
    val storage: FileStorage = FileStorage(),
    val idResolver: IdResolver = IdResolver.bean
) : CRUDManager<Identifiable<Note>, Int> {
    init{
        storage.addDir("notes")
        storage.currentDir = "notes"
    }

    override fun add(entity: Identifiable<Note>): Int? {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Identifiable<Note> {
        TODO("Not yet implemented")
    }

    override fun update(entity: Identifiable<Identifiable<Note>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(where: (Identifiable<Identifiable<Note>>) -> Boolean): List<Identifiable<Identifiable<Note>>> {
        TODO("Not yet implemented")
    }

    override fun page(count: Int): List<Identifiable<Identifiable<Note>>> {
        TODO("Not yet implemented")
    }

}