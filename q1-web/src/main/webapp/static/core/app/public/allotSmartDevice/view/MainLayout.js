Ext.define("core.public.allotSmartDevice.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.public.allotSmartDevice.MainLayout',
    layout:'border',
    border: false,
    funCode:"allotSmartDevice_main",
    detCode:"allotSmartDevice_detail",
   // detLayout:"",
    funData: {
        action: comm.get('baseUrl') + "/PtTerm", //请求Action
        pkName: "id",
        modelName: "com.yc.q1.model.base.pt.device.PtTerm", //实体全路径
        defaultObj: {
            roomId:"",
            leaf:true
        },
    },
         /** 关联此视图控制器 */
    controller: 'public.allotSmartDevice.mainController',
    items: [{
    	split: true,//对模块分开的线条
        xtype: "public.allotSmartDevice.roomInfoTree",
        width: 250,
        region: "west",
    }, {
    	flex:1,
    	split: true,
        xtype: "public.allotSmartDevice.allotDeviceGrid",
        region: "center",
        //width:600,
    }, {
    	split: true,
    	width: 360,
    	xtype: "public.allotSmartDevice.selectedDeviceGrid",
        region: "east",
    }]
})