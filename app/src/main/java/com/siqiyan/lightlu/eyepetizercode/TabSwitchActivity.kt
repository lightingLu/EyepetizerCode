package com.siqiyan.lightlu.eyepetizercode

import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.home.CategoriesContract
import com.siqiyan.lightlu.eyepetizercode.home.CategoryContract
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result

/**
 * 创建日期：2018/7/16 on 00:22
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TabSwitchActivity :BaseActivity(),CategoryContract.CategoryView {
    private var presenter: CategoriesContract.CategoriesPresenter? = null
    var adapter: TabSwitchAdapter? = null
    override fun initDate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int =R.layout.tab_switch_activity

    override fun onCategorySucc(result: Result) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCategoryFail(error: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(prsenter: CategoryContract.CategoryPresenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}