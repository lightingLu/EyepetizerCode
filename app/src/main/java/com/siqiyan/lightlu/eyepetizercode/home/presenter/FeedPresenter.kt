package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.FeedContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/12 on 23:34
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class FeedPresenter(var feedView: FeedContract.FeedView) : FeedContract.FeedPresenter {

    init {
        feedView.setPresenter(this)
    }

    override fun start() = Unit

    override fun feed(date: Long): Disposable = GetDataList.feed(date, object : CallBack<Result> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = feedView.onFeedFail(e)

        override fun onNext(t: Result) = feedView.onFeedSucc(t)

    })

}