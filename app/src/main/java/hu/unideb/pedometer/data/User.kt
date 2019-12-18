package hu.unideb.pedometer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0,
    var username: String = "",
    var password: String = "",
    var email: String = "")
