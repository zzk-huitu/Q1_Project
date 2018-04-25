Ext.define("core.ykt.accountmanger.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.accountmanger.detailform",
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
        beforeLabelTextTpl: comm.get("required"),
        xtype: "textfield",
        fieldLabel: "账户名称",
        name: "accountName",
        allowBlank: false,
        emptyText: '账户名称',
        blankText: '账户名称不能为空'
    }, {
        beforeLabelTextTpl: comm.get("required"),
        xtype: "textfield",
        fieldLabel: "账户编号",
        name: "accountNo",
        allowBlank: false,
        emptyText: '账户编号',
        blankText: '账户编号不能为空'   
    }, {
        xtype: "textfield",
        fieldLabel: "管理员姓名",
        name: "adminName"
    }, {
        xtype: "textfield",
        fieldLabel: "证件号码",
        name: "idCard"
    }, {
        xtype: "textfield",
        fieldLabel: "查询密码",
        name: "accountPwd",
        inputType: 'password'
    }, {
        xtype: "textfield",
        fieldLabel: "联系电话",
        name: "accountOwnerTel"
    }, {
        xtype: "datetimefield",
        fieldLabel: "建账日期",
        value: new Date(),
        dateType: "datetime",
        name: "createTime"
    }, {
        xtype: "checkboxfield",
        fieldLabel: "账户状态",
        name: "accountStatus",
    }, {
        xtype: "textareafield",
        fieldLabel: "账户描述",
        name: "accountDescription"
    }]

});