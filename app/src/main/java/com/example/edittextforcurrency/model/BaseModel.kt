package com.example.edittextforcurrency.model

import com.google.gson.annotations.SerializedName

data class BaseModel (

    @SerializedName("totalPassengers") val totalPassengers : Int,
    @SerializedName("totalPages") val totalPages : Int,
    @SerializedName("data") val data : List<Data>
)
data class Data (

    @SerializedName("_id") val _id : String,
    @SerializedName("name") val name : String,
    @SerializedName("trips") val trips : Int,
    @SerializedName("airline") val airline : List<Airline>,
    @SerializedName("__v") val __v : Int
)
data class Airline (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("country") val country : String,
    @SerializedName("logo") val logo : String,
    @SerializedName("slogan") val slogan : String,
    @SerializedName("head_quaters") val head_quaters : String,
    @SerializedName("website") val website : String,
    @SerializedName("established") val established : Int
)