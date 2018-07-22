package com.siqiyan.lightlu.eyepetizercode.home

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/22 on 23:23
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface CategoriesAllContract {

    interface CategoriesAllPresenter : BasePrsenter {
        fun categoriesAll(): Disposable
    }

    interface CategoriesAllView : BaseView<CategoriesAllPresenter> {
        fun onCategoriesAllSucc(result: Result)
        fun onCategoriesAllFail(error: Throwable?)
    }
}