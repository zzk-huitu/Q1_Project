Ext.define("core.consumemanager.consumelimit.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumelimit.detailform",
    fieldDefaults: { // 统一设置表单字段默认属性
        labelSeparator: '：', // 分隔符
        msgTarget: 'qtip',
        labelAlign: "right",
        labelWidth: 120,     //label 的寬度
    },
     items: [{
        fieldLabel: "主键",
        name: "id",
        xtype: "textfield",
        hidden: true
    }, {
        xtype: "textfield",
        name: "",
        hidden: true
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            fieldLabel: "卡类名称",
            readOnly:true,
            name: "",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            xtype: 'radiogroup',
            ref:'',
            fieldLabel: '222',
            columns: 1,
            vertical: true,
            items: [            
                { boxLabel: '启用消费限额', name: '', inputValue: 0,checked: true , width:100 },        
            ],
            listeners:{
            }
            
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.7,
          //  beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "日限额",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.7,
          //  beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue:100 ,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "早餐限额",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.7,
         //   beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "午餐限额",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.7,
          //  beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "晚餐限额",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.7,
           // beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "夜宵限额",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"
        }]
    },{
        fieldLabel: "备注",
        name: "",
        xtype: "textarea",
        emptyText: "请输入卡类备注",
        maxLength: 512,
        height: 100,
        maxLengthText: ""
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            xtype: "label",
            html: "<div style='padding-left:120px;padding-top:40px;font-size:14px;line-height: 10px;text-align:left;color: rgb(196, 68, 68);'>"+
            "<br>提示：</br><br>1. 各种限制额度如填入0则表示不限制</br>"+
            "<br>2. 如需禁用消费限额，请取消勾选[启用消费限额]</br>"+
            "<br>3. 如同时启用日限额和餐限额功能；消费时的判断顺序是先判断日限额再判断餐限额</br></div>"
        }]
    }]
});