var bagTypeStore = new Ext.data.ArrayStore({
    fields:['id','bagCode'],
    data:[
        ['1', '消费主钱包'],
        ['2', '水控主钱包'],
        ['3', '消费补助钱包']
    ],

});
Ext.define("core.ykt.card.view.AccountOperatorGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.card.accountoperatorgrid",
    dataUrl: comm.get('baseUrl') + "/PtCard/getAccountOperatorCardList",
    model: 'com.yc.q1.model.base.pt.card.PtCard',
    al:false,
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '用户操作',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800,
                lineHeight:'30px',
            }
        },{
            xtype: 'button',
            text: '充值',
            ref: 'userRecharge',
           // disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '退款',
            ref: 'userRefund',
           // disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },'->',{
            xtype: 'tbtext', 
            html:'快速搜索:',
        },{
            xtype:'combobox',
            store:bagTypeStore,
            displayField:"bagCode",
            valueField:"id",
           // value:"1",
            name:'bagCode',
            funCode: 'girdFastSearchText',
            emptyText: '请选择钱包类型',
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
            text: "用户id",
            dataIndex: "userId",
            hidden: true
        },{
            text: "人员编号",
            dataIndex: "userNumb",
            width:100
        }, {
            text: "姓名",
            dataIndex: "name",
            width:120
        }/*, {
            text: "部门编号",
            dataIndex: "",
            width:120
        }*/, {
            text: "部门名称",
            dataIndex: "deptName",
            width:120
        },{
            text: "钱包余额",
            dataIndex: "cardValue", // 不同卡钱包编码的余额不一样  消费 /水控/消费补助
            width:120,
        }, {
            text: "卡流水号",
            dataIndex: "cardNo",
            flex:1,        
            minWidth:120,
        }, {
            text: "卡类",
            dataIndex: "cardTypeId",
            width:120,

        }, {
            text: "押金",
            dataIndex: "deposit",
            width:120
        }, {
            xtype: 'actiontextcolumn',
            text: "操作",
            align: 'center',
            width: 120,
            fixed: true,
            items: [{
                text:'充值',  
                style:'font-size:12px;', 
                tooltip: '充值操作',
                ref: 'gridEdit',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");   
                        if(userBtn.indexOf(menuCode+"")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null; 
                },  
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('userRecharge_Win', {
                        view: view.grid,
                        record: rec
                    });
                }
            },  {
                text:'退款',  
                style:'font-size:12px;', 
                tooltip: '退款操作',
                ref: 'gridEdit',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");   
                        if(userBtn.indexOf(menuCode+"")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null; 
                },  
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('userRefund_Win', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }    
});