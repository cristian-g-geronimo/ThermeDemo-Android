package ro.trm.demo.tools.network

import com.gxorg.god.tools.network.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

class ResponseHandler {
    suspend fun <T : Any> handleRequest(block: suspend () -> T): Resource<T> {
        return try {
            val response = block()
            return handleSuccess(response)
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    private fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(e, getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(
                e,
                getErrorMessage(ErrorCodes.SocketTimeOut.code),
                null
            )
            else -> Resource.error(e, e.message ?: "", null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong $code"
        }
    }
}