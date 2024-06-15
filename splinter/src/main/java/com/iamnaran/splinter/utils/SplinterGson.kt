package com.iamnaran.splinter.utils

import com.google.gson.GsonBuilder

object SplinterGson {

    fun gsonBuilder(): GsonBuilder {
        return GsonBuilder().setPrettyPrinting()
    }
}