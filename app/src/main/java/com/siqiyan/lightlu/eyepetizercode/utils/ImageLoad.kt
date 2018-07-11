package com.siqiyan.lightlu.eyepetizercode.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

import com.siqiyan.lightlu.eyepetizercode.R
import java.lang.ref.WeakReference

/**
 * 创建日期：2018/7/11 on 21:49
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class ImageLoad {
    open fun load(url:String,image:ImageView?){
        if(image==null){
            return
        }
        var request=RequestOptions().centerCrop()
                .placeholder(R.drawable.default_banner)
                .error(R.drawable.default_banner)
                .transform(CenterCrop())
                .format(DecodeFormat.PREFER_RGB_565)
                .priority(Priority.LOW)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context).load(url).apply(request).into(object :DrawableImageViewTarget(image){})

    }
    open fun load(url:String,image:ImageView?,width:Int,height:Int){
        if(image==null){
            return
        }
        var param=image.layoutParams
        param.width=width
        param.height=height
        image.layoutParams=param
        var request=RequestOptions().centerCrop()
                .placeholder(R.drawable.default_banner)
                .error(R.drawable.default_banner)
                .override(width,height)
                .transform(CenterCrop())
                .format(DecodeFormat.PREFER_RGB_565)
                .priority(Priority.LOW)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context).load(url).apply(request).into(object :DrawableImageViewTarget(image){})

    }

    open fun loadCircle(url: String, image: ImageView?) {
        if (image == null) return
        var lp = image.layoutParams
        lp.width = DensityUtil.dip2px(image.context.applicationContext, 40f)
        lp.height = DensityUtil.dip2px(image.context.applicationContext, 40f)
        image.layoutParams = lp
        var requestOptions = RequestOptions().centerCrop()
                .placeholder(R.drawable.default_icon)
                .error(R.drawable.default_icon)
                .format(DecodeFormat.PREFER_RGB_565)
                .transform(CenterCrop())
                .override(lp.width)
                .dontAnimate()
                .priority(Priority.LOW)
                .transform(CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context)
                .load(url)
                .apply(requestOptions)
                .into(object : DrawableImageViewTarget(image) {
                })
    }

    open fun load(url: String, image: ImageView?, width: Int, height: Int, round: Int) {
        if (image == null) return
        var lp = image.layoutParams
        lp.width = width
        lp.height = height
        image.layoutParams = lp
        var requestOptions = RequestOptions().centerCrop()
                .placeholder(R.drawable.default_banner)
                .error(R.drawable.default_banner)
                .format(DecodeFormat.PREFER_RGB_565)
                .override(width, height)
                .priority(Priority.LOW)
                .dontAnimate()
                .transforms(CenterCrop(), RoundedCorners(DensityUtil.dip2px(image.context.applicationContext, round.toFloat())))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context)
                .load(url)
                .apply(requestOptions)
                .into(object : DrawableImageViewTarget(image) {
                })
    }

    open fun loadRound(url: String, image: ImageView?, round: Int) {
        if (image == null) return
        var requestOptions = RequestOptions().centerCrop()
                .placeholder(R.drawable.default_banner)
                .error(R.drawable.default_banner)
                .format(DecodeFormat.PREFER_RGB_565)
                .priority(Priority.LOW)
                .dontAnimate()
                .transforms(CenterCrop(), RoundedCorners(DensityUtil.dip2px(image.context.applicationContext, round.toFloat())))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(image.context)
                .load(url)
                .apply(requestOptions)
                .into(object : DrawableImageViewTarget(image) {
                })
    }

    open fun clearCache(context: WeakReference<Context>) {
        Thread(Runnable {
            Glide.get(context.get()!!.applicationContext).clearDiskCache()
        }).start()
        Glide.get(context.get()!!.applicationContext).clearMemory()
    }
}