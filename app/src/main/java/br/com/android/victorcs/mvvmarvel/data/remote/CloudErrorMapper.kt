package br.com.android.victorcs.mvvmarvel.data.remote

import br.com.android.victorcs.mvvmarvel.data.model.error.ErrorModel
import br.com.android.victorcs.mvvmarvel.data.model.error.ErrorStatus
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CloudErrorMapper @Inject constructor(private val gson: Gson) {

    fun mapToDomainError(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            is HttpException -> {
                if (throwable.code() == 401) {
                    ErrorModel(statusCode = ErrorStatus.UNAUTHORIZED, code = null, message = null)
                } else {
                    getHttpError(throwable.response()?.errorBody())
                }
            }

            is SocketTimeoutException -> {
                ErrorModel("TIME OUT!!",0, ErrorStatus.TIMEOUT)
            }

            is IOException -> {
                ErrorModel("CHECK CONNECTION",0, ErrorStatus.NO_CONNECTION)
            }

            is UnknownHostException -> {
                ErrorModel("CHECK CONNECTION",0, ErrorStatus.NO_CONNECTION)
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
            Timber.d("getErrorMessage() errorBody = [$result]")
            val json = Gson().fromJson(result, JsonObject::class.java)
            ErrorModel(json.toString(), 400, ErrorStatus.BAD_RESPONSE)
        } catch (ex: Throwable) {
            ex.printStackTrace()
            ErrorModel(statusCode = ErrorStatus.UNKNOWN, message = null, code = null)
        }

    }
}