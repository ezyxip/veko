package com.ezyxip.veko.data

interface CRUDManager<Entity, Identifier> {
    fun add(entity: Entity): Boolean
    fun get(id: Identifier): Entity
    fun update(entity: Entity): Boolean
    fun delete(id: Identifier): Boolean
}