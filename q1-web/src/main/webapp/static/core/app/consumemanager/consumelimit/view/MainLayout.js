Ext.define("core.consumemanager.consumelimit.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: "widget.consumemanager.consumelimit.mainlayout",
    funCode: "consumelimit_main",
    detCode: "consumelimit_detail",
    detLayout: "consumemanager.consumelimit.detaillayout",
    /** 关联此视图控制器 */
    controller: 'consumemanager.consumelimit.maincontroller',
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'consumemanager.consumelimit.othercontroller',
    funData: {
        action: comm.get("baseUrl") + "/XfXeSet", //请求Action
        pkName: "id",
        defaultObj: {
        },
        tabConfig:{         
            titleField:'cardTypeName',  
            addTitle:'添加消费限额',
            editTitle:'消费限额参数设置',
            detailTitle:'',
            addXtype:null,                                     //2018/1/3新加入，用于在公共方法中打开指定的界面
            editXtype:null,                                     //2018/1/3新加入，用于在公共方法中打开指定的界面
            detailXtype:null,    //2018/1/3新加入，用于在公共方法中打开指定的界面
        }
    },
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "consumemanager.consumelimit.maingrid",
    }]
})