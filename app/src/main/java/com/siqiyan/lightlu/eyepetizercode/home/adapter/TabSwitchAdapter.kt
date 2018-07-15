package com.siqiyan.lightlu.eyepetizercode.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
import com.siqiyan.lightlu.eyepetizercode.utils.ImageLoad
import com.siqiyan.lightlu.eyepetizercode.utils.ItemTouchHelperAdapter
import com.siqiyan.lightlu.eyepetizercode.utils.ItemTouchHelperViewHolder
import kotlinx.android.synthetic.main.tab_switch_item.view.*
import java.util.*

/**
 * 创建日期：2018/7/16 on 00:25
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TabSwitchAdapter(var data: ArrayList<Categories>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.tab_switch_item, parent, false)
        return ItemTabSwitchHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var categorie = datas[position]
        var holder = holder as ItemTabSwitchHolder
        holder.tv_tab_title!!.text = "#" + categorie.name
        holder.tv_tab_des!!.text = categorie.description
        ImageLoad().loadRound(categorie.headerImage!!, holder.iv_tab_switch_icon, 2)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(datas, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        datas.removeAt(position)
        notifyItemRemoved(position)
    }

    private var datas: ArrayList<Categories> = data

    fun clearAll() = this.datas.clear()

    fun addAll(data: ArrayList<Categories>?) {
        if (data == null) {
            return
        }
        this.datas.addAll(data)
    }


    override fun getItemCount(): Int {
        return datas.size
    }


    class ItemTabSwitchHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
        override fun onItemSelected() {

        }

        override fun onItemClear() {
        }

        var iv_tab_switch_icon: ImageView? = itemView.iv_tab_switch_icon
        var tv_tab_title: TextView? = itemView.tv_tab_title
        var tv_tab_des: TextView? = itemView.tv_tab_des
    }
}