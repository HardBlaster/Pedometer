package hu.unideb.pedometer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context): SQLiteOpenHelper(context, dbName, factory, version) {

    companion object {
        internal const val dbName = "userDB"
        internal val factory = null
        internal const val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table user(" +
                "id integer primary key autoincrement, " +
                "username varchar(20) unique, " +
                "email varchar(30) unique, " +
                "password varchar(32))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUserData(username: String, email: String, password: String) {
        val db: SQLiteDatabase = writableDatabase
        val user: ContentValues = ContentValues()

        user.put("username", username)
        user.put("email", email)
        user.put("password", password)

        db.insert("user", null, user)
        db.close()
    }

    fun registeredUser(username: String, password: String): Boolean {
        val db: SQLiteDatabase = writableDatabase
        val query = "select * " +
                    "from user " +
                    "where username = \"$username\" and password = \"$password\";"

        val cursor = db.rawQuery(query, null)

        return if(cursor.count <= 0) {

            cursor.close()
            false

        } else {

            cursor.close()
            true

        }
    }

    fun usernameExists(username: String): Boolean {
        val db: SQLiteDatabase = writableDatabase
        val query = "select * " +
                    "from user " +
                    "where username = \"$username;\""

        val cursor = db.rawQuery(query, null)

        return if(cursor.count <= 0) {

            cursor.close()
            false

        } else {

            cursor.close()
            true

        }
    }

    fun emailExists(email: String): Boolean {
        val db: SQLiteDatabase = writableDatabase
        val query = "select * " +
                    "from user " +
                    "where email = \"$email;\""

        val cursor = db.rawQuery(query, null)

        return if(cursor.count <= 0) {

            cursor.close()
            false

        } else {

            cursor.close()
            true

        }
    }

}