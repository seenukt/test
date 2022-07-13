package com.example.edittextforcurrency.repo

import com.example.edittextforcurrency.ApiInterFace
import com.example.edittextforcurrency.model.BaseModel
import com.example.edittextforcurrency.model.Dummy
import retrofit2.Response

class PagingRepo constructor(private  val api:ApiInterFace) {

    suspend fun getData(pageNumber:Int, size:Int): Response<BaseModel> {
       return api.pagingData(pageNumber,size)
    }
}