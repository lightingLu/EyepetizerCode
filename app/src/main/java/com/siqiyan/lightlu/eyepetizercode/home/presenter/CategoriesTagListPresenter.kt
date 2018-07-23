package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoryTabListContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/22 on 21:09
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoriesTagListPresenter(var view: CategoryTabListContract.CategoriesTagListView) : CategoryTabListContract.CategoriesTagListPresenter {
    init {
        view.setPresenter(this)
    }

    override fun categoriesTagList(path: String, map: HashMap<String, String>): Disposable = GetDataList.categoriesTagList(path, map, object : CallBack<Result> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = view.onCategoriesTagFail(e)

        override fun onNext(t: Result) = view.onCategoriesTagSucc(t)
    })


    override fun start() = Unit
}