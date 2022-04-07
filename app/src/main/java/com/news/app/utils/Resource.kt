package com.news.app.utils

sealed class Resource<T>(val data:T?=null,val message: String?=null){
    class  isLoading<T>():Resource<T>()
     class Success<T>(data:T?):Resource<T>(data)
     class Eror<T>(message:String?):Resource<T>(message=message)
}

