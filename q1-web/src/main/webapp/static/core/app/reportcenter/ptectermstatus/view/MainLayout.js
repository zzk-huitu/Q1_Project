Ext.define("core.reportcenter.ptectermstatus.view.MainLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.reportcenter.ptectermstatus.mainlayout',

    requires: [   
        "core.reportcenter.ptectermstatus.controller.MainController",
    ],
    
    /** 关联此视图控制器 */
    controller: 'reportcenter.ptectermstatus.maincontroller',

	funCode: "ptectermstatus_main",
	detCode: 'ptectermstatus_detail',
	detLayout: 'reportcenter.ptectermstatus.detaillayout',

	 /*标注这个视图控制器的别名，以此提供给window处使用*/
    otherController:'reportcenter.ptectermstatus.othercontroller',
 
	funData: {
		action: comm.get('baseUrl') + "/PtEcTermStatus", //请求Action
		pkName: "uuid",
		defaultObj: {
           
        },
	    tabConfig:{         //zzk：2017-6-1加入，用于对tab操作提供基本配置数据
            titleField:'',   //指定这个模块，主表格界面的名称的字段名，用于显示在tab标签页上面
            addTitle:'',
            editTitle:'',
            detailTitle:'',
            addXtype:null,										//2018/1/3新加入，用于在公共方法中打开指定的界面
            editXtype:null,										//2018/1/3新加入，用于在公共方法中打开指定的界面
            detailXtype:null,	//2018/1/3新加入，用于在公共方法中打开指定的界面
        }
	},

    minWidth:1100,
    scrollable:'x',
    layout:'border',

	items: [ {
		xtype: "reportcenter.ptectermstatus.roominfotree",		
        region: "west",
        width:250,
        split:true,
        //margin:'0 5 0 0'
	},{
        xtype: "reportcenter.ptectermstatus.maingrid",   
        region: "center",
      //  margin:'0 5 0 0'  
    }]
})
