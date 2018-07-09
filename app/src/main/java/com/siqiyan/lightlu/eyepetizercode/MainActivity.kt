package com.siqiyan.lightlu.eyepetizercode

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.siqiyan.lightlu.eyepetizercode.follow.FollowFragment
import com.siqiyan.lightlu.eyepetizercode.home.HomeFragment
import com.siqiyan.lightlu.eyepetizercode.mine.MineFragment
import com.siqiyan.lightlu.eyepetizercode.notify.NotifyFragment
import com.siqiyan.lightlu.sdkmanager.SdCardManager
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var homeFragment: HomeFragment?=null
    private var mineFragment: MineFragment?=null
    private var notifyFragment: NotifyFragment?=null
    private var followFragment: FollowFragment?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initFragment(savedInstanceState)

    }


    @SuppressLint("RestrictedApi")
    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState!=null){
            val mFragments:List<Fragment> =supportFragmentManager.fragments
            for (item in mFragments){
                if (item is HomeFragment){
                    homeFragment=item
                }
                if (item is NotifyFragment){
                    notifyFragment=item
                }
                if (item is FollowFragment){
                    followFragment=item
                }
                if (item is MineFragment){
                    mineFragment=item
                }
            }
        }else{
            homeFragment= HomeFragment()
            followFragment = FollowFragment()
            notifyFragment = NotifyFragment()
            mineFragment = MineFragment()
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.add(R.id.fl_content,homeFragment)
            beginTransaction.add(R.id.fl_content,followFragment)
            beginTransaction.add(R.id.fl_content,notifyFragment)
            beginTransaction.add(R.id.fl_content,mineFragment)
            beginTransaction.commit()

        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(followFragment)
                .hide(notifyFragment)
                .hide(mineFragment)
                .commit()

    }
    override fun onClick(v: View?) {
        clearState()
        when(v?.id){
            R.id.rb_home ->{
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(followFragment)
                        .hide(mineFragment)
                        .hide(notifyFragment)
                        .commit()
            }
            R.id.rb_follow -> {
                rb_follow.isChecked = true
                rb_follow.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(followFragment)
                        .hide(notifyFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .commit()
            }

            R.id.rb_notification -> {
                rb_notification.isChecked = true
                rb_notification.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(notifyFragment)
                        .hide(followFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .commit()
            }
            R.id.rb_mine -> {
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(mineFragment)
                        .hide(followFragment)
                        .hide(homeFragment)
                        .hide(notifyFragment)
                        .commit()
            }
        }


    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
        rb_notification.setTextColor(resources.getColor(R.color.gray))
        rb_follow.setTextColor(resources.getColor(R.color.gray))
    }

    private fun initView() = setRadioButton()

    private fun setRadioButton() {
        rb_home.isChecked = true
        rb_home.setTextColor(resources.getColor(R.color.black))
        rb_home.setOnClickListener(this)
        rb_notification.setOnClickListener(this)
        rb_follow.setOnClickListener(this)
        rb_mine.setOnClickListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            RxPermissions(this)
                    .request(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe({aBoolean ->
                        if (aBoolean){
                            if (SdCardManager.getInstance().isDiskAvailable) {
                                if (SdCardManager.getInstance().isNullPath) {
                                    SdCardManager.getInstance().changePath(SdCardManager.DownloadPath.SDCARD)
                                }
                            } else {
                                SdCardManager.getInstance().changePath(SdCardManager.DownloadPath.CACHE)
                            }
                        }else{
                            SdCardManager.getInstance().changePath(SdCardManager.DownloadPath.CACHE)

                        }
                        Toast.makeText(this, SdCardManager.getInstance().pathDir, Toast.LENGTH_SHORT).show()

                    },{})

        }


    }

    //再按一次退出
    private var firstTime: Long = 0
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                val secondTime = System.currentTimeMillis()
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                    firstTime = secondTime
                    return true
                } else {
                    System.exit(0)
                }
            }
        }
        return super.onKeyUp(keyCode, event)
    }

}
