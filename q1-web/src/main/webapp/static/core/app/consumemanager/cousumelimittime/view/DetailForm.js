Ext.define("core.consumemanager.consumelimittime.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumelimittime.detailform",
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
            //readOnly:true,
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
            ref:'xcStatus',
            fieldLabel: '是否启用消费限次',
            columns: 1,
            vertical: true,
            items: [            
                { boxLabel: '启用消费限次', name: 'xcStatus', inputValue: 0,checked: true , width:100 },        
            ],
            listeners:{
            }
            
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>（如需禁用消费限次，请取消勾选[启用消费限次]）</font>"
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
            fieldLabel: "日限次",
            name: "dailyCount",
        }, {
            columnWidth: 0.6,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>次（如同时启用日限次和餐限次功能；消费时的判断顺序是先判断日限次再判断餐限次）</font>"
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
            fieldLabel: "早餐限次",
            name: "meal1Count",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>次 （各种限制次数如填入0则表示不限制）</font>"
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
            fieldLabel: "午餐限次",
            name: "meal2Count",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>次 （各种限制次数如填入0则表示不限制）</font>"
        }]
    },{
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.4,
            //beforeLabelTextTpl: comm.get('required'),
            xtype: "numberfield",
            value: '0',
            maxValue:100 ,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: "晚餐限次",
            name: "meal3Count",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>次 （各种限制次数如填入0则表示不限制）</font>"
        }]
    },{
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
            fieldLabel: "夜宵限次",
            name: "meal4Count",
        }, {
            columnWidth: 0.4,
            xtype: "label",
            html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>次 （各种限制次数如填入0则表示不限制）</font>"
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
            "<br>提示：</br><br>1. 各种限制次数如填入0则表示不限制</br>"+
            "<br>2. 如需禁用消费限次，请取消勾选[启用消费限次]</br>"+
            "<br>3. 如同时启用日限次和餐限次功能；消费时的判断顺序是先判断日限次再判断餐限次</br></div>"
        }]
    }*/]
});