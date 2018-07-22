package com.siqiyan.lightlu.eyepetizercode.home

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.CategoryInfo
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/22 on 18:28
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface CategoriesDetailContract {
    interface CategoriesDetailPresenter : BasePrsenter {
        fun categoriesDetail(id: Int): Disposable
    }

    interface CategoriesDetailView : BaseView<CategoriesDetailPresenter> {
        fun onCategoriesDetailSucc(result: CategoryInfo)
        fun onCategoriesDetailFail(error: Throwable?)
    }
}