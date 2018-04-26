Ext.define("core.ykt.workstation.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.ykt.workstation.detailform",
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
        xtype : "textfield",
        fieldLabel : "工作站名称",
        name : "workStationName",
        emptyText: '工作站名称(最多30个字符)',
        maxLength: 30,
        maxLengthText: "最多30个字符",
    }, {
        xtype : "textfield",
        fieldLabel : "计算机名称",
        name : "computerName",
        emptyText: '计算机名称(最多50个字符,汉字占两个字符)', 
        maxLength: 50,
        maxLengthText: "最多50个字符(汉字占两个字符)",
    }, {
        xtype: 'textfield',
        fieldLabel : "IP地址",
        name : "workStationIP",
        regex: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
        emptyText: 'IP地址',
        regexText: 'IP格式为：xxx.xxx.xxx.xxx'
    }, {
        xtype : "textfield",
        fieldLabel : "MAC地址",
        name : "nic",
        emptyText: 'MAC地址(最多100个字符)', 
        maxLength: 100,
        maxLengthText: "最多100个字符",
    }, {
        xtype : "numberfield",
        fieldLabel : "服务器端口",
        name : "msServerPort",
    }, {
        xtype: 'numberfield',
        fieldLabel : "最大金额",
        name : "maxCardFree",
    }, {
        xtype: 'textareafield',
        fieldLabel : "工作站描述",
        name : "workStationNotes",
        emptyText: '工作站描述(最多200个字符)',
        maxLength: 200,
        maxLengthText: "最多200个字符",
    }]

});