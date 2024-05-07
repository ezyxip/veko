package com.ezyxip.veko.data

import java.io.File

class FileStorage (private val appDir: File = FileConfiguration.appDir) {
    constructor(dirName: String): this(File(dirName))

    init {
        if(appDir.isFile) throw Exception("Not a directory!")
        if(!appDir.exists()) appDir.mkdir()
    }
    var currentDir = ""
        get() = field
        set(value){
            if(File(appDir, value).exists() && File(appDir, value).isDirectory)
                field = value
            else
                throw Exception("Non-existent directory selected")
        }

    private val directory
        get() = File(appDir, currentDir)
    fun addDir(name: String){
        val res = File(directory, name).mkdir()
        if(!res) throw Exception("Failed to create directory")
    }
    fun exists(name: String): Boolean{
        return File(directory, name).exists()
    }
    fun addFile(name: String, dirName: String = ""): File{
        val file = if(dirName.isEmpty())
            File(directory, name)
        else
            File(appDir, "$dirName/$name")

        if(!file.createNewFile()) throw Exception("Failed to create file")
        return file
    }
    fun getFiles(dir: String = ""): List<File>{
        val res = if (dir.isEmpty())
            directory.listFiles()
        else{
            val target = File(appDir, dir)
            if(!target.exists()) throw Exception("The specified directory does not exist")
            target.listFiles()
        }
        if(res == null) return listOf()
        return res.toList()
    }
    fun getFile(name: String, dirName: String = ""): File {
        val res = if(dirName.isEmpty())
            File(directory, name)
        else
            File(appDir, "$dirName/$name")

        if (!res.exists()) throw Exception("The file cannot be found")

        return res
    }
}