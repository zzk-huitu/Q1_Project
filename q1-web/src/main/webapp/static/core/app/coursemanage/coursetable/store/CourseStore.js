
Ext.define("core.coursemanage.coursetable.store.CourseStore",{
    extend:"Ext.data.Store",
    alias: 'store.coursemanage.coursetable.coursestore',
//    fields: ['courseName','uuid'],
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.basic.PtBaseCourse", "").modelName,
	proxy: {
		type: "ajax",
		url: comm.get('baseUrl') + "/PtBaseCourse/list", //对应后台controller路径or方法
		extParams: {
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
	autoLoad: true
});