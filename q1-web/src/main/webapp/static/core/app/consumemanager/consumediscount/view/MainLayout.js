Ext.define("core.consumemanager.consumediscount.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: "widget.consumemanager.consumediscount.mainlayout",
    funCode: "consumediscount_main",
    detCode: "consumediscount_detail",
    detLayout: "consumemanager.consumediscount.detaillayout",
    /** 关联此视图控制器 */
    controller: 'consumemanager.consumediscount.maincontroller',
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'consumemanager.consumediscount.othercontroller',
    funData: {
        action: comm.get("baseUrl") + "/XfRateSet", //请求Action
        pkName: "id",
        defaultObj: {
        },
        tabConfig:{         
            titleField:'cardTypeName',  
            addTitle:'添加消费折扣',
            editTitle:'消费折扣费率参数设置',
            detailTitle:'消费折扣详情'
        }
    },
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "consumemanager.consumediscount.maingrid",
    }]
})