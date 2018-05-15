var bagTypeStore = new Ext.data.ArrayStore({
    fields:['id','bagCode'],
    data:[
        ['1', '消费主钱包'],
        ['2', '水控主钱包'],
        ['3', '消费补助钱包']
    ],

});
var moneySourceStore = new Ext.data.ArrayStore({
    fields:['id','source'],
    data:[
        ['1', '现金'],
        ['2', '支票'],
        ['3', '转账'],
        ['4', '其它']
    ],

});
Ext.define("core.ykt.card.AccountOperatorForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.card.accountoperatorform",
    layout: "form",
    autoHeight: true,
    frame: false,
    fieldDefaults: { // 统一设置表单字段默认属性
        labelSeparator: '：', // 分隔符
        msgTarget: 'qtip',
        labelAlign: "right",
    },
    items: [{
        text: "主键",
        dataIndex: "id",
        hidden: true
    },{
        text: "卡号",
        dataIndex: "cardNo",
        hidden: true
    },{
        text: "用户id",
        name: "userId",
        hidden: true
    },{
        text: "钱包余额",
        name: "cardValue",
        hidden: true
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "钱包类型",
            name: "bagCode",
            xtype: "combobox",
            store:bagTypeStore,
            displayField:"bagCode",
            valueField:"id",
            value:"1",
            editable:false,
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        ref:"rechargeMoneyContainer",
        items: [{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "充值金额",
            name: "czMoney",
            xtype: "numberfield",
            allowBlank: false,
            emptyText: "请选择充值金额",
            listeners:{
               blur: function(){
                var form = this.up("form");
                var cardValue= form.query("field[name=cardValue]")[0].getValue();
                if(cardValue){
                   var czMoney= form.query("field[name=czMoney]")[0].getValue();
                   var czMoneyTotal= form.query("field[name=czMoneyTotal]")[0];
                   var count =parseFloat(cardValue)+parseFloat(czMoney) ;
                   czMoneyTotal.setValue(count);   

               }
               
             }
            } ,
        },{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "充值后余额",
            name:"czMoneyTotal",//cardValue+czMoneyTotal  充值金额+ 卡余
            xtype: "textfield",
            editable:false,
            //allowBlank: false,

        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        ref:"refundMoneyContainer",
        hidden:true,
        items: [ {
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "退款金额",
            name: "tkMoney",
            xtype: "numberfield",
            allowBlank: false,
            emptyText: "请选择退款金额",
            listeners:{
             blur: function(){
                var form = this.up("form");
                var cardValue= form.query("field[name=cardValue]")[0].getValue();
                if(cardValue){
                  var czMoney= form.query("field[name=czMoney]")[0].getValue();
                  var czMoneyTotal= form.query("field[name=czMoneyTotal]")[0];
                  var count =parseFloat(cardValue)-parseFloat(tkMoney) ;
                  czMoneyTotal.setValue(count);   
              }
              
            }
        } ,

        },{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "退款后余额",
            name:"czMoneyTotal",//cardValue-czMoneyTotal 卡余 -退款金额
            xtype: "textfield",
            editable:false,
            //allowBlank: false,

        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        ref:"putFeeContainer",
        items: [{
            columnWidth: 0.5,
            fieldLabel: "资金来源",
            name: "moneySource",
            xtype: "combobox",
            store:moneySourceStore,
            displayField:"source",
            valueField:"id",
            value:"1",
            editable:false,

        },{
            columnWidth: 0.5,
            fieldLabel: "收管理费",
            name: "commissionCharge",
            xtype: "textfield",
            editable:false,
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        ref:"qiutFeeContainer",
        hidden:true,
        items: [{
            columnWidth: 0.5,
            fieldLabel: "资金来源",
            name: "payStyle",
            xtype: "combobox",
            store:moneySourceStore,
            displayField:"source",
            valueField:"id",
            value:"1",
            editable:false,

        },{
            columnWidth: 0.5,
            fieldLabel: "退管理费",
            name: "commissionCharge",
            xtype: "textfield",
            editable:false,
           
        }]
    }/* ,{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.5,
            fieldLabel: "打印票据",
            name: "",
            xtype: "checkboxfield",
          }]
    }*/]

});