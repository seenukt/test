package com.example.edittextforcurrency

object Utils {

    fun dataCheck(model: checkModel): Boolean {
        if (model.email.isEmpty()) {
            return false
        }
        if (model.password.isEmpty()) {
            return false
        }
        return true
    }
}
data class checkModel(val email:String,val password:String)