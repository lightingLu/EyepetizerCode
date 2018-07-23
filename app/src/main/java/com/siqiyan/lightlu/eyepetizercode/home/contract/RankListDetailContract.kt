package com.siqiyan.lightlu.eyepetizercode.home.contract

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/23 on 22:20
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface RankListDetailContract{

    interface RankListPresenter : BasePrsenter {
        fun rankListVideo(path: String, map: HashMap<String, String>): Disposable
    }

    interface RankListView : BaseView<RankListPresenter> {
        fun onRankListSucc(result: Result)
        fun onRankListFail(error: Throwable?)
    }

}