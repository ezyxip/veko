package com.ezyxip.celli.services

interface IdResolver {
    companion object{
        val bean: IdResolver by lazy { SimpleIdResolver() }
    }
    fun getCurrentId(): Int
    fun getNextId(): Int
}