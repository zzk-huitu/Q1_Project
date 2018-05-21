Ext.define("core.mjSystem.mjManage.view.MainLayout", {
	extend : "core.base.view.BasePanel",
	alias : 'widget.mjSystem.mjManage.mainLayout',

	requires : [ "core.mjSystem.mjManage.controller.MainController", ],
	/** 关联此视图控制器 */
	controller : 'mjSystem.mjManage.mainController',
	/** 页面代码定义 */
	funCode : "mjManage_main",
	detCode : "mjManage_detail",
	detLayout : "mjSystem.mjManage.detailLayout",
	/*标注这个视图控制器的别名，以此提供给window处使用*/
	otherController : 'mjSystem.mjManage.otherController',

	layout : 'fit',
	border : false,
	funData : {
		action : comm.get('baseUrl') + "/PtTerm", //请求Action 
		pkName : "id",
		defaultObj : {},
		tabConfig : { //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
			titleField : 'termName', //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
			addTitle : '添加参数',
			editTitle : '编辑参数',
			detailTitle : '参数详情'
		}
	},
	/*设置最小宽度，并且自动滚动*/
	minWidth : 1000,
	scrollable : 'x',
	layout : 'border',

	items : [ {
		split : true,
		xtype : "mjSystem.mjManage.roomInfoTree",
		region : "west",
		width : 250
	}, {
		xtype : "mjSystem.mjManage.mainGrid",
		region : "center"
	} ]
})