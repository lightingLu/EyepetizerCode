package com.siqiyan.lightlu.eyepetizercode.net

/**
 * Created by moment on 16/5/24.
 */

interface CallBack<T> {
    fun onCompleted()

    fun onError(e: Throwable?)

    fun onNext(t: T)
}
