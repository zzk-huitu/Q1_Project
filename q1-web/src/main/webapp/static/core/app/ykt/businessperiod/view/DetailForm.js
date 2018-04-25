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
        xtype : "textfield",
        fieldLabel : "时段名称",
        name : "mealName",
    }, {
        xtype : "timefield",
        fieldLabel : "开始时间",
        format : 'H:i:s',
        name : "beginTime",
    }, {
        xtype : "timefield",
        fieldLabel : "结束时间",
        format : 'H:i:s',
        name : "endTime",
    }, {
        xtype : "textareafield",
        fieldLabel : "时段说明",
        name : "mealNotes",
    }]

});