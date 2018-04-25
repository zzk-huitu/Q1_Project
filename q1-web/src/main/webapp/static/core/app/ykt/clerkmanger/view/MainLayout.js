Ext.define("core.ykt.clerkmanger.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.clerkmanger.mainlayout',
    
    requires: [   
    	"core.ykt.clerkmanger.controller.MainController",
        "core.ykt.clerkmanger.view.MainLayout",
        "core.ykt.clerkmanger.view.DetailLayout",
        "core.ykt.clerkmanger.view.MainGrid",
        "core.ykt.clerkmanger.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'ykt.clerkmanger.maincontroller',
    /** 页面代码定义 */
    funCode: "jobinfo_main",
    detCode: "jobinfo_detail",
    detLayout: "ykt.clerkmanger.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'ykt.clerkmanger.othercontroller',
    layout:'fit',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtMealOperator", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'jobName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加岗位',
        	editTitle:'编辑岗位',
        	detailTitle:'岗位详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "ykt.clerkmanger.maingrid"
    }]
})