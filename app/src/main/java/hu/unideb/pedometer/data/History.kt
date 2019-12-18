package hu.unideb.pedometer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey
    val userID: Int,
    val steps: Int
)