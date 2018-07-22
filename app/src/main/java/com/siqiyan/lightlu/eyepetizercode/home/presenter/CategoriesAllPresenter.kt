package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.CategoriesAllContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/22 on 23:23
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoriesAllPresenter (val categoriesAllView: CategoriesAllContract.CategoriesAllView) : CategoriesAllContract.CategoriesAllPresenter {

    init {
        categoriesAllView.setPresenter(this)
    }

    override fun start() = Unit

    override fun categoriesAll(): Disposable = GetDataList.categoriesAll(object : CallBack<Result> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = categoriesAllView.onCategoriesAllFail(e)

        override fun onNext(t: Result) = categoriesAllView.onCategoriesAllSucc(t)

    })
}