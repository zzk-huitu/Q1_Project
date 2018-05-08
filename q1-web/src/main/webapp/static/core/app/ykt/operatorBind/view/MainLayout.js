Ext.define("core.ykt.operatorBind.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.operatorBind.mainlayout',
    
    requires: [   
    	"core.ykt.operatorBind.controller.MainController",
        "core.ykt.operatorBind.view.MainLayout",
        "core.ykt.operatorBind.view.DetailLayout",
        "core.ykt.operatorBind.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'ykt.operatorBind.maincontroller',
    /** 页面代码定义 */
    funCode: "operatorBind_main",
    detCode: "operatorBind_detail",
    detLayout: "ykt.operatorBind.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'ykt.operatorBind.othercontroller',
    layout:'border',
    border:false,
    funData: {
        action: comm.get('baseUrl') + "/Ptcard", //请求Action 
        pkName: "id",
        defaultObj: {},
        tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'cardName',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
        	addTitle:'添加工作站',
        	editTitle:'编辑工作站',
        	detailTitle:'工作站详情'
        }
    },
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
		    xtype: "ykt.operatorBind.depttree",
		    region: "west",
		    width:250,
		    split : true,
            collapsible:true,    
	   },{
            xtype: "ykt.operatorBind.usergrid",
            region: "center",
            split : true,     
       },{
            xtype: "basecenterpanel",
            layout:"border",
            region: "east",
            width:400,
            split : true,     
            items: [{
        	    xtype: "ykt.operatorBind.roomBindGrid",
                region: "north",
                flex:1,
                split : true, 
            }, {
                xtype: "ykt.operatorBind.workStationBindGrid",
                region: "center",
                split : true,
                flex:1
            }, {
                xtype: "ykt.operatorBind.accountBindGrid",
                region: "south",
                split : true,
                flex:1
            }]
        }]
})