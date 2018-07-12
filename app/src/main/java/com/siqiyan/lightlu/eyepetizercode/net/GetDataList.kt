package com.siqiyan.lightlu.eyepetizercode.net

import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 创建日期：2018/7/10 on 00:08
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
object GetDataList {


    fun categories(callBack: CallBack<List<Categories>>): Disposable = RetrofitUtils().with().build()
            .categories()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })

    fun discovery(callBack: CallBack<Result>): Disposable = RetrofitUtils().with().build()
            .discovery()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                callBack.onNext(result)
            }, { t: Throwable? ->
                callBack.onError(t)

            }) {
                callBack.onCompleted()
            }


    fun allRec(page: Int, callBack: CallBack<Result>): Disposable = RetrofitUtils().with().build()
            .allRec(page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })


}