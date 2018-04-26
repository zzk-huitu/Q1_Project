Ext.define("core.ykt.clerkmanger.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.clerkmanger.detailform",
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
        fieldLabel: "营业账户ID",
        name: "accountId",
        hidden: true
    },{
        beforeLabelTextTpl : comm.get("required"),
        fieldLabel : "营业员名称",
        name : "operatorName",
        xtype : "textfield",
        allowBlank : false
    }, {
        beforeLabelTextTpl : comm.get("required"),
        fieldLabel : "营业账户",
        name : "accountName",
        xtype: "basefuncfield",
        funcController: "core.ykt.accountmanger.controller.MainController", //该功能主控制器
        funcPanel: "ykt.accountmanger.mainlayout", //该功能显示的主视图
        funcTitle: "营业账户查询", //查询窗口的标题
        configInfo: {
        width:"1000px",
        fieldInfo: "accountId~accountName,id~accountName", //将该功能下指定的字段带入该表单
        whereSql: " and 1=1", //查询条件，带#字段#，使用该表单中的字段值拼接查询条件
        muiltSelect: false //是否多选
          },
          emptyText: '营业账户', //指定的是文本框中的文字
          blankText: "营业账户不能为空"
    }, {
        beforeLabelTextTpl : comm.get("required"),
        fieldLabel : "密码",
        name : "password",
        xtype : "textfield",
        inputType : 'password',
        allowBlank : false
    }, {
        fieldLabel : "卡号流水",
        name : "operatorCardId",
        xtype : "textfield",
        readOnly:true
    }, {
        fieldLabel : "物理卡号",
        name : "factoryFixId",
        xtype : "textfield",
        readOnly:true
    }, {
        
        fieldLabel: "应用系统",
        name: "useType",
        xtype: "basecombobox",
        ddCode: "YYXT",
        emptyText: '请选择...',
        blankText: '请选择状态',
    },{
        fieldLabel : "有效期",
        name : "validDate",
        xtype: "datetimefield",
        format:'Y-m-d H:i:s'
    },{
        fieldLabel : "营业员描述",
        name : "operatorNotes",
        xtype : "textareafield"
    } ]

});