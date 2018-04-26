Ext.define("core.ykt.cardtype.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.cardtype.detaillayout',
	funCode:"cardtype_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtCardType", //请求Action
		pkName: "cardTypeId",
		defaultObj: {
		// 	 actBegin: new Date(),
		// 	 signBeing:new Date()
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,

    /*关联此视图控制器*/
	controller: 'ykt.cardtype.detailcontroller',
	items: [{
		xtype: "ykt.cardtype.detailform"
	}]
})