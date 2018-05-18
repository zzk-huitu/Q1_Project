Ext.define("core.system.appUpdate.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.system.appUpdate.detailLayout',
	funCode:"appUpdate_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtAppInfo", //请求Action
		pkName: "id"
	},
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "system.appUpdate.detailForm"
	}]
})