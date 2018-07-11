package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.DiscoverContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/11 on 22:41
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class DiscoverPresenter(var discoverView: DiscoverContract.DiscoverView):DiscoverContract.DiscoverPresenter {
    init {
        discoverView.setPresenter(this)
    }
    override fun discover(): Disposable =GetDataList.discovery(object :CallBack<Result>{
        override fun onCompleted() =Unit
        override fun onError(e: Throwable?) =discoverView.onDiscoveryFail(e)
        override fun onNext(t: Result)=discoverView.onDiscoverySucc(t)
    })

    override fun start() =Unit
}