package hu.unideb.pedometer.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    val userID: Int,
    @Embedded(prefix = "date_")
    @PrimaryKey
    val date: SimpleDate,
    val steps: Int
)