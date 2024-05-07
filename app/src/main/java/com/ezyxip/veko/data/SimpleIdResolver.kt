package com.ezyxip.celli.services


import com.ezyxip.veko.data.FileConfiguration
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception
import java.util.Properties

class SimpleIdResolver (private val appDir: File = FileConfiguration.appDir): IdResolver {
    constructor(dir: String) : this(File(dir))
    companion object{
        const val dir = "incrementIdResolver"
        const val file = "id.conf"
        const val startValue = 0
        const val currentIdPropName = "currentId"
        const val confComment = "IdResolver property file"
    }
    private var currentValue: Int
    init {
        val resolverDir = checkDir()
        val confFile = checkConfigurationFile(resolverDir)
        val prop = Properties()
        val stream = FileReader(confFile)
        prop.load(stream)
        stream.close()
        prop.getProperty(currentIdPropName) ?: Exception("Невалидный файл настроек для IdResolver")
        currentValue = prop.getProperty(currentIdPropName).toInt()
    }
    private fun checkDir(): File{
        val resolverDir = File(appDir, dir)
        if(!resolverDir.exists()){
            resolverDir.mkdir()
        }
        return resolverDir
    }
    private fun checkConfigurationFile(resolverDir: File): File{
        val configurationFile = File(resolverDir, file)
        if(!configurationFile.exists()){
            configurationFile.createNewFile()
            val prop = Properties()
            prop.setProperty(currentIdPropName, startValue.toString())
            val stream = FileWriter(configurationFile)
            prop.store(stream, confComment)
            stream.close()
        }
        return configurationFile
    }
    override fun getCurrentId(): Int = currentValue

    override fun getNextId(): Int {
        currentValue++
        val prop = Properties()
        prop.setProperty(currentIdPropName, currentValue.toString())
        val resolverDir = File(appDir, dir)
        val configurationFile = File(resolverDir, file)
        val stream = FileWriter(configurationFile)
        prop.store(stream, confComment)
        stream.close()
        return currentValue
    }

}