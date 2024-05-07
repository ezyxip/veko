package com.ezyxip.veko.data

import com.ezyxip.celli.services.SimpleIdResolver
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleIdResolverTest {

    @BeforeAll
    fun setup(){
        File("./testdir").mkdir()
    }

    @AfterAll
    fun clear(){
        File("./testdir").deleteRecursively()
    }

    @Test
    fun test() {
        val resolver = SimpleIdResolver("testdir")
        assertEquals(1, resolver.getNextId())
        assertEquals(2, resolver.getNextId())
        assertEquals(3, resolver.getNextId())

        assertEquals(3, resolver.getCurrentId())

        val resolver2 = SimpleIdResolver("testdir")
        assertEquals(4, resolver2.getNextId())
        assertEquals(5, resolver2.getNextId())
        assertEquals(6, resolver2.getNextId())

        assertEquals(6, resolver2.getCurrentId())
    }
}