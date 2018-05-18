
var auditStore = new Ext.data.ArrayStore({
    fields:['id','auditStatus'],
    data:[
        ['false', '未审核'],
        ['true', '已审核'],
        ['all', '所有记录']
    ],

});
Ext.define("core.cardCenter.subsidyConfig.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.cardCenter.subsidyConfig.mainGrid",
    dataUrl: comm.get('baseUrl') + "/PtSubsidyFillMoneyMain/list",
    model: 'com.yc.q1.model.storage.pt.PtSubsidyFillMoneyMain',
    menuCode:"SUBSIDY_CONFIG",
    al:true,
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'button',
            text: '添加',
            ref: 'gridAdd',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '编辑',
            ref: 'gridEdit',
            disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '删除',
            ref: 'gridDelete',
            disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '审核',
            ref: 'gridAudit',
            disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },'->',{
            xtype: 'tbtext', 
            html:'快速搜索:',
        },{
            xtype:'combobox',
            store:auditStore,
            displayField:"auditStatus",
            valueField:"id",
            name:'isAudit',
            funCode: 'girdFastSearchText',
            emptyText: '请选择浏览条件',
        },{
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型 
            iconCls: 'x-fa fa-search',  
        }],
    }, 
    panelButtomBar:null,
    
    //排序字段及模式定义
    defSort: [],
    extParams: {},
    columns:  {        
        defaults:{
            titleAlign:"center",
            align:'center'
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
            text: "创建人",
            dataIndex: "createUser",
            width:120
        }, {
            text: "创建日期",
            dataIndex: "createTime",
            width:150,
            renderer: function(value,metaData) {  
              if(value!=""){      
                return Ext.Date.format(new Date(value), 'Y/m/d H:i:s');
            }  
         }
        }, {
            text: "充值金额",
            dataIndex: "fillMoney",
            width:120,
            renderer: function(value,metaData) {  
                return "￥"+ parseFloat(value).toFixed(2);

            }
        },{
            text: "到账日期",
            dataIndex: "fillDate",
            width:120,
            renderer: function(value,metaData) {  
              if(value!=null){      
                    return Ext.Date.format(new Date(value), 'Y/m/d');
                }  
            }
        }, {
            text: "审核人",
            dataIndex: "auditUser",
            width:120
        }, {
            text: "审核日期",
            dataIndex: "auditDate",
            width:150,
            renderer: function(value) {  
              if(value!=""){      
                return Ext.Date.format(new Date(value), 'Y/m/d H:i:s');
            } 
         }
        }, {
            text: "审核状态",
            dataIndex: "isAudit",
            width:120,
            renderer: function(value,metaData) {  
               return (value == true) ? '<font color=green>已审核</font>' : '<font color=red>未审核</font>';
           }  
        }, {
            text: "说明",
            dataIndex: "notes",
            flex:1,        
            minWidth:120,
            renderer: function(value,metaData) {  
                var title=" 说明 ";
                metaData.tdAttr = 'data-qtitle="' + title + '" data-qtip="' + value + '"';  
                return value;  
            }
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
                        var menuCode="SUBSIDY_CONFIG";     // 此菜单的前缀
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
            }, {
                text: '删除',
                style: 'font-size:12px;',
                tooltip: '删除',
                getClass: function(view, metadata, record, rowIndex, colIndex, store) {
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="SUBSIDY_CONFIG";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");                 
                        if(userBtn.indexOf(menuCode+"_gridDelete")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null;
                },
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('deleteClick', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }    
});