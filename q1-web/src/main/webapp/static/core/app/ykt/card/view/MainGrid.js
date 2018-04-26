Ext.define("core.ykt.card.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.card.maingrid",
    dataUrl: comm.get('baseUrl') + "/PtCard/list",
    model: 'com.yc.q1.model.base.pt.card.PtCard',
    al:false,
    menuCode:"JOBINFO", //new：此表格与权限相关的菜单编码
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'button',
            text: '挂失/解挂',
            ref: 'gridAdd_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '批量挂失',
            ref: 'gridEdit_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true,
            iconCls: 'x-fa fa-pencil-square'
        }, {
            xtype: 'button',
            text: '批量解挂',
            ref: 'gridDelete',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true,
            iconCls: 'x-fa fa-minus-circle'
        },'->',{
            xtype: 'tbtext', 
            html:'快速搜索：'
        },{
            xtype:'textfield',
            name:'cardNo',
            funCode: 'girdFastSearchText',
            emptyText: '请输入卡流水号'
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
    },{
        property: 'createTime',
        direction: 'DESC'
    }],
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
        }, {
            text: "卡流水号",
            dataIndex: "cardNo",
            flex:1,        
            minWidth:120,
        }, {
            text: "卡状态",
            dataIndex: "cardStatusId",
            columnType: "basecombobox", //列类型
            ddCode: "KZT" //字典代码
        }, {
            text: "卡类型",
            dataIndex: "cardTypeId",
            renderer: function(value,cellmeta,record,rowIndex,columnIndex,store) {
                value= record.raw.cardTypeName;
                return value
            }
        }, {
            text: "当日消费次数",
            dataIndex: "dayCount",
            hidden: true
        }, {
            text: "当日交易金额",
            dataIndex: "dayValue",
            hidden: true
        }, {
            text: "卡押金",
            dataIndex: "deposit",
            hidden: true
        }, {
            text: "创建时间",
            dataIndex: "createTime",
            hidden: true
        }, {
            text: "有效期",
            dataIndex: "expiryDate",
            renderer: function(value) {
                if(value!=""){
                    var date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
                    return date.toLocaleString()
                }
                return null
             }
        }, {
            text: "物理卡号",
            dataIndex: "factoryFixId",
        }, {
            text: "最后交易时间",
            dataIndex: "lastPayDate",
            hidden: true
        }, {
            text: "最后交易餐类",
            dataIndex: "lastPayMealType",
            hidden: true
        }, {
            text: "当餐消费次数",
            dataIndex: "mealCount",
            hidden: true
        }, {
            text: "当餐交易金额",
            dataIndex: "mealValue",
            hidden: true
        }, {
            text: "状态修改时间",
            dataIndex: "statusChangeTime",
            renderer: function(value) {
                if(value!=""){
                    var date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
                    return date.toLocaleString()
                }
                return null
            }
        }, {
            text: "用户ID",
            dataIndex: "userId",
            hidden: true
        }, {
            text: "学号",
            dataIndex: "userNumb",
            renderer: function(value,cellmeta,record,rowIndex,columnIndex,store) {
                value= record.raw.userNumb;
                return value
            },
            hidden: true
        }, {
            text: "姓名",
            dataIndex: "xm",
            renderer: function(value,cellmeta,record,rowIndex,columnIndex,store) {
                value= record.raw.xm;
                return value
            },
            hidden: true
        }, {
            xtype: 'actiontextcolumn',
            text: "操作",
            align: 'center',
            width: 250,
            fixed: true,
            items: [{
                text:'编辑',  
                style:'font-size:12px;', 
                tooltip: '编辑',
                ref: 'gridEdit',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="JOBINFO";     // 此菜单的前缀
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
                text:'详细',  
                style:'font-size:12px;', 
                tooltip: '详细',
                ref: 'gridDetail',
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('detailClick_Tab', {
                        view: view.grid,
                        record: rec
                    });
                }
            }, {
                text:'删除',  
                style:'font-size:12px;', 
                tooltip: '删除',
                ref: 'gridDelete',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="JOBINFO";     // 此菜单的前缀
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