Ext.define("core.consumemanager.consumediscount.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumediscount.detailform",
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
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        ref:"consumeContainer",
        items: [{
            columnWidth: 0.45,
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
            editable: false         
        }, {
            columnWidth: 0.45,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "餐类名称",
           // readOnly:true,
            name:"mealTypeId",
            xtype: "combobox",
            store: {
                type: 'consumemanager.consumediscount.mealtypestore',
            },
            allowBlank: false,
            displayField: 'mealName',
            valueField: 'mealTypeId',
            emptyText: "请选择餐类名称",
            editable: false            

        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.3,
            xtype: 'checkboxgroup',
            ref:'rateStatus',
            fieldLabel: '折扣状态',
            columns: 1,
            vertical: true,
            items: [            
                { boxLabel: '启用折扣消费', name: 'rateStatus', inputValue: 0, checked: true,width:100 },        
            ],
            listeners:{
            }
            
        },{
            columnWidth: 0.4,
            xtype: "label",
           html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>（如需禁用折扣消费，请取消勾选[启用折扣消费]）</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            xtype: 'checkboxgroup',
            ref:'rateType',
            fieldLabel: '日折扣',
            columns: 1,
            vertical: true,
            items: [            
                { boxLabel: '按日折扣', name: 'rateType', inputValue: 0,width:100 },  
            ],
            listeners:{
            }
            
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
            fieldLabel: "第一次费率",
            name: "ratePrice1",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>% （各种费率如填入0则表示不限制）</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
           // beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue:100 ,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "第二次费率",
            name: "ratePrice2",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>% （各种费率如填入0则表示不限制）</font>"
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
            fieldLabel: "第三次费率",
            name: "ratePrice3",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>% （各种费率如填入0则表示不限制）</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
              columnWidth: 0.9,
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
            "<br>提示：</br><br>1. 各种费率如填入0则表示不限制</br>"+
            "<br>2. 如需禁用折扣消费，请取消勾选[启用折扣消费]</br></div>"
        }]
    }*/]
});