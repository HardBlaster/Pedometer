package hu.unideb.pedometer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0,
    var username: String = "",
    var password: String = "",
    var email: String = "") {

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as User

        if (this.username != other.username) return false

        return true
    }
}