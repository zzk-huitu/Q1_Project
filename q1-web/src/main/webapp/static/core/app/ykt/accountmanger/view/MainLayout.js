Ext.define("core.ykt.accountmanger.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.accountmanger.mainlayout',
    
    requires: [   
    	"core.ykt.accountmanger.controller.MainController",
        "core.ykt.accountmanger.view.MainLayout",
        "core.ykt.accountmanger.view.DetailLayout",
        "core.ykt.accountmanger.view.MainGrid",
        "core.ykt.accountmanger.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'ykt.accountmanger.maincontroller',
    /** 页面代码定义 */
    funCode: "accountmanger_main",
    detCode: "accountmanger_detail",
    detLayout: "ykt.accountmanger.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'ykt.accountmanger.othercontroller',
    layout:'fit',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtAccount", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'accountName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加账户',
        	editTitle:'编辑账户',
        	detailTitle:'账户详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "ykt.accountmanger.maingrid"
    }]
})