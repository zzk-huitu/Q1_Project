Ext.define("core.public.consumetermmanager.view.DetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.public.consumetermmanager.detaillayout",
	funCode: "consumetermmanager_detail",
	funData: {
		action: comm.get("baseUrl") + "/", //请求Action
		pkName: "id",
		defaultObj: {},
	},
	  /*关联此视图控制器*/
    controller: 'public.consumetermmanager.detailcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "public.consumetermmanager.consumetermgrid"
	}]
});