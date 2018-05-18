Ext.define("core.cardCenter.subsidyConfig.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.cardCenter.subsidyConfig.mainLayout',

    /** 关联此视图控制器 */
    controller: 'cardCenter.subsidyConfig.mainController',
    /** 页面代码定义 */
    funCode: "subsidyConfig_main",
    detCode: "subsidyConfig_detail",
    detLayout: "cardCenter.subsidyConfig.detailLayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'cardCenter.subsidyConfig.otherController',
    funData: {
        action: comm.get('baseUrl') + "/PtSubsidyFillMoneyMain", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加',
        	editTitle:'编辑',
        	detailTitle:'详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
		    xtype: "cardCenter.subsidyConfig.mainGrid",
	   }]
})