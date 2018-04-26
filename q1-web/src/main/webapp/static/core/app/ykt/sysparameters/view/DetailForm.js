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
        beforeLabelTextTpl : comm.get("required"),
        xtype: "textfield",
        fieldLabel: "参数名称",
        name: "sysParamName",
        allowBlank: false,
        emptyText: '参数名称(最多50个字符)',
        blankText: '参数名称不能为空',
        maxLength: 50,
        maxLengthText: "最多50个字符",
    }, {
        beforeLabelTextTpl : comm.get("required"),
        xtype: "numberfield",
        fieldLabel: "参数编码",
        name: "sysParamCode",
        allowBlank: false,
        emptyText: '参数编码(最多30个字符)',
        blankText: '参数编码不能为空',
        maxLength: 20,
        maxLengthText: "最多20个字符",
    }, {
        beforeLabelTextTpl : comm.get("required"),
        xtype: "textfield",
        fieldLabel: "参数值",
        name: "sysParamValue",
        allowBlank: false,
        emptyText: '参数值(最多100个字符,汉字占2个字符)',
        blankText: '参数值不能为空',
        maxLength: 100,
        maxLengthText: "最多100个字符,汉字占2个字符",
    }, {
        xtype: "textareafield",
        fieldLabel: "参数备注",
        name: "sysParamRemark",
        emptyText: '参数备注(最多100个字符)',
        maxLength: 100,
        maxLengthText: "最多100个字符"
    }]

});