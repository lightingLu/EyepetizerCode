package com.siqiyan.lightlu.eyepetizercode.home

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/12 on 23:41
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface CategoryContract {
    interface CategoryPresenter : BasePrsenter {
        fun category(id: Int, start: Int, num: Int): Disposable
    }

    interface CategoryView : BaseView<CategoryPresenter> {
        fun onCategorySucc(result: Result)
        fun onCategoryFail(error: Throwable?)
    }
}