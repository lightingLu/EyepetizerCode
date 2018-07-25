package com.siqiyan.lightlu.eyepetizercode.net.entity

/**
 * 创建日期：2018/7/8 on 21:31
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TagIndex {
    var tabInfo: TabInfo? = null
    var tagInfo: TagInfo? = null

    class TabInfo {
        var defaultIdx: Int = 0
        var tabList: List<TabList>? = null
    }

    class TabList {
        var id: Int = 0
        var name: String? = null
        var apiUrl: String? = null
    }

    class TagInfo {
        var dataType: String? = null
        var id: Int = 0
        var name: String? = null
        var description: String? = null
        var headerImage: String? = null
        var bgPicture: String? = null
        var actionUrl: String? = null
        var recType: Int = 0
        var tagFollowCount: Int = 0
        var tagVideoCount: Int = 0
        var tagDynamicCount: Int = 0
        var follow: Follow? = null
    }

    class Follow {
        var itemType: String? = null
        var itemId: Int = 0
        var followed: Boolean = false
    }
}