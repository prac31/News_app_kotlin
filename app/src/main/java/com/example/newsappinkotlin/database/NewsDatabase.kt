package com.example.newsappinkotlin.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import com.example.newsappinkotlin.model.NewsModel

// defining database entities
@Database(entities = [NewsModel::class],version = 1,exportSchema = false)
abstract class NewsDatabase :RoomDatabase() {
    companion object
    {
        private var newsDatabase:NewsDatabase?=null

        fun getSavedItems(context: Context) :NewsDatabase
        {
            if (newsDatabase==null)
                newsDatabase=Room.databaseBuilder(context,NewsDatabase::class.java,"NewsDB").allowMainThreadQueries().build()
            return newsDatabase!!
        }
    }
// allows access to NewsDao
    abstract fun getNewsDao():NewsDao
}
