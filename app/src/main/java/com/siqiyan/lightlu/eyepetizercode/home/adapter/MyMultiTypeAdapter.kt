package com.siqiyan.lightlu.eyepetizercode.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.utils.getMultiType

/**
 * 创建日期：2018/7/11 on 22:36
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class MyMultiTypeAdapter(list:ArrayList<Result.ItemList>,var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var datas: ArrayList<Result.ItemList> = list
    private var mContext: Context = context


    enum class ITEM_TYPE(val type:String){
        ITEM_TEXTCARD("textCard"),
        ITEM_BRIEFCARD("briefCard"),
        ITEM_DYNAMIC_INFOCARD("DynamicInfoCard"),
        ITEM_HORICONTAL_SCROLLCARD("horizontalScrollCard"),
        ITEM_FOLLOWCARD("followCard"),
        ITEM_VIDEOSMALLCARD("videoSmallCard"),
        ITEM_SQUARECARD_COLLECTION("squareCardCollection"),
        ITEM_VIDEOCOLLECTION_WITHBRIEF("videoCollectionWithBrief"),
        ITEM_BANNER("banner"),
        ITEM_BANNER2("banner2"),
        ITEM_VIDEO("video"),
        ITEM_VIDEOCOLLECTION_OFHORISCROLLCARD("videoCollectionOfHorizontalScrollCard"),
        ITEM_TEXTHEADER("textHeader"),
        ITEM_TEXTFOOTER("textFooter")
    }
    fun clearAll()=this.datas.clear()
    fun addAllData(list:ArrayList<Result.ItemList>?){
        if (list==null){
            return
        }
        datas.addAll(list)
    }

    //创建新View，被LayoutManager所调用
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            createMyViewHolder(viewGroup, viewType)


    //将数据与界面进行绑定的操作
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) =
            bindViewHolder(mContext, datas, viewHolder, position)

    override fun getItemViewType(position: Int): Int = getMultiType(position, datas)

    //获取数据的数量
    override fun getItemCount(): Int = datas.size


}