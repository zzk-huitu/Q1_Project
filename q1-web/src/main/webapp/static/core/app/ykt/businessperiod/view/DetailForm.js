Ext.define("core.ykt.businessperiod.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.businessperiod.detailform",
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
        name: "mealTypeId",
        hidden: true
    },{
        beforeLabelTextTpl: comm.get("required"),
        xtype : "textfield",
        fieldLabel : "时段名称",
        name : "mealName",
        allowBlank: false,
        emptyText: '时段名称(最多30个字符,汉字占2个字符)',
        blankText: '时段名称不能为空',
        maxLength: 30,
        maxLengthText: "最多30个字符,汉字占2个字符",
    }, {
        beforeLabelTextTpl: comm.get("required"),
        xtype : "timefield",
        fieldLabel : "开始时间",
        format : 'H:i:s',
        name : "beginTime",
        allowBlank: false,
        emptyText: '开始时间',
        blankText: '开始时间不能为空',
        maxLength: 8,
        maxLengthText: "最多8个字符",
    }, {
        beforeLabelTextTpl: comm.get("required"),
        xtype : "timefield",
        fieldLabel : "结束时间",
        format : 'H:i:s',
        name : "endTime",
        allowBlank: false,
        emptyText: '结束时间',
        blankText: '结束时间不能为空',
        maxLength: 8,
        maxLengthText: "最多8个字符",
    }, {
        beforeLabelTextTpl: comm.get("required"),
        xtype : "textareafield",
        fieldLabel : "时段说明",
        name : "mealNotes",
        allowBlank: false,
        emptyText: '时段说明(最多200个字符)',
        blankText: '时段说明不能为空',
        maxLength: 200,
        maxLengthText: "最多200个字符",
    }]

});