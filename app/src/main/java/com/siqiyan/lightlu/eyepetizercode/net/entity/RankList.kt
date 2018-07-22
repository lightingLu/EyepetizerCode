package com.siqiyan.lightlu.eyepetizercode.net.entity

/**
 * 创建日期：2018/7/8 on 21:30
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class RankList {
    var tabInfo: TabInfo? = null

    class TabInfo {
        var defaultIdx: Int = 0
        var tabList: List<TabList>? = null
    }

    class TabList {
        var id: Int = 0
        var name: String? = null
        var apiUrl: String? = null
    }
}