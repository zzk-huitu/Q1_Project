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
        xtype: "container",
        layout: "column", // 从左往右的布局
        ref:"consumeContainer",
        items: [{
            columnWidth: 0.4,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "卡类名称",
           // readOnly:true,
            name: "cardTypeId",
            xtype: "combobox",
            store: {
                type: 'consumemanager.consumediscount.cardtypestore',
            },
            allowBlank: false,
            displayField: 'cardTypeName',
            valueField: 'cardTypeId',
            emptyText: "请选择卡类名称",
            editable: false,
        
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.3,
            xtype: 'checkboxgroup',
            ref:'xeStatus',
            fieldLabel: '是否启用消费限额',
            columns: 1,
            vertical: true,
            items: [            
                { boxLabel: '启用消费限额', name: 'xeStatus', inputValue: 0,checked: true , width:100 },        
            ],
            listeners:{
            }
            
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>（如需禁用消费限额，请取消勾选[启用消费限额]）</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
          //  beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "日限额",
            name: "dailyMoney",
        }, {
            columnWidth: 0.6,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元（ 如同时启用日限额和餐限额功能；消费时的判断顺序是先判断日限额再判断餐限额）</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
          //  beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue:100 ,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "早餐限额",
            name: "meal1Money",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元 （各种限制额度如填入0则表示不限制）</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
         //   beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "午餐限额",
            name: "meal2Money",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元 （各种限制额度如填入0则表示不限制）</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
          //  beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "晚餐限额",
            name: "meal3Money",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元 （各种限制额度如填入0则表示不限制）</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
           // beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue: 100,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "夜宵限额",
            name: "meal4Money",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元 （各种限制额度如填入0则表示不限制）</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            fieldLabel: "备注",
            name: "notes",
            xtype: "textarea",
            emptyText: "请输入卡类备注",
            maxLength: 512,
            height: 100,
            maxLengthText: "100"
        }]
    }/*, {
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
    }*/]
});