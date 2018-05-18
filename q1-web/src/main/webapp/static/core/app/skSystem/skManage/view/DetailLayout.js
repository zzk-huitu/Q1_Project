Ext.define("core.skSystem.skManage.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.skSystem.skManage.detailLayout',
	funCode:"smartdevice_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtTerm", //请求Action
		pkName: "id",
		defaultObj: {
		// 	 actBegin: new Date(),
		// 	 signBeing:new Date()
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    viewModel: 'skSystem.skManage.mainModel',
    /*关联此视图控制器*/
	controller: 'skSystem.skManage.detailController',
	items: [{
		xtype: "skSystem.skManage.detailForm"
	}]
})