Ext.define("core.consumemanager.consumelimittime.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.consumemanager.consumelimittime.maingrid",
    dataUrl: comm.get("baseUrl") + "/XfXcSet/list", //数据获取地址
    model: "com.yc.q1.model.base.xf.XfXcSet", //对应的数据模型
    menuCode:"",

    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'button',
            text: '添加',
            ref: 'gridAdd_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-pencil-square'
        },{
            xtype: 'button',
            text: '编辑',
            ref: 'gridEdit_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true, 
            iconCls: 'x-fa fa-pencil-square'
        },{
            xtype: 'button',
            text: '限次设备绑定',
            ref: 'limitTimeTermBing',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle',
            disabled:false,
        }],
  
   },
    defSort: [{
        property: 'updateTime',
        direction: 'DESC'
    }],
    panelButtomBar:null,
    extParams: {
        
    },
    columns:{
         defaults:{
            titleAlign:"center",
            align:'center',
        },
        items: [{
            xtype : "rownumberer",
            width : 50,
            text : '序号',
            align : 'center'
        },{
            text: "主键",
            dataIndex: "id",
            hidden: true
        }, {
            text : "卡类名称",
            dataIndex : "cardTypeName",
            flex:1
        },{
            text : "状态",
            dataIndex : "xcStatus",
            flex:1,
            renderer: function(value) {
                return (value == true) ? '<font color=green>启用</font>' : '<font color=red>禁用</font>';
            }
        },{
            text : "日限次",
            dataIndex : "dailyCount",        
            flex:1
        },{
            text : "早餐限次",
            dataIndex : "meal1Count",     
            flex:1
        },{
            text : "午餐限次",
            dataIndex : "meal2Count",
            flex:1
        },{
            text : "晚餐限次",
            dataIndex : "meal3Count",
            flex:1
        }, {
            text : "夜餐限次",
            dataIndex : "meal4Count",
            flex:1
        },{
            text : "备注",
            dataIndex : "notes",
            flex:1
        },{
           xtype: 'actiontextcolumn',
           text: "操作",
           align: 'center',
           width: 100,
           fixed: true,
           items: [{
                text: '编辑',
                style: 'font-size:12px;',
                tooltip: '编辑此信息',
                getClass: function(view, metadata, record, rowIndex, colIndex, store) {
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");                 
                        if(userBtn.indexOf(menuCode+"_gridEdit_Tab")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null;
                },
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('editClick_Tab', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }
});