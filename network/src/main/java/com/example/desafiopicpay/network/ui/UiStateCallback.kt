package com.example.desafiopicpay.network.ui

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response

class UiStateCallback<R>(private val mutableLiveData: MutableLiveData<UiState<R>>) :
    RetryCallback<R>() {

    companion object {
        private const val GENERIC_ERROR_MSG = "Ops, something went wrong"
    }

    override fun onRetry() {
        mutableLiveData.postValue(UiLoading)
    }

    override fun onFailure(call: Call<R>, t: Throwable) {
        super.onFailure(call, t)
        mutableLiveData.postValue(UiError(ErrorData(Throwable(GENERIC_ERROR_MSG, t))))
    }

    override fun onResponse(call: Call<R>, response: Response<R>) {
        super.onResponse(call, response)
        val uiState = createUiState(response)

        mutableLiveData.postValue(uiState)
    }

    private fun createUiState(response: Response<R>): UiState<R> {
        return if (response.isSuccessful)
            createSuccessResponse(response)
        else
            UiError(createErrorData(response))
    }

    private fun createSuccessResponse(response: Response<R>): UiState<R> {
        val body = response.body()

        return if (body != null)
            UiSuccess(body)
        else
            UiSuccess(Unit as R)
    }

    private fun createErrorData(response: Response<R>): ErrorData {

        val errorBody = response
            .errorBody()?.string()
        val throwable = createExceptionMessage(response, errorBody)

        return ErrorData(throwable, response.code(), errorBody)
    }

    private fun <R> createExceptionMessage(
        response: Response<R>,
        errorBody: String?
    ): Exception {
        val message = getErrorBodyMessage(errorBody)

        return IllegalStateException(message?.message ?: response.message())
    }

    private fun getErrorBodyMessage(errorBody: String?): ErrorBody? =
        errorBody?.run {
            try {
                Gson().fromJson<ErrorBody>(this, ErrorBody::class.java)
            } catch (jsonSyntaxException: JsonSyntaxException) {
                null
            }
        }

    private class ErrorBody(
        @SerializedName("message")
        val message: String
    )
}
