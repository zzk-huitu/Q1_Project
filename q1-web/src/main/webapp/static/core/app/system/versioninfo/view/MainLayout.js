Ext.define("core.system.versioninfo.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.system.versioninfo.mainlayout',
    
    requires: [   
    	"core.system.versioninfo.controller.MainController",
        "core.system.versioninfo.view.MainLayout",
        "core.system.versioninfo.view.DetailLayout",
        "core.system.versioninfo.view.MainGrid",
        "core.system.versioninfo.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'system.versioninfo.maincontroller',
    /** 页面代码定义 */
    funCode: "versioninfo_main",
    detCode: "versioninfo_detail",
    detLayout: "system.versioninfo.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'system.versioninfo.othercontroller',
    layout:'fit',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtSysParameter", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'sysParamName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加配置',
        	editTitle:'编辑配置',
        	detailTitle:'配置详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "system.versioninfo.maingrid"
    }]
})