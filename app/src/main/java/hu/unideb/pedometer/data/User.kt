package hu.unideb.pedometer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val username: String,
    val password: String,
    val email: String)
{
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0
}