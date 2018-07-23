package com.siqiyan.lightlu.eyepetizercode.home.activity

import android.graphics.Color
import android.net.Uri
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoriesDetailContract
import com.siqiyan.lightlu.eyepetizercode.home.HomeFragment
import com.siqiyan.lightlu.eyepetizercode.home.presenter.CategoriesDetailPresenter
import com.siqiyan.lightlu.eyepetizercode.home.view.CategoryTagListFragment
import com.siqiyan.lightlu.eyepetizercode.net.entity.CategoryInfo
import com.siqiyan.lightlu.eyepetizercode.utils.AppBarStateChangeListener
import com.siqiyan.lightlu.eyepetizercode.utils.ImageLoad
import com.siqiyan.lightlu.eyepetizercode.utils.getScreenHeight
import com.siqiyan.lightlu.eyepetizercode.utils.getScreenWidth
import kotlinx.android.synthetic.main.activity_categories_tag_list.*
import java.io.File

class CategoriesTagListActivity : BaseActivity() , CategoriesDetailContract.CategoriesDetailView{
    override fun onCategoriesDetailSucc(result: CategoryInfo) {
        val list = result.tabInfo!!.tabList
        tabInfo = result.tabInfo
        var categoryInfo = result.categoryInfo as CategoryInfo.CategoryInfo
        val width = getScreenWidth(applicationContext)
        val height = getScreenHeight(applicationContext) / 3
        ImageLoad().load(categoryInfo.headerImage.toString(), iv_bg, width, height)
        toolbar.title = ""

        tv_name.text = categoryInfo.name.toString()
        tv_description.text = categoryInfo.description.toString()
        for (tab in list!!) {
            var category = HomeFragment.CategoryListEntity()
            category.category_id = tab.id.toString()
            category.name = tab.name.toString()
            mTitles.add(category)
            mTitle.add(tab.name.toString())
            var uri = Uri.parse(tab.apiUrl)
            var path: String = uri.path.toString().replace("/", File.separator)

            var id = uri.getQueryParameter("id")
            mFragments.add(CategoryTagListFragment(id, path))
        }

        mAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager.adapter = mAdapter
        val stringArray = mTitle.toArray(arrayOfNulls<String>(0))
        tab_layout.setViewPager(viewpager, stringArray as Array<String>)
        viewpager.offscreenPageLimit = list.size
        viewpager.currentItem = 0

        if (tabIndex < tab_layout.tabCount) {
            tab_layout.setCurrentTab(tabIndex, true)
        }

    }

    override fun onCategoriesDetailFail(error: Throwable?) {
    }

    override fun setPresenter(prsenter: CategoriesDetailContract.CategoriesDetailPresenter) {
        this.presenter=prsenter
    }

    private var mFragments = ArrayList<Fragment>()
    private var mTitles: ArrayList<HomeFragment.CategoryListEntity> = ArrayList()
    private var mTitle: ArrayList<String> = ArrayList()
    private var mAdapter: MyPagerAdapter? = null
    private var presenter: CategoriesDetailContract.CategoriesDetailPresenter? = null
    private var id: Int? = 0
    private var title: String? = null
    private var tabIndex: Int = 0
    var tabInfo: CategoryInfo.TabInfo? = null

    override fun initDate() {
      presenter!!.categoriesDetail(this.id!!)
    }

    override fun initView() {
        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getInt("id")
            title = bundle.getString("title")
            tabIndex = bundle.getInt("tabIndex", 0)
        }

        appbarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) =
                    when (state) {
                        State.EXPANDED -> {

                            //展开状态
                            toolbar.setNavigationIcon(R.drawable.ic_action_back_white)
                            tv_title.setTextColor(Color.WHITE)
                            tv_title.text = ""
                        }
                        State.COLLAPSED -> {

                            //折叠状态
                            toolbar.setNavigationIcon(R.drawable.ic_action_back)
                            tv_title.setTextColor(Color.BLACK)
                            tv_title.text = title

                        }
                        else -> {

                            //中间状态
                            toolbar.setNavigationIcon(R.drawable.ic_action_back_white)
                            tv_title.setTextColor(Color.WHITE)
                            tv_title.text = ""
                        }
                    }

        })
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() = finish()


    override fun getLayoutId(): Int =R.layout.activity_categories_tag_list

    override fun initPresenter() {
        CategoriesDetailPresenter(this)
    }
    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int = mFragments.size

        override fun getPageTitle(position: Int): CharSequence = mTitle[position]

        override fun getItem(position: Int): CategoryTagListFragment {
            val list = tabInfo!!.tabList
            val tab = list!![position] as CategoryInfo.TabList
            var uri = Uri.parse(tab.apiUrl)
            var path: String = uri.path.toString().replace("/", File.separator)

            var id = uri.getQueryParameter("id")
            return CategoryTagListFragment(id, path)
        }

        override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
    }

}
