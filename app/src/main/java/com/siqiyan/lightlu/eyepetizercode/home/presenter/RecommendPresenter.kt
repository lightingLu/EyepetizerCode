package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.RecommendContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/12 on 23:18
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class RecommendPresenter(var recommendView: RecommendContract.RecommendView) : RecommendContract.RecommendPresenter {

    init {
        recommendView.setPresenter(this)
    }

    override fun start() = Unit

    override fun allRec(page: Int): Disposable = GetDataList.allRec(page, object : CallBack<Result> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = recommendView.onRecommendFail(e)

        override fun onNext(t: Result) = recommendView.onRecommendSucc(t)

    })

}