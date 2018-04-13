Ext.define("core.baseset.teacherdorm.view.DetailLayout",{
	extend:"core.base.view.BasePanel",
	alias : 'widget.baseset.teacherdorm.detaillayout',
	funCode:"teacherdorm_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtTeacherDorm", //请求Action
		whereSql: "", //表格查询条件
		orderSql: " order by orderIndex DESC", //表格排序条件
		pkName: "id",
		defaultObj: {
	
		}
	},
	items: [{
		xtype: "baseset.teacherdorm.detailform"
	}]
});