package com.ezyxip.veko.data

import java.io.File

interface FileConfiguration {
    companion object{
        lateinit var appDir: File
    }
}