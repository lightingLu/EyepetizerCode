package com.siqiyan.lightlu.eyepetizercode.home

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/9 on 23:45
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface CategoriesContract {
    interface CategoriesPresenter : BasePrsenter {
        fun categories(): Disposable
    }

    interface CategoriesView : BaseView<CategoriesPresenter> {
        fun onCategoriesSucc(result: List<Categories>)
        fun onCategoriesFail(error: Throwable?)
    }
}