package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoryContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/12 on 23:42
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoryPresenter(var categoryView: CategoryContract.CategoryView) : CategoryContract.CategoryPresenter {

    init {
        categoryView.setPresenter(this)
    }

    override fun start() = Unit

    override fun category(id: Int, start: Int, num: Int): Disposable =
            GetDataList.category(id, start, num, object : CallBack<Result> {
                override fun onCompleted() = Unit

                override fun onError(e: Throwable?) = categoryView.onCategoryFail(e)

                override fun onNext(t: Result) = categoryView.onCategorySucc(t)

            })

}