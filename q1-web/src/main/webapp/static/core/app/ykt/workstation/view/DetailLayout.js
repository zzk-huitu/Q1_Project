Ext.define("core.ykt.workstation.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.workstation.detaillayout',
	funCode:"workstation_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtWorkStation", //请求Action
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
	controller: 'ykt.workstation.detailcontroller',
	items: [{
		xtype: "ykt.workstation.detailform"
	}]
})