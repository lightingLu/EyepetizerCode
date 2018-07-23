package com.siqiyan.lightlu.eyepetizercode.home.contract

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/22 on 21:09
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface CategoryTabListContract {

    interface CategoriesTagListPresenter : BasePrsenter {
        fun categoriesTagList(path: String, map: HashMap<String, String>): Disposable
    }

    interface CategoriesTagListView : BaseView<CategoriesTagListPresenter> {
        fun onCategoriesTagSucc(result: Result)
        fun onCategoriesTagFail(error: Throwable?)
    }
}