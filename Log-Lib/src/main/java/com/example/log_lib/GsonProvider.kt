package com.example.log_lib

import com.google.gson.Gson

object GsonProvider {

    fun getGson(): Gson{
        return Gson()
    }

}