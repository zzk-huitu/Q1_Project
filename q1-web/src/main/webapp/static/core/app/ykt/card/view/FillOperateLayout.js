Ext.define("core.ykt.card.view.FillOperateLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.ykt.card.filloperatelayout',
	funCode:"filloperate_detail",
	funData: {
		action: comm.get('baseUrl') + "/XfConsumeDetail", //请求Action
		pkName: "id",
		defaultObj: {
		}
	},
	 /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,

    /*关联此视图控制器*/
	controller: 'ykt.card.detailcontroller',
	layout:"border",
	items: [{
		xtype: "ykt.card.consumedetailgrid",
		region: "center",
	},{
		xtype: "ykt.card.consumedetailform",
		region: "south",
		height:"400",
	}]
})