package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.RankListDetailContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/23 on 22:23
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class RankListDetailPresenter (var rankListDetailView: RankListDetailContract.RankListView) : RankListDetailContract.RankListPresenter {

    init {
        rankListDetailView.setPresenter(this)
    }

    override fun start() = Unit

    override fun rankListVideo(path: String, map: HashMap<String, String>): Disposable
            = GetDataList.rankListVideo(path, map, object : CallBack<Result> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = rankListDetailView.onRankListFail(e)

        override fun onNext(t: Result) = rankListDetailView.onRankListSucc(t)

    })

}