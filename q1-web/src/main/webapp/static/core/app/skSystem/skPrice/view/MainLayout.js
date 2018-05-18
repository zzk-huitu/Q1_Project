Ext.define("core.skSystem.skPrice.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.skSystem.skPrice.mainLayout',
    
    requires: [   
    	"core.skSystem.skPrice.controller.MainController",
        "core.skSystem.skPrice.view.MainLayout",
        "core.skSystem.skPrice.view.MainGrid",
        "core.skSystem.skPrice.view.DetailForm",
    ],
    
    /** 关联此视图控制器 */
    controller: 'skSystem.skPrice.mainController',
    /** 页面代码定义 */
    funCode: "skPrice_main",
    detCode: "skPrice_detail",
    detLayout: "skSystem.skPrice.detailLayout",
    
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'skSystem.skPrice.otherController',
    layout: 'border',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/BasePriceDefine", //请求Action 
        pkName: "id",
        categroy:"",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
        	addTitle:'添加费率',
        	editTitle:'编辑费率',
        	detailTitle:'费率详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:'x',
    items: [{
		xtype: "skSystem.skPrice.mainGrid",
       // margin:'5',
		region: "center",
	}]
})