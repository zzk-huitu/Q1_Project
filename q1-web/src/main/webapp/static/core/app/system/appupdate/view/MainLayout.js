Ext.define("core.system.appUpdate.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.system.appUpdate.mainLayout',
    funCode: "appUpdate_main",
    detCode: 'appUpdate_detail',
    detLayout: 'system.appUpdate.detailLayout',
    funData: {
        action: comm.get('baseUrl') + "/PtAppInfo", //请求Action
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'appTitle',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
            addTitle:'APP上传',
            editTitle:'',
            detailTitle:''
        }
    },
    requires: [    
        "core.system.appUpdate.controller.MainController",
    ],
      /*关联此视图控制器*/
    controller: 'system.appUpdate.mainController',
    
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'system.appUpdate.otherController',

    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "system.appUpdate.mainGrid",        
    }]
  
});