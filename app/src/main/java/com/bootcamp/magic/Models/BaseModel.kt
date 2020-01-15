package com.bootcamp.magic.Models

data class BaseModel<T>(var data: T?, val status: STATUS,val message:String?) {
    companion object {
        enum class STATUS {
            LOADING, SUCCESS,SUCCESS_STILL_HAVE_MORE, ERROR
        }
    }
}