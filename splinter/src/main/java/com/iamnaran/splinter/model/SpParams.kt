package com.iamnaran.splinter.model

class SpParams {
    private val params: MutableMap<String, Any> = mutableMapOf()

    fun putString(key: String, value: String) {
        params[key] = value
    }

    fun putInt(key: String, value: Int) {
        params[key] = value
    }

    fun putBoolean(key: String, value: Boolean) {
        params[key] = value
    }

    fun putLong(key: String, value: Long) {
        params[key] = value
    }

    fun putDouble(key: String, value: Double) {
        params[key] = value
    }

    fun toMap(): Map<String, Any> {
        return params.toMap()
    }
}