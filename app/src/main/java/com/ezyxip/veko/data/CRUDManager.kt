package com.ezyxip.veko.data

interface CRUDManager<Entity, Identifier> {
    fun add(entity: Entity): Identifier?
    fun get(id: Identifier): Entity
    fun update(entity: Identifiable<Entity>)
    fun delete(id: Identifier)
    fun all(where: (Identifiable<Entity>)->Boolean = {true}): List<Identifiable<Entity>>
}