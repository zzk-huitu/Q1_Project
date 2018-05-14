Ext.define("core..ykt.card.SubsidySet.SelectUserLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.card.SubsidySet.selectuserlayout',
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
        xtype: "ykt.card.SubsidySet.depttree",
        width: 200,
        region: "west",
    }, {
    	flex:1,
    	split: true,
        xtype: "ykt.card.selectusergrid",
        region: "center",
    }, {
    	split: true,
    	width: 220,
    	xtype: "ykt.card.isselectusergrid",
        region: "east",
    }]
})