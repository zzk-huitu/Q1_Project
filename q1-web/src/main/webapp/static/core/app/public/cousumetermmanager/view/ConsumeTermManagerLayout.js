Ext.define("core.public.consumetermmanager.view.ConsumeTermManagerLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.public.consumetermmanager.consumetermmanagerlayout",
	funCode: "consumetermmanager_main",
	detCode: "consumetermmanager_detail",
    detLayout: "public.consumetermmanager.detaillayout",
	funData: {
		action: comm.get("baseUrl") + "/", //请求Action
		pkName: "id",
	
	},
	 /** 关联此视图控制器 */
    controller: 'public.consumetermmanager.consumetermcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "public.consumetermmanager.consumetermmanagergrid"
	}]
});