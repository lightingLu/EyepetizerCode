package com.siqiyan.lightlu.eyepetizercode.search

import android.support.v4.app.FragmentManager
import android.view.View
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.view.FlowLayout
import com.siqiyan.lightlu.eyepetizercode.view.SearchView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() ,SearchContract.SearchHotView{
    override fun onHotSucc(tags: List<String>) {
    }

    override fun setPresenter(prsenter: SearchContract.SearchHotPresenter) {
    }

    lateinit var presenters: SearchContract.SearchHotPresenter
    internal var fragment: SearchFragment? = null
    internal var managers: FragmentManager? = null

    override fun initDate() {
    }

    override fun initView() {
        search_view.addOnClearClickListener(object :SearchView.ClearButtonClick{
            override fun clear() {
                search_view.setText("")
                if (fragment!=null){
                    hideFragment(fragment!!)
                    fl_content.visibility = View.GONE
                    rl_search.visibility = View.VISIBLE
                }

            }

        })

        flow_layout.setOnFlowItemClickListener(object :FlowLayout.OnFlowItemClickListener{
            override fun onItemClick(txt: String) {
                search_view.setText(txt)
                search(txt)
            }

        })

    }

    private fun search(txt: String) {


    }


    private fun hideFragment(fragment: SearchFragment) {
        if (managers==null){
            managers=supportFragmentManager
        }
        val transaction =managers!!.beginTransaction()
        if (fragment!=null){
            if (fragment.isAdded){
                transaction.remove(fragment)
            }
            if (fl_content.childCount>0){
                fl_content.removeAllViews()
            }
        }

    }

    override fun getLayoutId(): Int = R.layout.activity_search


    override fun initPresenter() {
        SearchHotPresenter(this)
    }

}
