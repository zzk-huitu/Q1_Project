Ext.define("core.ykt.operatorBind.view.selectStationLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.ykt.operatorBind.selectStationLayout',
	funCode: "operatorBind_selectrolemain",
	layout: 'border',
	//bodyPadding: 5,	
	funData: {
		action: comm.get('baseUrl') + "/PtoperatorBind", //请求Action
		pkName: "id",
		//默认的初始值设置
		defaultObj: {
			sex: '1',
			category: '1',
			state: '1',
			operatorBindPwd: '123456',
			orderIndex: 1,
			isykt: 'true'
		}
	},
	items: [{
		xtype: "ykt.operatorBind.selectStationGrid",
		region: "west",
		margin:5,
		flex:2,
		title:null
		//border:true
	},{
		xtype: "ykt.operatorBind.isSelectStationGrid",
		region: "center",
		flex:1,
		margin:5,
		//border:true
	}]
})