Ext.define("core.ykt.operatorBind.view.selectAccountLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.ykt.operatorBind.selectAccountLayout',
	funCode: "operatorBind_selectAccountmain",
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
		xtype: "ykt.operatorBind.selectAccountGrid",
		region: "west",
		margin:5,
		flex:2,
		title:null
		//border:true
	},{
		xtype: "ykt.operatorBind.isSelectAccountGrid",
		region: "center",
		flex:1,
		margin:5,
		//border:true
	}]
})