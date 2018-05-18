Ext.define("core.skSystem.skAllot.view.DeviceAllotLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.skSystem.skAllot.deviceAllotLayout',
    layout:'border',
    border: false,
    
    funData: {
        action: comm.get('baseUrl') + "/PtTerm", //请求Action
        pkName: "id",
        modelName: "com.yc.q1.model.base.pt.device.PtTerm", //实体全路径
        defaultObj: {
            roomId:"",
            leaf:true
        },
    },
    items: [{
    	split: true,//对模块分开的线条
        xtype: "skSystem.skAllot.roomInfoTree2",
        width: 250,
        region: "west",
    }, {
    	flex:1,
    	split: true,
        xtype: "skSystem.skAllot.deviceAllotGrid",
        region: "center",
        //width:600,
    }, {
    	split: true,
    	width: 360,
    	xtype: "skSystem.skAllot.deviceSysGrid",
        region: "east",
    }]
})