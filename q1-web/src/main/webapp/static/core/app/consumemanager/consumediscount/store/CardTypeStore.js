Ext.define("core.consumemanager.consumediscount.store.CardTypeStore",{
	extend: "Ext.data.Store",
	alias: 'store.consumemanager.consumediscount.cardtypestore',
	model:factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.card.PtCardType","checked").modelName,
	proxy: {
		type: "ajax",
		url: comm.get('baseUrl') + "/PtCardType/cardTypeList", //对应后台controller路径or方法
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