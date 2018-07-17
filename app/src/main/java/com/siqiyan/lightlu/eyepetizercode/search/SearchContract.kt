package com.siqiyan.lightlu.eyepetizercode.search

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/17 on 22:43
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class SearchContract {

    interface SearchPresenter : BasePrsenter {
        fun search(start: Int, num: Int, search: String): Disposable
    }

    interface SearchView : BaseView<SearchPresenter> {
        fun onSearchSucc(result: Result)
    }

    interface SearchHotPresenter : BasePrsenter {
        fun hot(): Disposable
    }

    interface SearchHotView : BaseView<SearchHotPresenter> {
        fun onHotSucc(tags: List<String>)
    }
}