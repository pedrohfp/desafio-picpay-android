package com.example.desafiopicpay.network.ui

abstract class UseCase<in P, R> {

    operator fun invoke(parameters: P, result: UiStateLiveData<R>) {
        result.postValue(UiLoading)
        try {
            create(parameters).let { useCaseResponse ->
                result.postValue(UiSuccess(useCaseResponse))
            }
        } catch (exception: IllegalStateException) {
            result.postValue(UiError(ErrorData(exception)))
        }
    }
    protected abstract fun create(parameters: P): R
}
