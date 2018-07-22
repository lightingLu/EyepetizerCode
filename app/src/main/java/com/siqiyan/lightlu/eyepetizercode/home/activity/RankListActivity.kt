package com.siqiyan.lightlu.eyepetizercode.home.activity

import android.graphics.Typeface
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.home.HomeFragment
import com.siqiyan.lightlu.eyepetizercode.home.RankListContract
import com.siqiyan.lightlu.eyepetizercode.home.presenter.RankListPresenter
import com.siqiyan.lightlu.eyepetizercode.home.view.RankListFragment
import com.siqiyan.lightlu.eyepetizercode.net.entity.RankList
import com.siqiyan.lightlu.eyepetizercode.utils.UriUtils
import kotlinx.android.synthetic.main.activity_rank_list.*
import java.io.File

/**
 * 全部排行界面
 */
class RankListActivity : BaseActivity() , RankListContract.RankListView {

    private var mFragments = ArrayList<Fragment>()
    private var mTitles: ArrayList<HomeFragment.CategoryListEntity> = ArrayList()
    private var mTitle: ArrayList<String> = ArrayList()
    private var mAdapter: MyPagerAdapter? = null
    private var presenter: RankListContract.RankListPresenter? = null


    override fun initPresenter() {
        RankListPresenter(this)
    }
    override fun onRankListSucc(result: RankList) {
        val list = result.tabInfo!!.tabList
        tabInfo = result.tabInfo

        for (tab in list!!) {
            var category = HomeFragment.CategoryListEntity()
            category.category_id = tab.id.toString()
            category.name = tab.name.toString()
            mTitles.add(category)
            mTitle.add(tab.name.toString())
            var uri = Uri.parse(tab.apiUrl)
            var path: String = uri.path.toString().replace("/", File.separator)

            mFragments.add(RankListFragment(path, UriUtils().parseCategoriesTagListUri(tab.apiUrl!!).map))
        }
        mAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager.adapter = mAdapter
        val stringArray = mTitle.toArray(arrayOfNulls<String>(0))
        tab_layout.setViewPager(viewpager, stringArray as Array<String>)
        viewpager.offscreenPageLimit = list.size
        viewpager.currentItem = 0
    }

    override fun onRankListFail(error: Throwable?) {
    }

    override fun setPresenter(prsenter: RankListContract.RankListPresenter) {
        this.presenter = prsenter
    }

    override fun initDate() {
        presenter!!.rankList()

    }

    override fun initView() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        tv_title.typeface = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf")

    }

    override fun getLayoutId(): Int =R.layout.activity_rank_list
    override fun onBackPressed() = finish()

    var tabInfo: RankList.TabInfo? = null


    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int = mFragments.size

        override fun getPageTitle(position: Int): CharSequence = mTitle[position]

        override fun getItem(position: Int): Fragment {
            val list = tabInfo!!.tabList
            val tab = list!![position] as RankList.TabList
            var uri = Uri.parse(tab.apiUrl)
            var path: String = uri.path.toString().replace("/", File.separator)

            return RankListFragment(path, UriUtils().parseCategoriesTagListUri(tab.apiUrl!!).map)
        }

        override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
    }
}
