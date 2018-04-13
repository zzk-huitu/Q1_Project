Ext.define("core.wisdomclass.roomterm.view.DetailLayout", {
    extend: "core.base.view.BasePanel",
	alias: "widget.wisdomclass.roomterm.detaillayout",
	funCode: "roomterm_detail",
	funData: {
		action: comm.get("baseUrl") + "/PtInfoTerminal", //请求Action
		pkName: "id",
		defaultObj: {}
	},
    minWidth:1000,
    scrollable:true,
	items: [{
        xtype: "wisdomclass.roomterm.detailform",
    }]
});