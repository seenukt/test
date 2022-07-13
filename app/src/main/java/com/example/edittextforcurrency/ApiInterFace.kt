package com.example.edittextforcurrency

import com.example.edittextforcurrency.model.BaseModel
import com.example.edittextforcurrency.model.Dummy
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterFace {

    @GET("passenger/")
    suspend fun pagingData(@Query("page")page:Int,@Query("size")size:Int=20 ):Response<BaseModel>

}