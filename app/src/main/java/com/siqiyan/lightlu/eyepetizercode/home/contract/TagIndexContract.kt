package com.siqiyan.lightlu.eyepetizercode.home.contract

import com.siqiyan.lightlu.eyepetizercode.base.BasePrsenter
import com.siqiyan.lightlu.eyepetizercode.base.BaseView
import com.siqiyan.lightlu.eyepetizercode.net.entity.TagIndex
import io.reactivex.disposables.Disposable

/**
 * 创建日期：2018/7/25 on 23:23
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TagIndexContract {


    interface TagIndexPresenter : BasePrsenter {
        fun tagIndex(id: Int): Disposable
    }

    interface TagIndexView : BaseView<TagIndexPresenter> {
        fun onTagIndexSucc(tagIndex: TagIndex)
        fun onTagIndexFail(throwable: Throwable?)
    }
}