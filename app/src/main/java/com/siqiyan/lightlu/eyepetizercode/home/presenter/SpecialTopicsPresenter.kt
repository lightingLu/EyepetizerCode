package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.SpecialTopicsContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/23 on 23:17
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class SpecialTopicsPresenter(var specialTopicsView: SpecialTopicsContract.SpecialTopicsView) : SpecialTopicsContract.SpecialTopicsPresenter {

    init {
        specialTopicsView.setPresenter(this)
    }

    override fun start() = Unit

    override fun specialTopics(start: Int, num: Int): Disposable
            = GetDataList.specialTopics(start, num, object : CallBack<Result> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = specialTopicsView.onSpecialTopicsFail(e)

        override fun onNext(t: Result) = specialTopicsView.onSpecialTopicsSucc(t)

    })

}