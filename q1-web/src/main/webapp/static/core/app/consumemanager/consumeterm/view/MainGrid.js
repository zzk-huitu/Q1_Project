Ext.define("core.consumemanager.consumeterm.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.consumemanager.consumeterm.maingrid",
    dataUrl: comm.get("baseUrl") + "", //数据获取地址
    model: "", //对应的数据模型
    menuCode:"",

    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'button',
            text: '添加',
            ref: 'gridAdd_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle',
            disabled:false,
        },{
            xtype: 'button',
            text: '编辑',
            ref: 'gridEdit_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true, 
            iconCls: 'x-fa fa-pencil-square'
        },{
            xtype: 'button',
            text: '批量添加',
            ref: 'gridBatchAdd',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle',
            disabled:false,
        } ,{
            xtype: 'button',
            text: '高级参数',
            ref: 'gridHigh',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle',
            disabled:false,
        },{
            xtype: 'button',
            text: '基础参数',
            ref: 'gridBase',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle',
            disabled:false,
        },/*'->',{
            xtype: 'tbtext', 
            html:'快速搜索：'
        },{
            xtype:'textfield',
            name:'courseName',
            funCode: 'girdFastSearchText',
            emptyText: '请输入设备名称'
        },{
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型 
            iconCls: 'x-fa fa-search',  
        }*/],
  
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
        },  {
            text : "终端号",
            dataIndex : "",
            flex:1
        }, {
            text : "终端名称",
            dataIndex : "",
            flex:1
        },{
            text : "终端类型",
            dataIndex : "",        
            flex:1,
            columnType: "basecombobox", //列类型
            ddCode: "", //字典代码     
        },{
            text : "ip地址",
            dataIndex : "",     
            flex:1
        },{
            text : "端口号",
            dataIndex : "",
            flex:1
        },{
            text : "消费模式",
            dataIndex : "",
            flex:1,
             columnType: "basecombobox", //列类型
            ddCode: "", //字典代码     
        },{
            text : "工作区域",
            dataIndex : "",
            flex:1,
            columnType: "basecombobox", //列类型
            ddCode: "", //字典代码     
        },{
            text : "结算账户",
            dataIndex : "",
            flex:1,
             columnType: "basecombobox", //列类型
            ddCode: "", //字典代码     
        },{
            text : "说明",
            dataIndex : "",
            flex:1,
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
                        var menuCode="COURSEINFO";     // 此菜单的前缀
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