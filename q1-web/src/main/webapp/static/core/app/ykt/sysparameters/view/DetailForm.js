Ext.define("core.ykt.sysparameters.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.sysparameters.detailform",
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
    }, {
        xtype: "textfield",
        fieldLabel: "参数名称",
        name: "sysParamName",
    }, {
        xtype: "numberfield",
        fieldLabel: "参数编码",
        name: "sysParamCode",
    }, {
        xtype: "textfield",
        fieldLabel: "参数值",
        name: "sysParamValue",
    }, {
        xtype: "textareafield",
        fieldLabel: "参数备注",
        name: "sysParamRemark",
    }]

});