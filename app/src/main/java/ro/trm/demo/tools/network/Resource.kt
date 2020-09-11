package com.gxorg.god.tools.network

data class Resource<out T>(val status: Status, val data: T?, val error:Throwable?, val message: String?="") {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable?, msg: String, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error,
                msg
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}