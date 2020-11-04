package com.wcc.whatdidilearn.data

import androidx.room.TypeConverter
import com.wcc.whatdidilearn.R
import com.wcc.whatdidilearn.entities.Level

class Converters {
    @TypeConverter
    fun levelToInt(level: Level): Int {
        return level.color
    }

    @TypeConverter
    fun intToLevel(color: Int): Level {
        return when(color) {
            R.color.purple_200 -> Level.LOW
            R.color.purple_500 -> Level.MEDIUM
            else -> Level.HIGH
        }
    }
}