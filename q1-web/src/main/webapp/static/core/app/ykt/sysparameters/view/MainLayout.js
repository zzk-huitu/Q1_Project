Ext.define("core.ykt.sysparameters.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.sysparameters.mainlayout',
    
    requires: [   
    	"core.ykt.sysparameters.controller.MainController",
        "core.ykt.sysparameters.view.MainLayout",
        "core.ykt.sysparameters.view.DetailLayout",
        "core.ykt.sysparameters.view.MainGrid",
        "core.ykt.sysparameters.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'ykt.sysparameters.maincontroller',
    /** 页面代码定义 */
    funCode: "sysparameters_main",
    detCode: "sysparameters_detail",
    detLayout: "ykt.sysparameters.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'ykt.sysparameters.othercontroller',
    layout:'fit',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtSysParameter", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'sysParamName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加参数',
        	editTitle:'编辑参数',
        	detailTitle:'参数详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "ykt.sysparameters.maingrid"
    }]
})