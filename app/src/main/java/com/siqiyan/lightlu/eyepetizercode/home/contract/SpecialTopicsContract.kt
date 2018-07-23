package com.siqiyan.lightlu.eyepetizercode.home.contract

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/23 on 23:15
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface SpecialTopicsContract {
    interface SpecialTopicsPresenter : BasePrsenter {
        fun specialTopics(start: Int, num: Int): Disposable
    }

    interface SpecialTopicsView : BaseView<SpecialTopicsPresenter> {
        fun onSpecialTopicsSucc(result: Result)
        fun onSpecialTopicsFail(throwable: Throwable?)
    }
}