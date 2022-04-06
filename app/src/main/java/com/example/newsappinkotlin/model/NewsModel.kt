package com.example.newsappinkotlin.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "news_table")

data class NewsModel (
    @SerializedName("source")@Embedded val source: SourceModel,
    @SerializedName("author") var author:String?,
    @SerializedName("title") @PrimaryKey var title:String,
    @SerializedName("description") var desciption:String?,
    @SerializedName("url") var url:String?,
    @SerializedName("urlToImage") var urlToImage:String?,
    @SerializedName("publishedAt") var publishedAt:String?,
    @SerializedName("content") var content:String?
)