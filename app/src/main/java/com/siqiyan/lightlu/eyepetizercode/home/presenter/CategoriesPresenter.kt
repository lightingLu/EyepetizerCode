package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoriesContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/10 on 00:05
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoriesPresenter(var view: CategoriesContract.CategoriesView): CategoriesContract.CategoriesPresenter {
    init {
        view.setPresenter(this)
    }
    override fun start() = Unit

    override fun categories(): Disposable = GetDataList.categories(object :CallBack<List<Categories>>{
        override fun onCompleted() =Unit
        override fun onError(e: Throwable?) =view.onCategoriesFail(e)
        override fun onNext(t: List<Categories>) =view.onCategoriesSucc(t)
    })

}