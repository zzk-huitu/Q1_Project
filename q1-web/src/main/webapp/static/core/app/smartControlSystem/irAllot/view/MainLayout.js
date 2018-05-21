Ext.define("core.smartControlSystem.irAllot.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.smartControlSystem.irAllot.mainLayout',
    
    requires: [   
    "core.smartControlSystem.irAllot.controller.MainController",       
    ],
    /** 关联此视图控制器 */
    controller: 'smartControlSystem.irAllot.mainController',
    /** 页面代码定义 */
    funCode: "irAllot_main",
    detCode: 'irAllot_detail',
    detLayout: "smartControlSystem.irAllot.detailLayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'smartControlSystem.irAllot.otherController',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtTerm", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
        	addTitle:'分配红外设备',
        	editTitle:'编辑红外设备',
        	detailTitle:'红外设备详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:'x',
    layout:'border',
    
    items: [{
        split: true,
        xtype: "smartControlSystem.irAllot.roomInfoTree",
        region: "west",
        width:250
    }, {
        xtype: "smartControlSystem.irAllot.mainGrid",
        region: "center"
    }]
})