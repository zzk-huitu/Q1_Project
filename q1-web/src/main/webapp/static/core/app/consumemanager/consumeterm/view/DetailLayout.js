Ext.define("core.consumemanager.consumeterm.view.DetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.consumemanager.consumeterm.detaillayout",
	funCode: "consumeterm_detail",
	funData: {
		action: comm.get("baseUrl") + "/", //请求Action
		pkName: "id",
		defaultObj: {},
	},
	  /*关联此视图控制器*/
    controller: 'consumemanager.consumeterm.detailcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "consumemanager.consumeterm.detailform"
	}]
});