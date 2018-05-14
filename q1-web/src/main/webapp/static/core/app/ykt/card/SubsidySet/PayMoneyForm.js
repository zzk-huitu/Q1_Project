Ext.define("core.ykt.card.SubsidySet.PayMoneyForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.card.SubsidySet.paymoneyform",
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
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.35,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "充值金额",
            name: "fillMoney",
            xtype: "numberfield",
            allowBlank: false,
            emptyText: "请选择充值金额",
            value:0.1,
            allowDecimals:true,
            decimalPrecision:2,
            minValue:0.1,
            maxValue:5000,
        }, {
            columnWidth: 0.35,
            beforeLabelTextTpl: comm.get('required'),
            fieldLabel: "到账日期",
            name:"fillDate",
            value: new Date(),
            xtype: "datetimefield",
            dateType:'date',  
            emptyText: "请选择到账日期",

        },{
            columnWidth: 0.1,
            fieldLabel: "当月允许重复",
            name: "allowRepeat",
            xtype: "checkboxfield",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.9,
            fieldLabel: "描述",
            name: "notes",
            xtype: "textarea",
            emptyText: "请输入描述",
            maxLength: 512,
            height: 50,
            maxLengthText: "100"
        }]
    }]

});