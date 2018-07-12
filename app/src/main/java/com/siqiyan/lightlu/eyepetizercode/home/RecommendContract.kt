package com.siqiyan.lightlu.eyepetizercode.home

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable



interface RecommendContract {
    interface RecommendPresenter : BasePrsenter {
        fun allRec(page: Int): Disposable
    }

    interface RecommendView : BaseView<RecommendPresenter> {
        fun onRecommendSucc(result: Result)
        fun onRecommendFail(error: Throwable?)
    }
}