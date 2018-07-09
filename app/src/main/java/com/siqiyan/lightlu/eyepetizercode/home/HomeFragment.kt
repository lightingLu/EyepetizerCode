package com.siqiyan.lightlu.eyepetizercode.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Toast
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.event.ChangeTabEvent
import com.siqiyan.lightlu.eyepetizercode.event.CurrentTagEvent
import com.siqiyan.lightlu.eyepetizercode.event.RefreshEvent
import com.siqiyan.lightlu.eyepetizercode.event.RxBus
import com.siqiyan.lightlu.eyepetizercode.home.presenter.CategoriesPresenter
import com.siqiyan.lightlu.eyepetizercode.home.view.CategoryFragment
import com.siqiyan.lightlu.eyepetizercode.home.view.DiscoveryFragment
import com.siqiyan.lightlu.eyepetizercode.home.view.FeedFragment
import com.siqiyan.lightlu.eyepetizercode.home.view.RecommendFragment
import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * 创建日期：2018/7/8 on 22:13
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class HomeFragment :BaseFragment(),CategoriesContract.CategoriesView ,ViewPager.OnPageChangeListener{

    private var mFragments = ArrayList<Fragment>()
    var tabId: Long = 10001
    var str = arrayOf("发现","推荐","日报")
    private var mTitles: ArrayList<CategoryListEntity> = ArrayList()
    private var mTitle: ArrayList<String> = ArrayList()
    private var mAdapter: MyPagerAdapter? = null
    private var presenter: CategoriesContract.CategoriesPresenter? = null
    private var currentIndex = ""

    override fun initPresenter() {
        CategoriesPresenter(this)
    }

    override fun setPresenter(prsenter: CategoriesContract.CategoriesPresenter) {
        this.presenter=prsenter
    }

    override fun onPageScrollStateChanged(state: Int) =Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) =Unit

    override fun onPageSelected(position: Int) {
        if (mAdapter != null && mTitles != null && mTitles!!.size - 1 >= position) {
            val entity = mTitles[position]
            currentIndex = entity.category_id.toString()
            RxBus.default!!.post(CurrentTagEvent(currentIndex, false))
        }
    }

    override fun initEvent() {
        RxBus.default!!.toObservable(RefreshEvent::class.java)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(activity, "refresh", Toast.LENGTH_SHORT).show() }

        RxBus.default!!.toObservable(ChangeTabEvent::class.java)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> tab_layout.setCurrentTab(result.tagIndex, true) })


    }
    override fun onCategoriesSucc(result: List<Categories>) {
        var list = result.toMutableList()
        var tabs: MutableList<Categories> = ArrayList<Categories>() as MutableList<Categories>
        for (tab in str) {
            val cate = Categories()
            cate.name = tab
            cate.id = tabId
            tabId++
            tabs.add(cate)
        }

        list.addAll(0, tabs)
        for (cate in list) {
            when {
                cate.id == 10001L -> mFragments.add(DiscoveryFragment())
                cate.id == 10002L -> mFragments.add(RecommendFragment())
                cate.id == 10003L -> mFragments.add(FeedFragment())
                else -> mFragments.add(CategoryFragment(cate.id.toString()))
            }
            var category = CategoryListEntity()
            category.category_id = cate.id.toString()
            category.name = cate.name
            mTitles.add(category)
            mTitle.add(cate.name.toString())
        }
        mAdapter = MyPagerAdapter(fm = fragmentManager!!)
        viewpager.adapter = mAdapter
        val stringArray = mTitle.toArray(arrayOfNulls<String>(0))
        tab_layout.setViewPager(viewpager, stringArray as Array<String>)
        viewpager.offscreenPageLimit = 3
        viewpager.currentItem = 0
        viewpager.addOnPageChangeListener(this)

    }

    override fun onCategoriesFail(error: Throwable?) = Unit



    override fun initDate() {
      presenter!!.categories()

    }

    override fun initView() {
        iv_home.setColorFilter(resources.getColor(R.color.black))
        tv_bar_title.visibility = View.GONE
        tab_layout.visibility = View.VISIBLE
        iv_home.setOnClickListener {
//            var intent = Intent(activity, TabSwitchActivity::class.java)
//            startActivity(intent)
        }
        iv_search.setOnClickListener {
//            var intent = Intent(activity, SearchActivity::class.java)
//            startActivity(intent)
//            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_in_top)
        }
    }


    override fun getLayoutId()= R.layout.home_fragment


    override fun onDestroyView() {
        mFragments!!.clear()
        mAdapter = null
        viewpager!!.adapter = null
        clearFindViewByIdCache()
        super.onDestroyView()
    }


    class CategoryListEntity {

        var category_id: String? = null
        var name: String? = null
    }


    private inner class MyPagerAdapter(fm:FragmentManager): FragmentStatePagerAdapter(fm){
        override fun getItem(position: Int): Fragment =mFragments[position]

        override fun getCount(): Int =mFragments.size

        override fun getPageTitle(position: Int): CharSequence? =mTitle[position]

        override fun getItemPosition(`object`: Any): Int =PagerAdapter.POSITION_NONE

    }
}