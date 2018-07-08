package com.siqiyan.lightlu.eyepetizercode.ad

import android.content.Intent
import android.graphics.Typeface
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import com.siqiyan.lightlu.eyepetizercode.MainActivity
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ad.*

class AdActivity : BaseActivity() {
    override fun initDate() {
        val scaleAnimation = ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration=2000
        val animationSet =AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.duration=2000
        bg!!.startAnimation(animationSet)
        animationSet.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent= Intent(this@AdActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })


    }

    override fun initView() {
        val createFromAsset = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_english_intro!!.typeface=createFromAsset
    }


    override fun getLayoutId()= R.layout.activity_ad


}
