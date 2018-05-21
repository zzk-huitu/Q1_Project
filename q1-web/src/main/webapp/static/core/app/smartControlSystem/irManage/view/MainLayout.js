Ext.define("core.smartControlSystem.irManage.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.smartControlSystem.irManage.mainLayout',
    
    requires: [   
    	"core.smartControlSystem.irManage.controller.MainController",       
    ],
    /** 关联此视图控制器 */
    controller: 'smartControlSystem.irManage.mainController',
    /** 页面代码定义 */
    funCode: "irManage_main",
    detCode: "irManage_detail",
    detLayout: "smartControlSystem.irManage.detailLayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'smartControlSystem.irManage.otherController',

    layout:'fit',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtTerm", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'termName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加红外参数',
        	editTitle:'编辑红外参数',
        	detailTitle:'红外参数详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:'x',
    layout:'border',
    
    items: [{
        split: true,
        xtype: "smartControlSystem.irManage.roomInfoTree",
        region: "west",
        width: 250
    }, {
        xtype: "smartControlSystem.irManage.mainGrid",
        region: "center"
    }]
})