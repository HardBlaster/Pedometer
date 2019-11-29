package hu.unideb.pedometer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.unideb.pedometer.data.History
import hu.unideb.pedometer.data.User

@Database(
    entities = [User::class, History::class],
    version = 1
)
abstract class PMDatabase: RoomDatabase() {

    abstract fun historyDAO(): HistoryDAO
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var instance: PMDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PMDatabase::class.java,
                "pedometer.db").build()
    }
}