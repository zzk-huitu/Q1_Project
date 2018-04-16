Ext.define("core.baseset.calendar.view.CalendarForm", {
	extend: "core.base.view.BaseForm",
	alias: "widget.baseset.calendar.calendarform",

	defaults:{
		width:400,
		margin:"10 5 0 5",
	},
	items: [{
		fieldLabel: "主键",
		name: "id",
		xtype: "textfield",
		hidden: true
	}, {
		fieldLabel: "标题",
		name: "calenderName",
		xtype: "textfield",
		beforeLabelTextTpl: comm.get('required'),
		allowBlank: false,
		maxLength: 64,
	}, {
		fieldLabel: "适用校区ID",
		name: "campusId",
		xtype: "textfield",
		hidden: true
	}, {
		beforeLabelTextTpl: comm.get("required"),
		fieldLabel: '适用校区',
		name: "campusName",
		xtype: 'basefuncfield',
		allowBlank: false,
		refController: "baseset.calendar.othercontroller", //该功能主控制器
		formPanel:"baseset.calendar.calendarform",
		funcPanel: "baseset.campus.mainlayout", //该功能显示的主视图
		funcTitle: "所属校区", //查询窗口的标题
		configInfo: {
			fieldInfo: "campusId~campusName,id~campusName",
			whereSql: " and isDelete='0' ",
			width :850,
			height:500,
			muiltSelect: false //是否多选
		}
	}, {
		beforeLabelTextTpl: comm.get('required'),
        xtype: "basecombobox",
        fieldLabel: "学段",
        name: "sectionCode",
        ddCode: "RKXD",
        allowBlank: false,
        emptyText: '请选择学段',
        blankText: "学段不能为空",
	},{
		fieldLabel: "生效时间",
		name: "activityTime",
		xtype: "datefield",
		format: 'Y年m月d日',
		beforeLabelTextTpl: comm.get('required'),
		allowBlank: false,
		editable: false
	}]
});