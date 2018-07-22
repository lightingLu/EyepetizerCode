package com.siqiyan.lightlu.eyepetizercode.net.entity

/**
 * 创建日期：2018/7/8 on 21:30
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoryInfo {

    var categoryInfo: CategoryInfo? = null
    var tabInfo: TabInfo? = null

    class CategoryInfo {
        var dataType: String? = null
        var id: Long? = null
        var name: String? = null
        var description: String? = null
        var headerImage: String? = null
        var actionUrl: String? = null
        var follow: Follow? = null

    }

    class Follow {
        var itemType: String? = null
        var itemId: Int? = null
        var followed: Boolean = false
    }

    class TabInfo {
        var defaultIdx: Int? = 0
        var tabList: List<TabList>? = null

    }

    class TabList {
        var id: Int = 0
        var name: String? = null
        var apiUrl: String? = null
    }
}