Ext.define("core.ykt.card.view.ErrorFillMoneyGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.card.errorfillmoneygrid",
    dataUrl: comm.get('baseUrl') + "/PtCard/getFillMOneyCardList",
    model: 'com.yc.q1.model.base.pt.card.PtCard',
    al:false,
   // menuCode:"", //new：此表格与权限相关的菜单编码
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '人员/卡片信息',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800,
                lineHeight:'30px',
            }
        },{
            xtype: 'button',
            text: '补款操作',
            ref: 'errorFillMoneyOperate',
           // disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
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
            text: "人员编号",
            dataIndex: "userNumb",
            width:100
        }, {
            text: "姓名",
            dataIndex: "name",
            width:100
        }/*, {
            text: "部门编号",
            dataIndex: "",
            width:120
        }*/, {
            text: "部门名称",
            dataIndex: "deptName",
            width:100
        },{
            text: "物理卡号",
            dataIndex: "factoryFixId",
            flex:1,        
            minWidth:100,
        }, {
            text: "卡流水号",
            dataIndex: "cardNo",
            flex:1,        
            minWidth:100,
        }, {
            text: "押金",
            dataIndex: "deposit",
            width:100
        }, {
            text: "消费余额",
            dataIndex: "cardValue",//消费主钱包的余额 T_PT_CardBags
            width:100,
            renderer: function(value,cellmeta,record,rowIndex,columnIndex,store) {
                var xfCardValue = record.get("xfCardValue");
                if(xfCardValue)
                  return xfCardValue;  
              return "";
            
           }
        }, {
            text: "水控余额",
            dataIndex: "cardValue", //水控主钱包的余额  T_PT_CardBags
            width:100,
            renderer: function(value,cellmeta,record,rowIndex,columnIndex,store) {  
               var skCardValue = record.get("skCardValue");
               if(skCardValue)
                  return skCardValue; 
              return ""; 
              
          }
        },{
            text: "卡类",
            dataIndex: "cardTypeId", 
            width:100
        }, {
            xtype: 'actiontextcolumn',
            text: "操作",
            align: 'center',
            width: 120,
            fixed: true,
            items: [{
                text:'补款操作',  
                style:'font-size:12px;', 
                tooltip: '补款操作',
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('fillOperating_Win', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }    
});