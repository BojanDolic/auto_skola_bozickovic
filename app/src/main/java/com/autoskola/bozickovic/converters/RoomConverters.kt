package com.autoskola.bozickovic.converters

import androidx.room.TypeConverter
import java.lang.StringBuilder

public class RoomConverters {

    @TypeConverter
    fun convertIntListToString(list: List<Int>): String {
        val builder = StringBuilder()
        for(item in list) {
            builder.append("$item,")
        }
        builder.deleteCharAt(builder.length -1)

        return builder.toString()
    }

    @TypeConverter
    fun convertFromStringToListInt(string: String): List<Int> {
        val stringList = string.split(",")
        var list: MutableList<Int> = mutableListOf()

        stringList.forEach {
            list.add(it.toInt())
        }
        return list
    }

    @TypeConverter
    fun convertListToString(list: List<String>): String {

        val builder = StringBuilder()
        for(item in list) {
            builder.append("$item,")
        }
        builder.deleteCharAt(builder.length -1)

        return builder.toString()
    }

    @TypeConverter
    fun convertFromStringToList(string: String): List<String> = string.split(",")

    @TypeConverter
    fun convertFromBooleanToInt(bool: Boolean): Int {
        return if(bool) { 1 } else { 0 }
    }

    @TypeConverter
    fun convertIntToBoolean(int: Int): Boolean {
        return int == 1
    }

}
