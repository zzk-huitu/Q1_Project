Ext.define("core.consumemanager.consumelimit.view.DetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.consumemanager.consumelimit.detaillayout",
	funCode: "consumelimit_detail",
	funData: {
		action: comm.get("baseUrl") + "/XfXeSet", //请求Action
		pkName: "id",
		defaultObj: {},
	},
	  /*关联此视图控制器*/
    controller: 'consumemanager.consumelimit.detailcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "consumemanager.consumelimit.detailform"
	}]
});