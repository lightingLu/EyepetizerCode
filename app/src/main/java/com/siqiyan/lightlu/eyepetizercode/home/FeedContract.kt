package com.siqiyan.lightlu.eyepetizercode.home

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/12 on 23:32
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface FeedContract {
    interface FeedPresenter : BasePrsenter {
        fun feed(date: Long): Disposable
    }

    interface FeedView : BaseView<FeedPresenter> {
        fun onFeedSucc(result: Result)
        fun onFeedFail(error: Throwable?)
    }
}