Ext.define("core.ykt.businessperiod.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.businessperiod.detaillayout',
	funCode:"businessperiod_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtMealType", //请求Action
		pkName: "mealTypeId",
		defaultObj: {
		// 	 actBegin: new Date(),
		// 	 signBeing:new Date()
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,

    /*关联此视图控制器*/
	controller: 'ykt.businessperiod.detailcontroller',
	items: [{
		xtype: "ykt.businessperiod.detailform"
	}]
})