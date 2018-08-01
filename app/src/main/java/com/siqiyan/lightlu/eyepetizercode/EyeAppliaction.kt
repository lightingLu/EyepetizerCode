package com.siqiyan.lightlu.eyepetizercode

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

/**
 * 创建日期：2018/7/8 on 20:58
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class EyeAppliaction : Application() {


    override fun onCreate() {
        super.onCreate()
        Thread{
            kotlin.run {
                CrashReport.initCrashReport(this, "3ec6d448-fa91-44b2-83e2-51f3d776cea2", false);
            }
        }
    }


}