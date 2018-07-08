package com.siqiyan.lightlu.eyepetizercode.net.entity

/**
 * 创建日期：2018/7/8 on 21:26
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class Result {
    var itemList: List<ItemList>? = null
    var count: Int = 0
    var total: Int = 0
    var nextPageUrl: String? = null
    var adExist: Boolean = false
    var updateTime: Object? = null
    var refreshCount: Int? = 0
    var lastStartId: Int? = 0

    class ItemList {
        var type: String? = null
        var tag: String? = null
        var id: Int = 0
        var adIndex: Int = 0
        /**
         * horizontalScrollCard、textCard、briefCard、followCard、
         * videoSmallCard、squareCardCollection、videoCollectionWithBrief、DynamicInfoCard、
         * banner
         */
        var data: Any? = null

        override fun toString(): String {
            return "ItemList(type=$type, tag=$tag, id=$id, adIndex=$adIndex, data=$data)"
        }


    }

    override fun toString(): String {
        return "Discovery(itemList=${itemList.toString()}, count=$count, total=$total, nextPageUrl=$nextPageUrl, adExist=$adExist)"
    }

}