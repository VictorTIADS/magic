package com.bootcamp.magic.Utils

import java.io.InputStreamReader

object MockedJsonReader {
    fun readmockedJson(path:String):String{
        this.javaClass.classLoader?.let {
            val reader = InputStreamReader(it.getResourceAsStream(path))
            val content = reader.readText()
            reader.close()
            return content
        } ?: kotlin.run {
            return ""
        }
    }
}