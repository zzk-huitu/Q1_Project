Ext.define("core.smartControlSystem.irManage.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.smartControlSystem.irManage.detailLayout',
	funCode:"irManage_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtTerm", //请求Action
		pkName: "id",
		defaultObj: {
	
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    /*关联此视图控制器*/
	controller: 'smartControlSystem.irManage.detailController',
	items: [{
		xtype: "smartControlSystem.irManage.irBaseParamForm"
	}]
})