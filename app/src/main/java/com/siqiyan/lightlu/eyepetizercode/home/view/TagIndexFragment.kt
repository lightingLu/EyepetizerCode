package com.siqiyan.lightlu.eyepetizercode.home.view

import android.annotation.SuppressLint
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoryTabListContract
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result

@SuppressLint("ValidFragment")
/**
 * 创建日期：2018/7/25 on 23:58
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TagIndexFragment (id: String, path: String) : BaseFragment(), CategoryTabListContract.CategoriesTagListView {


    override fun onCategoriesTagSucc(result: Result) {
    }

    override fun onCategoriesTagFail(error: Throwable?) {
    }

    override fun setPresenter(prsenter: CategoryTabListContract.CategoriesTagListPresenter) {
    }

    override fun initDate() {
    }

    override fun initView() {
  }

    override fun getLayoutId(): Int = R.layout.tagindex_fragment
}