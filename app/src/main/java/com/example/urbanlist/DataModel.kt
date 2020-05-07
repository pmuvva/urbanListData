package com.example.urbanlist

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("list")
    val data:List<DataModel1>
)

data class DataModel1(
    val definition:String,
    val permalink:String,
    val thumbs_up: Int,
    @SerializedName("list")
    val sound_urls:List<String>,
    val author:String,
    val word:String,
    val defid:Int,
    val current_vote:String,
    val written_on:String,
    val example:String,
    val thumbs_down: Int

)

data class UpDown(val up:Int,val dn:Int)