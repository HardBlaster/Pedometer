package hu.unideb.pedometer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.unideb.pedometer.data.History

@Dao
interface HistoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(history: History)

    @Query("select * from history where userID = :ID")
    fun getHistory(ID: Int) : LiveData<History>

}