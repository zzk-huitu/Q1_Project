Ext.define("core.xfSystem.xfAllot.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.xfSystem.xfAllot.mainLayout',
    requires: [   
        "core.xfSystem.xfAllot.view.MainLayout",
    ],
    
    /** 关联此视图控制器 */
    controller: 'xfSystem.xfAllot.mainController',
    /** 页面代码定义 */
    funCode: "xfAllot_main",
    detCode: 'xfAllot_detaillayout',
    detLayout: "xfSystem.xfAllot.detailLayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'xfSystem.xfAllot.otherController',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/PtTerm", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
        	addTitle:'分配设备',
        	editTitle:'编辑设备',
        	detailTitle:'设备详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:'x',
    layout:'border',
    
    items: [{
        split: true,
        xtype: "xfSystem.xfAllot.roomInfoTree",
        region: "west",
        width:250
        //width: comm.get("clientWidth") * 0.16
    }, {
        xtype: "xfSystem.xfAllot.mainGrid",
        region: "center"
    }]
})