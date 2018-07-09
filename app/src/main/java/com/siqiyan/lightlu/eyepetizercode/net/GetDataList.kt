package com.siqiyan.lightlu.eyepetizercode.net

import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
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
}