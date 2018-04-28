Ext.define("core.ykt.clerkmanger.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.clerkmanger.detaillayout',
	funCode:"clerkmanger_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtMealOperator", //请求Action
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
	controller: 'ykt.clerkmanger.detailcontroller',
	items: [{
		xtype: "ykt.clerkmanger.detailform"
	}]
})