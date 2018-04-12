Ext.define("core.system.appupdate.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.system.appupdate.detaillayout',
	funCode:"appupdate_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtAppInfo", //请求Action
		pkName: "id"
	},
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "system.appupdate.detailform"
	}]
})