package com.siqiyan.lightlu.eyepetizercode.search

import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.view.FlowLayout
import com.siqiyan.lightlu.eyepetizercode.view.SearchView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() ,SearchContract.SearchHotView{
    override fun onHotSucc(tags: List<String>) =flow_layout.setTagView(this,tags.toList())

    override fun setPresenter(prsenter: SearchContract.SearchHotPresenter) {
        this.presenters=prsenter
    }

    lateinit var presenters: SearchContract.SearchHotPresenter
    internal var fragment: SearchFragment? = null
    internal var managers: FragmentManager? = null

    override fun initDate() {
        presenters.hot()
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
        search_view.setOnEditorActionListener { v, actionId, event ->
            //当点击键盘搜索键时
            if (actionId== EditorInfo.IME_ACTION_SEARCH){
                val text = search_view.text.toString().trim{
                    it<=' '
                }
                if (!TextUtils.isEmpty(text)){
                    search(text)
                }else{
                    Toast.makeText(this@SearchActivity,"请输入搜索内容",Toast.LENGTH_SHORT).show()
                }
                return@setOnEditorActionListener true
            }
            false
        }

        tv_search_cancel.setOnClickListener {
            if (fragment!=null){
                hideFragment(fragment!!)
                fl_content.visibility=View.GONE
                rl_search.visibility =View.VISIBLE
            }
        }

    }

    /**
     * 搜索内容
     */
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


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top)
    }
}
