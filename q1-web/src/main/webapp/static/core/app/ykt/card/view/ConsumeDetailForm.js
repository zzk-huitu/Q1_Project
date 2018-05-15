
Ext.define("core.ykt.card.ConsumeDetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.card.consumedetailform",
    layout: "form",
    autoHeight: true,
    frame: false,
    fieldDefaults: { // 统一设置表单字段默认属性
        labelSeparator: '：', // 分隔符
        msgTarget: 'qtip',
        labelAlign: "right",
    },
  items: [{
        xtype: "textfield",
        fieldLabel: "主键",
        name: "id",
        hidden: true
    },{
        xtype: "textfield",
        fieldLabel: "卡号",
        name: "cardNo",
        hidden: true
    },{
        xtype: "textfield",
        fieldLabel: "用户id",
        name: "userId",
        hidden: true
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.35,
            fieldLabel: "消费机",
            name: "termName",
            xtype: "textfield",
            editable:false,
        }, {
            columnWidth: 0.35,
            fieldLabel: "消费金额",
            xtype: "textfield",
            name:"consumeValue",
            editable:false,
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.35,
            fieldLabel: "卡余额",
            name: "cardValue",
            xtype: "textfield",
            editable:false,
        }, {
            columnWidth: 0.35,
            fieldLabel: "消费日期",
            name:"consumeDate",
            xtype: "textfield",
            editable:false,
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.35,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "补款金额",
            name: "fillMoney",
            allowBlank: false,
            xtype: "numberfield",
            listeners:{
             blur: function(){
                var form = this.up("form");
                var cardValue= form.query("field[name=cardValue]")[0].getValue();
                var consumeValue= form.query("field[name=consumeValue]")[0].getValue();
                if(cardValue){
                 var fillMoney= form.query("field[name=fillMoney]")[0].getValue();
                 var operatorMoney= form.query("field[name=operatorMoney]")[0];
                 if(parseFloat(fillMoney)>parseFloat(consumeValue)){
                    Ext.example.msg("提示", "补款金额不能大于消费金额！");
                    operatorMoney.setValue("");
                    return ;

                }
                var count =parseFloat(cardValue)+parseFloat(fillMoney) ;
                operatorMoney.setValue(count);   
            }
              
            } ,
        }
    }, {
            columnWidth: 0.35,
            fieldLabel: "钱包类型",
            name:"bagType",
            xtype: "basecombobox",
            ddCode: "CARDBAGTYPE",
            editable:false,
            readOnly:true,
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.35,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "操作后余额",
            name: "operatorMoney",//补款金额+cardValue
            xtype: "textfield",
            editable:false,
            allowBlank: false,
        }, {
            columnWidth: 0.35,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "补款日期",
            name:"fillDate",
            value: new Date(),
            xtype: "datetimefield",

        }]
    }]

});