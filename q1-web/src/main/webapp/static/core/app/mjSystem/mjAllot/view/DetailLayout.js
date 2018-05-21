Ext.define("core.mjSystem.mjAllot.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.mjSystem.mjAllot.detailLayout',
	funCode:"mjAllot_detaillayout",
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
	controller: 'mjSystem.mjAllot.detailController',
	
	items: [{
		xtype: "mjSystem.mjAllot.detailForm"
	}]
})