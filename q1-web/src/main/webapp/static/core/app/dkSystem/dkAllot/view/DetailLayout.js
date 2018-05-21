Ext.define("core.dkSystem.dkAllot.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.dkSystem.dkAllot.detailLayout',
	funCode:"dkAllot_detaillayout",
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
	controller: 'dkSystem.dkAllot.detailController',
	
	items: [{
		xtype: "dkSystem.dkAllot.detailForm"
	}]
})