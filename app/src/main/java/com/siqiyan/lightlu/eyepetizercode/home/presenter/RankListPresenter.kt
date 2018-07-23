package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.RankListContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.RankList
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/23 on 00:18
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class RankListPresenter(var rankListDetailView: RankListContract.RankListView) : RankListContract.RankListPresenter {

    init {
        rankListDetailView.setPresenter(this)
    }

    override fun start() = Unit

    override fun rankList(): Disposable = GetDataList.rankList(object : CallBack<RankList> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = rankListDetailView.onRankListFail(e)

        override fun onNext(t: RankList) = rankListDetailView.onRankListSucc(t)

    })
}