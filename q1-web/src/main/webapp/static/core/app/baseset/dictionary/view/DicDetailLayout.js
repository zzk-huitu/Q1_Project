Ext.define("core.baseset.dictionary.view.DicDetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.baseset.dictionary.dicdetaillayout',
	funCode: "dic_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtDataDict", //请求Action
		pkName: "id",
		defaultObj: {
			orderIndex: 1,
			dicType:"LIST"
		}
	},
	 /*关联此视图控制器*/
    controller: 'baseset.dictionary.detailcontroller',
	layout: 'fit',
	bodyPadding: 2,
	items: [{
		xtype: "baseset.dictionary.dicform"
	}]
})