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
    }, {
        xtype: "textfield",
        name: "",
        hidden: true
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            fieldLabel: "卡类名称",
            readOnly:true,
            name: "",
        }, {
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            fieldLabel: "餐类名称",
            readOnly:true,
            name: "",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            xtype: 'checkboxgroup',
            ref:'',
            fieldLabel: '111',
            columns: 2,
            vertical: true,
            items: [            
                { boxLabel: '启用折扣消费', name: '', inputValue: 0,checked: true , width:100 },        
                { boxLabel: '按日折扣', name: '', inputValue: 1},  
            ],
            listeners:{
            }
            
        }]
    }, {
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
            fieldLabel: "第一次费率",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>%</font>"
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.7,
           // beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue:100 ,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "第二次费率",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>%</font>"
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
            fieldLabel: "第三次费率",
            name: "",
        }, {
            columnWidth: 0.2,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>%</font>"
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
            "<br>提示：</br><br>1. 各种费率如填入0则表示不限制</br>"+
            "<br>2. 如需禁用折扣消费，请取消勾选[启用折扣消费]</br></div>"
        }]
    }]
});