package com.siqiyan.lightlu.eyepetizercode.home.contract

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.RankList
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/23 on 00:16
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface RankListContract {
    interface RankListPresenter : BasePrsenter {
        fun rankList(): Disposable
    }

    interface RankListView : BaseView<RankListPresenter> {
        fun onRankListSucc(result: RankList)
        fun onRankListFail(error: Throwable?)
    }
}