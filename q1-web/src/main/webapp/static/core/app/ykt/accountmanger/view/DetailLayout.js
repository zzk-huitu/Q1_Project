Ext.define("core.ykt.accountmanger.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.accountmanger.detaillayout',
	funCode:"jobinfo_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtAccount", //请求Action
		pkName: "id",
		defaultObj: {
		// 	 actBegin: new Date(),
		// 	 signBeing:new Date()
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,

    /*关联此视图控制器*/
	controller: 'ykt.accountmanger.detailcontroller',
	items: [{
		xtype: "ykt.accountmanger.detailform"
	}]
})