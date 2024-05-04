package com.ezyxip.veko.data

import java.io.File

class FileStorage (private val appDir: File = FileConfiguration.appDir) {
    constructor(dirName: String): this(File(dirName))

    init {
        if(appDir.isFile) throw Exception("Not a directory!")
        if(!appDir.exists()) appDir.mkdir()
    }
    var currentDir = "./"
        get() = field
        set(value){
            if(File(appDir, value).exists() && File(appDir, value).isDirectory)
                field = value
            else
                throw Exception("Non-existent directory selected")
        }
    fun addDir(name: String): Boolean{
        return File(appDir, currentDir + name).mkdir()
    }
    fun isExist(dirName: String): Boolean{
        return false
    }
    fun addFile(name: String, dirName: String = ""): Boolean{
        return false
    }
    fun getFiles(dir: String): List<File>{
        return listOf()
    }
    fun getFile(name: String, dirName: String = ""): File {
        return File("./")
    }
}