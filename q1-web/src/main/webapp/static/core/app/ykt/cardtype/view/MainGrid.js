Ext.define("core.ykt.cardtype.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.cardtype.maingrid",
    dataUrl: comm.get('baseUrl') + "/PtCardType/list",
    model: 'com.yc.q1.model.base.pt.card.PtCardType',

    menuCode:"JOBINFO", //new：此表格与权限相关的菜单编码
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'button',
            text: '添加',
            ref: 'gridAdd_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '编辑',
            ref: 'gridEdit_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true,
            iconCls: 'x-fa fa-pencil-square'
        }, '->',{
            xtype: 'tbtext', 
            html:'快速搜索：'
        },{
            xtype:'textfield',
            name:'cardTypeName',
            funCode: 'girdFastSearchText',
            emptyText: '请输入卡类名称'
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
        property: 'cardTypeName',
        direction: 'ASC'
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
            dataIndex: "cardTypeId",
            hidden: true
        },{
    		text: "卡类名称",
    		dataIndex: "cardTypeName",
    		type: "string",
    		flex:1,        
            minWidth:120,
    	}, {
    		text: "工本费",
    		dataIndex: "issueFee",
    		type: "string",
    		width:120
    	},{
    		text: "折旧费率",
    		dataIndex: "issueFee",
    		type: "string",
    		width:120
    	},{
    		text: "管理费",
    		dataIndex: "commissionCharge",
    		type: "string",
    		width:120
    	}, {
    		text: "卡押金",
    		dataIndex: "deposit",
    		type: "string",
    		width:120
    	},{
    		text: "是否有效",
    		dataIndex: "useFlag",
    		width:120,
    		type: "boolean",
    		renderer:function(v){
    			 return v==true?"<font color=red>是</font>":"<font color=green>否</font>"
    		}
    	},  {
    		text: "卡类描述",
    		dataIndex: "cardNotes",
    		type: "string",
    		width:200
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
            }]
        }]
    }    
});