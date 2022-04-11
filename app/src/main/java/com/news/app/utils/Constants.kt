package com.news.app.utils

import java.lang.StringBuilder

object Constants {


    val prefName="GreenPointPref"
    val baseURL="https://newsapi.org/"
    val API_KEY="c142e3ac2bfb4a1aa3dd71a4b7ba6768"
    val API_RES_KEY_FOR_LOCAL=" https://newsapi.org/v2/top-headlines?country=in&apiKey=c142e3ac2bfb4a1aa3dd71a4b7ba6768"
    @JvmStatic
    public fun String.convertToCamelCase(): String {
        var prev_char: Char = '$'
        val camelCase = StringBuilder()
        this.forEach {
            if (prev_char.equals(' ')) {
                camelCase.append(it.toUpperCase())
            } else if (!prev_char.equals(' ')) {
                camelCase.append(it)
            }
            prev_char = it

        }
        return camelCase.toString()
    }
    public fun MutableList<String>.getDummyList():MutableList<String>{
        with(this){
            add("Entertainment")
            add("Business")
            add("Cricket")
            add("Politics")
        }
        return this
    }

}