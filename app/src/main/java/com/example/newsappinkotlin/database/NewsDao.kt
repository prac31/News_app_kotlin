package com.example.newsappinkotlin.database

import androidx.room.*
import com.example.newsappinkotlin.model.NewsModel

@Dao
interface NewsDao {
    // inserts entity in database
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insertNews(news:NewsModel)

    // retrieves entity from database
    @Query("SELECT * FROM news_table")
    fun getAllSavedNews():List<NewsModel>

    // checks the count if 0(not present), 1(present)
    @Query("SELECT COUNT(title) FROM news_table WHERE  title= :ti")
    fun getCount(ti:String):Int

    // deletes entity from database
    @Delete
    fun delete(news: NewsModel)
}

