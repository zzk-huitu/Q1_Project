Ext.define("core.basedevice.irdevice.view.BrandDetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.basedevice.irdevice.branddetaillayout',
	funCode: "irdevice_branddetaillayout",
	funData: {
		action: comm.get('baseUrl') + "/PtIrDeviceBrand", //请求Action
        whereSql: "", //表格查询条件
		whereSql: "", //表格查询条件
		orderSql: "", //表格排序条件
		pkName: "id",
		defaultObj: {
			orderIndex: 1,
		}
	},
	layout: 'fit',
	items: [{
		xtype: "basedevice.irdevice.treeform"
	}]
})