package br.com.android.victorcs.mvvmarvel.data.remote

import br.com.android.victorcs.mvvmarvel.data.ZERO
import br.com.android.victorcs.mvvmarvel.data.model.error.ErrorModel
import br.com.android.victorcs.mvvmarvel.data.model.error.ErrorStatus
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

private const val UNAUTHORIZED = 401
private const val BAD_RESPONSE = 400
private const val TIME_OUT = "TIME OUT!!"
private const val CHECK_CONNECTION = "CHECK CONNECTION"

@Singleton
class CloudErrorMapper @Inject constructor(){

    fun mapToDomainError(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            is HttpException -> {
                if (throwable.code() == UNAUTHORIZED) {
                    ErrorModel(statusCode = ErrorStatus.UNAUTHORIZED, code = null, message = null)
                } else {
                    getHttpError(throwable.response()?.errorBody())
                }
            }

            is SocketTimeoutException -> {
                ErrorModel(TIME_OUT, ZERO, ErrorStatus.TIMEOUT)
            }

            is IOException -> {
                ErrorModel(CHECK_CONNECTION,ZERO, ErrorStatus.NO_CONNECTION)
            }

            is UnknownHostException -> {
                ErrorModel(CHECK_CONNECTION,ZERO, ErrorStatus.NO_CONNECTION)
            }
            else -> null
        }
        return errorModel!!
    }

    /**
     * Attempts to parse http response body and get error data from it.
     * @param body retrofit response body.
     * @return Returns an instance of [ErrorModel] with parsed data or UNKNOWN status.
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            val result = body?.string()
            Timber.e("getErrorMessage() errorBody = [$result]")
            ErrorModel("", BAD_RESPONSE, ErrorStatus.BAD_RESPONSE)
        } catch (ex: Throwable) {
            ex.printStackTrace()
            ErrorModel(statusCode = ErrorStatus.UNKNOWN, message = null, code = null)
        }

    }
}