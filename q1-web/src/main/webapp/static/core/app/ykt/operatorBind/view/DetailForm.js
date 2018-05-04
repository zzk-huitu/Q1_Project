Ext.define("core.ykt.operatorBind.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.operatorBind.detailform",
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
    }]

});