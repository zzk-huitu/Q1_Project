Ext.define("core.system.dept.store.CourseStore", {
	extend: "Ext.data.Store",
	alias: 'store.system.dept.coursestore',

	model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.basic.PtBaseCourse", "checked").modelName,
	proxy: {
		type: "ajax",
		url: comm.get('baseUrl') + "/PtBaseCourse/list", //对应后台controller路径or方法
		extraParams : {
			filter: "[{'type':'string','comparison':'=','value':'aaaa','field':'id'}]"
		},
		reader: {
			type: "json",
			rootProperty: "rows",
			totalProperty: 'totalCount'
		},
		writer: {
			type: "json"
		}
	},
	autoLoad: false
})