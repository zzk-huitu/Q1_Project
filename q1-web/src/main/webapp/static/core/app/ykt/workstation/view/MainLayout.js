Ext.define("core.ykt.workstation.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.workstation.mainlayout',
    
    requires: [   
    	"core.ykt.workstation.controller.MainController",
        "core.ykt.workstation.view.MainLayout",
        "core.ykt.workstation.view.DetailLayout",
        "core.ykt.workstation.view.MainGrid",
        "core.ykt.workstation.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'ykt.workstation.maincontroller',
    /** 页面代码定义 */
    funCode: "workstation_main",
    detCode: "workstation_detail",
    detLayout: "ykt.workstation.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'ykt.workstation.othercontroller',
    layout:'fit',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtWorkStation", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'workStationName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加工作站',
        	editTitle:'编辑工作站',
        	detailTitle:'工作站详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "ykt.workstation.maingrid"
    }]
})