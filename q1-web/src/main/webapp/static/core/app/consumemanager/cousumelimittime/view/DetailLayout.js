Ext.define("core.consumemanager.consumelimittime.view.DetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.consumemanager.consumelimittime.detaillayout",
	funCode: "consumelimittime_detail",
	funData: {
		action: comm.get("baseUrl") + "/XfXcSet", //请求Action
		pkName: "id",
		defaultObj: {},
	},
	  /*关联此视图控制器*/
    controller: 'consumemanager.consumelimittime.detailcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "consumemanager.consumelimittime.detailform"
	}]
});