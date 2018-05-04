Ext.define("core.ykt.operatorBind.view.selectRoomLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.ykt.operatorBind.selectRoomLayout',
	funCode: "operatorBind_selectRoommain",
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
		xtype: "ykt.operatorBind.areagrid",
		region: "west",
		margin:5,
		flex:2,
		title:null
	},{
		xtype: "ykt.operatorBind.selectRoomGrid",
		region: "center",
		margin:5,
		flex:5,
		title:null
	},{
		xtype: "ykt.operatorBind.isSelectRoomGrid",
		region: "east",
		flex:3,
		margin:5,
	}]
})