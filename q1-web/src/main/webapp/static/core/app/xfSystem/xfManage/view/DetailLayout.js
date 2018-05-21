Ext.define("core.xfSystem.xfManage.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.xfSystem.xfManage.detailLayout',
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
    viewModel: 'xfSystem.xfManage.mainModel',
    /*关联此视图控制器*/
	controller: 'xfSystem.xfManage.detailController',
	items: [{
		xtype: "xfSystem.xfManage.detailForm"
	}]
})