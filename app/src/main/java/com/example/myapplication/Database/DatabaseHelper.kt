package com.example.myapplication.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.constraintlayout.motion.widget.Debug
import com.example.myapplication.Model.User

class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, "saweMLia", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val queryUser = "CREATE TABLE User(" +
                "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "balance INTEGER NOT NULL" +
                ")"

        db?.execSQL(queryUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, v1: Int, v2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User")
        onCreate(db)
    }

    fun GetAllUsers() : ArrayList<User>{
        val users = ArrayList<User>()
        val db = readableDatabase

        val query = "SELECT * FROM User"
        val cursor = db?.rawQuery(query, null)

        if(cursor != null){
            while(cursor.moveToNext()){
                users.add(User(cursor.getString(cursor
                    .getColumnIndexOrThrow("username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
                    (cursor.getString(cursor
                        .getColumnIndexOrThrow("balance"))).toInt())
                )
            }
            cursor.close()
        }
        db.close()
        return users
    }

    fun getCurrUser(username: String, password: String): User? {
        val db = readableDatabase
        val query = "SELECT * FROM User WHERE username = ? AND password = ? LIMIT 1"
        val cursor = db.rawQuery(query, arrayOf(username, password))

        var user: User? = null


        if (cursor != null && cursor.moveToFirst()) {
            val fetchedUsername = cursor.getString(cursor.getColumnIndexOrThrow("username"))
            val fetchedPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            val fetchedBalance = cursor.getInt(cursor.getColumnIndexOrThrow("balance")).toInt()
            user = User(fetchedUsername, fetchedPassword, fetchedBalance)
        }

        cursor?.close()
        db.close()

        return user
    }

    fun InsertNewUser(newUser : User){
        val db = writableDatabase
        val contentVal : ContentValues = ContentValues().apply {
            put("username", newUser.username)
            put("password", newUser.password)
            put("balance", newUser.balance)
        }

        db.insert("User", null, contentVal)
        db.close()
    }

}