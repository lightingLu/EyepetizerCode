package com.siqiyan.lightlu.eyepetizercode.follow

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/15 on 22:44
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface FollowContract {
    interface FollowPresenter :BasePrsenter{
        fun followView(start: Int, num: Int, follow: Boolean, startId: Int):Disposable
    }

    interface FollowView:BaseView<FollowPresenter>{
        fun onFollowSuc(result: Result)
        fun onFollowFail(e:Throwable?)
    }

}