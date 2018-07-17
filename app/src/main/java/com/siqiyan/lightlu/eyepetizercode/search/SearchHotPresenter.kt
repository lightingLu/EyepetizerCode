package com.siqiyan.lightlu.eyepetizercode.search

import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/17 on 22:47
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class SearchHotPresenter(var searchHotView: SearchContract.SearchHotView):SearchContract.SearchHotPresenter {

    init {
        searchHotView.setPresenter(this)
    }
    override fun hot(): Disposable =GetDataList.hot(object :CallBack<List<String>> {
        override fun onCompleted() =Unit

        override fun onError(e: Throwable?) =Unit

        override fun onNext(t: List<String>) =searchHotView.onHotSucc(t)
    })

    override fun start()=Unit
}