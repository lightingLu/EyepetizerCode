package com.siqiyan.lightlu.eyepetizercode.follow

import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/15 on 23:53
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class FollowPresenter(var followView: FollowContract.FollowView) :FollowContract.FollowPresenter {

    init {
        followView.setPresenter(this)
    }

    override fun followView(start: Int, num: Int, follow: Boolean, startId: Int): Disposable =
            GetDataList.follow(start, num, follow, startId, object : CallBack<Result> {
                override fun onCompleted() = Unit

                override fun onError(e: Throwable?) = followView.onFollowFail(e)

                override fun onNext(t: Result) = followView.onFollowSuc(t)

            })

    override fun start() = Unit

}