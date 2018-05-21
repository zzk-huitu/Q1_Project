
Ext.define("core.smartControlSystem.irManage.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.smartControlSystem.irManage.mainGrid",
    dataUrl: comm.get('baseUrl') + "/PtTerm/list",
    model: "com.yc.q1.model.base.pt.device.PtTerm",
    al:false,
    menuCode:"IR_TERM_MANAGE", //new：此表格与权限相关的菜单编码

    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '红外设备管理',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800
            }
        },'->',{
            xtype: 'button',
            text: '导出',
            ref: 'gridExport',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-file-excel-o'
        },{
            xtype: 'tbtext', 
            html:'快速搜索：'
        },{
            xtype:'textfield',
            name:'termName',
            funCode: 'girdFastSearchText',
            emptyText: '请输入红外设备名称'
        },{
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型 
            iconCls: 'x-fa fa-search',  
        }],
    }, 
    panelButtomBar:null,
    
    //排序字段及模式定义
    defSort: [{
        property: 'updateTime',
        direction: 'DESC'
    }],
    extParams: {
        filter:'[{"type" : "string","value" : "11","field" : "termTypeId","comparison" : "="}]'
    },
    columns:  {        
        defaults:{
            titleAlign:"center"
        },
        items: [{
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        },{
            text: "主键",
            dataIndex: "id",
            hidden: true
        },{
            flex: 1.5,
            minWidth: 150,
            text: "设备序列号",
            dataIndex: "termSn",            
        }, {
            flex: 1.5,
            minWidth: 150,
            text: "设备名称",
            dataIndex: "termName",            
        }, {     
            flex: 1,
            minWidth: 100,
            text: "网关名称",
            dataIndex: "gatewayName"
        }, {
            flex: 1,
            minWidth: 100,
            text: "所属房间",
            dataIndex: "roomName",
        }, {
            width: 100,
            text: "设备类型",
            dataIndex: "termTypeId",
            columnType: "basecombobox", //列类型
            ddCode: "PTTERMTYPE" //字典代码
        },{
            xtype: 'actiontextcolumn',
            text: "操作",
            align: 'center',
            width: 150,
            fixed: true,
            items: [{
                text:'高级参数',  
                style:'font-size:12px;', 
                tooltip: '设置红外高级参数',
                ref: 'gridIrHighParam',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="IR_TERM_MANAGE";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");                 
                        if(userBtn.indexOf(menuCode+"_gridIrHighParam")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null; 
                },
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('setIrHighParam_Tab', {
                        view: view.grid,
                        record: rec
                    });
                }
            }, {
                text:'基础参数',  
                style:'font-size:12px;', 
                tooltip: '设置红外基础参数',
                ref: 'gridIrBaseParam',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="IR_TERM_MANAGE";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");                 
                        if(userBtn.indexOf(menuCode+"_gridIrBaseParam")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null; 
                },
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('setIrBaseParam_Tab', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }    
});
