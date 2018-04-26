Ext.define("core.ykt.cardtype.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.cardtype.detailform",
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
        name: "cardTypeId",
        hidden: true
    }, {
		beforeLabelTextTpl: comm.get("required"),
		xtype : "textfield",
		fieldLabel : "卡类名称",
		name : "cardTypeName",
		allowBlank: false,
        emptyText: '卡类名称',
        blankText: '卡类名称不能为空',
        maxLength: 20,
        maxLengthText: "最多20个字符",
	}, {
		xtype : "checkboxfield",
		fieldLabel : "是否有效",
		name : "useFlag",
	}, {
		xtype : "numberfield",
		fieldLabel : "工本费",
		name : "issueFee",
		allowDecimals: true, 
		decimalPrecision: 2
	}, {
		xtype: 'numberfield',
		fieldLabel : "折旧费率",
		name : "issueFee",
		allowDecimals: true, 
		decimalPrecision: 2
	} , {
		xtype : "numberfield",
		fieldLabel : "管理费",
		name : "commissionCharge",
		allowDecimals: true, 
		decimalPrecision: 2
	}, {
		xtype : "numberfield",
		fieldLabel : "卡押金",
		name : "deposit",
		allowDecimals: true, 
		decimalPrecision: 2
	}, {
		xtype: 'textareafield',
		fieldLabel : "卡类描述",
		name : "cardNotes",
	} ]

});