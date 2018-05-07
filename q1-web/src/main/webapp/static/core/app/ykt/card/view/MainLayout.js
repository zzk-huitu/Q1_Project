Ext.define("core.ykt.card.view.MainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.card.mainlayout',
    
    requires: [   
    	"core.ykt.card.controller.MainController",
        "core.ykt.card.view.MainLayout",
        "core.ykt.card.view.DetailLayout",
        "core.ykt.card.view.MainGrid",
        "core.ykt.card.view.DeptTree",
        "core.ykt.card.view.DetailForm"
    ],
    
    /** 关联此视图控制器 */
    controller: 'ykt.card.maincontroller',
    /** 页面代码定义 */
    funCode: "card_main",
    detCode: "card_detail",
    detLayout: "ykt.card.detaillayout",
    /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'ykt.card.othercontroller',
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
		    xtype: "ykt.card.depttree",
		    region: "west",
		    width:250,
		    split : true,
            collapsible:true,    
	   },{
            xtype: "basecenterpanel",
            region: "center",
            items: [{
        	    xtype: "ykt.card.usergrid",
                region: "north",
                flex:2,
                split : true,
            }, {
                xtype: "ykt.card.maingrid",
                region: "center",
                plit : true,
                flex:1
            }]
        }]
})