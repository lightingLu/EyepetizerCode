package com.siqiyan.lightlu.eyepetizercode.home.presenter

import com.siqiyan.lightlu.eyepetizercode.home.contract.TagIndexContract
import com.siqiyan.lightlu.eyepetizercode.net.CallBack
import com.siqiyan.lightlu.eyepetizercode.net.GetDataList
import com.siqiyan.lightlu.eyepetizercode.net.entity.TagIndex
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/25 on 23:22
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TagIndexPresenter(var tagIndexView: TagIndexContract.TagIndexView) : TagIndexContract.TagIndexPresenter {

    init {
        tagIndexView.setPresenter(this)
    }

    override fun start() = Unit

    override fun tagIndex(id: Int): Disposable = GetDataList.tagIndex(id, object : CallBack<TagIndex> {
        override fun onCompleted() = Unit

        override fun onError(e: Throwable?) = tagIndexView.onTagIndexFail(e)

        override fun onNext(t: TagIndex) = tagIndexView.onTagIndexSucc(t)

    })

}