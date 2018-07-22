package com.siqiyan.lightlu.eyepetizercode.home.view

import android.annotation.SuppressLint
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.home.CategoryTabListContract
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result

@SuppressLint("ValidFragment")
/**
 * 创建日期：2018/7/22 on 19:28
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoryTagListFragment(id: String, path: String):BaseFragment() ,CategoryTabListContract.CategoriesTagListView{
    override fun onCategoriesTagSucc(result: Result) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCategoriesTagFail(error: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(prsenter: CategoryTabListContract.CategoriesTagListPresenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initDate() {
    }


    override fun initView() {
    }

    override fun getLayoutId(): Int = R.layout.categor_tag_fragment
}