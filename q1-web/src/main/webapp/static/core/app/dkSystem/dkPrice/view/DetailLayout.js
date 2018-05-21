Ext.define("core.dkSystem.dkPrice.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.dkSystem.dkPrice.detailLayout',
	funCode:"skPrice_detail",
	funData: {
		action: comm.get('baseUrl') + "/BasePriceDefine", //请求Action
		pkName: "id",
		defaultObj: {
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,

    /*关联此视图控制器*/
	controller: 'dkSystem.dkPrice.detailController',
	
	items: [{
		xtype: "dkSystem.dkPrice.detailForm"
	}]
})