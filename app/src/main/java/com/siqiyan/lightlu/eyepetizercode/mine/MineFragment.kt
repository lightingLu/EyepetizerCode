package com.siqiyan.lightlu.eyepetizercode.mine

import android.widget.Toast
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.sdkmanager.PathDialog
import com.siqiyan.lightlu.sdkmanager.SdCardManager
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.mine_fragment.*

/**
 * 创建日期：2018/7/8 on 22:13
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class MineFragment :BaseFragment() {

    override fun initDate() =Unit

    override fun initView() {
        ll_download.setOnClickListener {
            showPathDialog()
        }
    }

    private fun showPathDialog() {
        RxPermissions(activity!!)
                .request(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe({ aBoolean ->
                    if (aBoolean!!) {
                        val dialog = PathDialog(activity)
                        dialog.setOnPathChangeListener {
                            getDownloadPath()
                            dialog.dismiss()
                        }
                        dialog.show()
                        dialog.setCanceledOnTouchOutside(true)
                    } else {
                        Toast.makeText(activity, "无此权限，无法打开此功能！", Toast.LENGTH_SHORT).show()
                    }
                }) { }
    }

    private fun getDownloadPath() {
        if (SdCardManager.getInstance().isDiskNow) {
            val builder = StringBuilder(activity!!.getString(R.string.download_path_dialog_sdcard) + ":")
            builder.append(SdCardManager.getInstance().diskDownloadDir + "")
            tv_download.text = builder.toString()
        } else {
            val builder = StringBuilder(activity!!.getString(R.string.download_path_dialog_phone) + ":")
            builder.append(SdCardManager.getInstance().cacheDownloadDir + "")
            tv_download.text = builder.toString()
        }
    }


    override fun getLayoutId()= R.layout.mine_fragment
}