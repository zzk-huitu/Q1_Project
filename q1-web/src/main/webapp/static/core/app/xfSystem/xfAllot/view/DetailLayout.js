Ext.define("core.xfSystem.xfAllot.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.xfSystem.xfAllot.detailLayout',
	funCode:"xfAllot_detaillayout",
	funData: {
		action: comm.get('baseUrl') + "/PtTerm", //请求Action
		pkName: "id",
		defaultObj: {
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,

    /*关联此视图控制器*/
	controller: 'xfSystem.xfAllot.detailController',
	
	items: [{
		xtype: "xfSystem.xfAllot.detailForm"
	}]
})