Ext.define("core.ykt.sysparameters.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.sysparameters.detaillayout',
	funCode:"sysparameters_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtSysParameter", //请求Action
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
	controller: 'ykt.sysparameters.detailcontroller',
	items: [{
		xtype: "ykt.sysparameters.detailform"
	}]
})