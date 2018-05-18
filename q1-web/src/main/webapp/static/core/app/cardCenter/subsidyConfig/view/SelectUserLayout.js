Ext.define("core.cardCenter.subsidyConfig.SelectUserLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.cardCenter.subsidyConfig.selectUserLayout',
    layout:'border',
    border: false,
    funCode:"selectUser_main",
    funData: {
        action: comm.get('baseUrl') + "/", //请求Action
        pkName: "id",
        modelName: "", //实体全路径
        defaultObj: {
        },
    },
    items: [{
    	split: true,//对模块分开的线条
        xtype: "cardCenter.subsidyConfig.deptTree",
        width: 200,
        region: "west",
    }, {
    	flex:1,
    	split: true,
        xtype: "cardCenter.subsidyConfig.selectUserGrid",
        region: "center",
    }, {
    	split: true,
    	width: 220,
    	xtype: "cardCenter.subsidyConfig.isSelectUserGrid",
        region: "east",
    }]
})