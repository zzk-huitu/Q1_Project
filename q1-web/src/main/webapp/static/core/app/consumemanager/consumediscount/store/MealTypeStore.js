Ext.define("core.consumemanager.consumediscount.store.MealTypeStore",{
	extend: "Ext.data.Store",
	alias: 'store.consumemanager.consumediscount.mealtypestore',
	model:factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.system.PtMealType","checked").modelName,
	proxy: {
		type: "ajax",
		url: comm.get('baseUrl') + "/PtMealType/mealTypeList", //对应后台controller路径or方法
		extraParams : {
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
	autoLoad:true
 });