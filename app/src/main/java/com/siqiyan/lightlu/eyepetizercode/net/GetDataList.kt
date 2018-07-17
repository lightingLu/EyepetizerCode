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

    fun feed(date: Long, callBack: CallBack<Result>): Disposable = RetrofitUtils().with().build()
            .feed(date, 2)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })

    fun category(id: Int, start: Int, num: Int, callBack: CallBack<Result>): Disposable = RetrofitUtils().with().build()
            .category(id, start, num)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })

    fun follow(start: Int, num: Int, follow: Boolean, startId: Int, callBack: CallBack<Result>): Disposable = RetrofitUtils().with().build()
            .follow(start, num, follow, startId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })

    fun hot(callBack: CallBack<List<String>>): Disposable = RetrofitUtils().with().build()
            .hot()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })

    fun search(start: Int, num: Int, search: String, callBack: CallBack<Result>): Disposable = RetrofitUtils().with().build()
            .search(search, start, num)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> callBack.onNext(result) },
                    { throwable: Throwable -> callBack.onError(throwable) },
                    { callBack.onCompleted() })


}