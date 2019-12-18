package hu.unideb.pedometer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.unideb.pedometer.data.History
import hu.unideb.pedometer.data.User

@Database(
    entities = [User::class, History::class],
    version = 1,
    exportSchema = false
)
abstract class PMDatabase: RoomDatabase() {

    abstract val historyDAO: HistoryDAO
    abstract val userDAO: UserDAO

    companion object {
        @Volatile
        private var INSTANCE: PMDatabase? = null

        fun getInstance(context: Context): PMDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PMDatabase::class.java,
                        "user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}