package com.siqiyan.lightlu.eyepetizercode.home.contract

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/11 on 22:31
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface DiscoverContract {
    interface DiscoverPresenter:BasePrsenter{
       fun discover():Disposable
    }

    interface DiscoverView:BaseView<DiscoverPresenter>{
        fun onDiscoverySucc(result: Result)
        fun onDiscoveryFail(error: Throwable?)
    }

}