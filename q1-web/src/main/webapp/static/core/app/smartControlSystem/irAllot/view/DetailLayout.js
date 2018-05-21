Ext.define("core.smartControlSystem.irAllot.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.smartControlSystem.irAllot.detailLayout',
	funCode:"irAllot_detail",
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
	controller: 'smartControlSystem.irAllot.detailController',
	
	items: [{
		xtype: "smartControlSystem.irAllot.detailForm"
	}]
})