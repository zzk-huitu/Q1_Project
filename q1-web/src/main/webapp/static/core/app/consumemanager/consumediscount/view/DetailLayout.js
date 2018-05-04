Ext.define("core.consumemanager.consumediscount.view.DetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.consumemanager.consumediscount.detaillayout",
	funCode: "consumediscount_detail",
	funData: {
		action: comm.get("baseUrl") + "/XfRateSet", //请求Action
		pkName: "id",
		defaultObj: {},
	},
	  /*关联此视图控制器*/
    controller: 'consumemanager.consumediscount.detailcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "consumemanager.consumediscount.detailform"
	}]
});