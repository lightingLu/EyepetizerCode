package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoriesDetailContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.CategoryInfo
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/22 on 18:30
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoriesDetailPresenter(var view: CategoriesDetailContract.CategoriesDetailView): CategoriesDetailContract.CategoriesDetailPresenter {
    override fun categoriesDetail(id: Int): Disposable =
            GetDataList.categoriesDetail(id, object : CallBack<CategoryInfo> {
                override fun onCompleted() = Unit

                override fun onError(e: Throwable?) = view.onCategoriesDetailFail(e)

                override fun onNext(t: CategoryInfo) = view.onCategoriesDetailSucc(t)

            })

    override fun start() =Unit

    init {
        view.setPresenter(this)
    }

}