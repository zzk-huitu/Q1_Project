Ext.define("core.consumemanager.consumeterm.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: "widget.consumemanager.consumeterm.mainlayout",
    funCode: "consumeterm",
    detCode: "consumeterm",
    detLayout: "consumemanager.consumeterm.detaillayout",
    /** 关联此视图控制器 */
    controller: 'consumemanager.consumeterm.maincontroller',
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'consumemanager.consumeterm.othercontroller',
    funData: {
        action: comm.get("baseUrl") + "/", //请求Action
        pkName: "id",
        defaultObj: {
        },
        tabConfig:{         
            titleField:'',  
            addTitle:'',
            editTitle:'编辑消费设备',
            detailTitle:'',
            addXtype:null,                                     //2018/1/3新加入，用于在公共方法中打开指定的界面
            editXtype:null,                                     //2018/1/3新加入，用于在公共方法中打开指定的界面
            detailXtype:null,    //2018/1/3新加入，用于在公共方法中打开指定的界面
        }
    },
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "consumemanager.consumeterm.maingrid",
    }]
})