package com.example.newsappinkotlin.model

import com.google.gson.annotations.SerializedName

// object in the NewsModel
data class SourceModel (
   @SerializedName("id") var id:String?,
   @SerializedName("name") var name:String?
)