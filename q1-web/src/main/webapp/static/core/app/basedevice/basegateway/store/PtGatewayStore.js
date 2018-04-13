Ext.define("core.basedevice.basegateway.store.PtGatewayStore", {
	extend: "Ext.data.Store",
	alias: 'store.basedevice.basegateway.ptgatewaystore',
	model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtFrontServer", "checked").modelName,
	proxy: {
		type: "ajax",
		url: comm.get('baseUrl') + "/PtFrontServer/list", //对应后台controller路径or方法
		extParams: {
		},
		reader: {
			type: "json",
			rootProperty: "rows",
			totalProperty: 'totalCount'
		},
		writer: {
			type: "json"
		}
	},
	autoLoad: true
})