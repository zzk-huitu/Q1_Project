var consumeDateStroe = new Ext.data.ArrayStore({
    fields: ['date', 'consumeDate'],
    data: [
        ['month', '最近一个月'],
        ['halfYear', '最近半年'],
        ['year', '最近一年']
    ]
});
Ext.define("core.ykt.card.view.ConsumeDetailGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.card.consumedetailgrid",
    dataUrl: comm.get('baseUrl') + "/XfConsumeDetail/getUserConsumeList",
    model: 'com.yc.q1.model.storage.xf.XfConsumeDetail',
    al:false,
   // menuCode:"", //new：此表格与权限相关的菜单编码
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '消费信息',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800,
                lineHeight:'30px',
            }
        }/*,{
            name: "termNo", //字段名 ?
            xtype: "basecombobox",
            ddCode:"",
            value: "",
            emptyText: '请选择消费机类型',
            blankText: '请选择一个消费机类型', //消费设备
            editable: false,
            funCode: 'girdFastSearchText',
        }*/,{
            name: "consumeDate", //字段名
            xtype: "combobox",
            store: consumeDateStroe,
            displayField: 'consumeDate',
            valueField: 'date',
            value: "month",
            emptyText: '请选择一个消费时间段',
            blankText: '请选择一个消费时间段',
            editable: false,
            mode: 'local',
            funCode: 'girdFastSearchText',
        
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
            dataIndex: "userId",
            hidden: true
        },{
            text: "卡号",
            dataIndex: "cardNo",
            hidden: true
        },{
            text: "消费日期",
            dataIndex: "consumeDate",
            width:150,
            renderer:function(value){
                var partte=/^\d*$/;
                if(partte.test(value)){
                    return Ext.Date.format(new Date(value*1), 'Y/m/d');
                }else if(value.trim()!=""){
                    var date=value.replace(new RegExp(/-/gm) ,"/");       
                    return Ext.Date.format(new Date(date), 'Y/m/d');
                }else
                    return "";
                
                

            } 
        }, {
            text: "消费金额",
            dataIndex: "consumeValue",
            width:135,
            renderer:function(value){
                return "￥"+parseFloat(value).toFixed(2);
            }
        }, {
            text: "卡上余额",
            dataIndex: "cardValue",
            width:135,
            renderer:function(value){
                return "￥"+parseFloat(value).toFixed(2);
            }
        }, {
            text: "餐类",
            dataIndex: "mealName",
            width:135,
        },{
            text: "设备流水",
            dataIndex: "termRecordId",
            width:150,
        }]
    }    
});