Ext.define("core.baseset.dictionary.view.DicForm", {
	extend: "core.base.view.BaseForm",
	alias: "widget.baseset.dictionary.dicform",
	
	layout: "form",
	autoHeight: true,
	frame: false,
	fieldDefaults: { // 统一设置表单字段默认属性
		labelSeparator: '：', // 分隔符
		msgTarget: 'qtip',
		labelAlign: "right",
	},
	items: [{
		fieldLabel: '主键',
		name: "uuid",
		hidden: true
	}, {
		xtype: "textfield",
		fieldLabel: "上级字典Id",
		name: "parentNode",
		hidden:true
	}, {
		beforeLabelTextTpl: comm.get("required"),
		xtype: "textfield",
		fieldLabel: "上级字典",
		name: "parentName",
		readOnly: true
	}, {
		beforeLabelTextTpl: comm.get('required'),
		allowBlank: false,
		blankText: "字典名称不能为空",
		fieldLabel: '字典名称',
		name: "nodeText",
		maxLength: 16,
		emptyText: '请输入字典名称(最大16个字符)'
	}, {
		beforeLabelTextTpl: comm.get('required'),
		allowBlank: false,
		blankText: "字典编码不能为空",
		fieldLabel: '字典编码',
		name: "dicCode",
		maxLength: 16,
		emptyText: '请输入字典编码(最大16个字符)'
	}, {
		beforeLabelTextTpl: comm.get('required'),
		allowBlank: false,
		blankText: "排序号不能为空",
		fieldLabel: '排序号',
		name: "orderIndex",
		xtype: "numberfield",
		emptyText: "同级别字典的显示顺序",
		allowDecimals: false,
	}, {
		beforeLabelTextTpl: comm.get('required'),
		allowBlank: true,
		blankText: "字典类型不能为空",
		fieldLabel: '字典类型',
		name: "dicType",
		emptyText: '请选择字典类型',
		xtype: "basecombobox",
		ddCode: "DICTYPE"
	}, {
		beforeLabelTextTpl: '',
		allowBlank: true,
		blankText: "引用实体不能为空",
		fieldLabel: '引用实体路径',
		name: "refModel",
		maxLength: 256,
		emptyText: '请输入引用实体(最大256个字符)'
	}]
});