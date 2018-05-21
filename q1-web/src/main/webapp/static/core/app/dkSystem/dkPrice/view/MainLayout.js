Ext.define("core.dkSystem.dkPrice.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.dkSystem.dkPrice.mainLayout',
    
    requires: [   
    	"core.dkSystem.dkPrice.controller.MainController",
        "core.dkSystem.dkPrice.view.MainLayout",
        "core.dkSystem.dkPrice.view.MainGrid",
        "core.dkSystem.dkPrice.view.DetailForm",
    ],
    
    /** 关联此视图控制器 */
    controller: 'dkSystem.dkPrice.mainController',
    /** 页面代码定义 */
    funCode: "dkPrice_main",
    detCode: "dkPrice_detail",
    detLayout: "dkSystem.dkPrice.detailLayout",
    
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'dkSystem.dkPrice.otherController',
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
		xtype: "dkSystem.dkPrice.mainGrid",
       // margin:'5',
		region: "center",
	}]
})