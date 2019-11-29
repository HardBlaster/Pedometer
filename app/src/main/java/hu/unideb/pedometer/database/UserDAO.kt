package hu.unideb.pedometer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hu.unideb.pedometer.data.User

@Dao
interface UserDAO {

    @Insert
    fun insert(user: User)

    @Query("select * from user where username = :userName")
    fun getUser(userName: String) : LiveData<User>
}