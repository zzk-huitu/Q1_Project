Ext.define("core.wisdomclass.roomterm.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.wisdomclass.roomterm.detailform",
    fieldDefaults: { // 统一设置表单字段默认属性
        labelSeparator: "：", // 分隔符
        msgTarget: "qtip",
        labelWidth: 120,
        labelAlign: "right"
    },
    items: [{
        xtype: "textfield",
        fieldLabel: "主键",
        name: "roomId",
        hidden: true
    }, {
        beforeLabelTextTpl: comm.get('required'),
        xtype: "textfield",
        fieldLabel: "房间名称",
        name: "roomName",
        readOnly: true
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            xtype: "textfield",
            fieldLabel: "终端ID1",
            name: "terminalId1",
            hidden: true
        }, {
            columnWidth: 0.4,
            fieldLabel: "门牌号1",
            xtype: "textfield",
            name: "houseNo01",
            readOnly: true
        }, {
            columnWidth: 0.6,
            fieldLabel: "分配终端",
            xtype: "basefuncfield",
            name: "terminalNo1",
            refController: "wisdomclass.roomterm.othercontroller", //该功能主控制器
            funcPanel: "baseset.terminal.mainlayout", //该功能显示的主视图
            funcTitle: "选择终端", //查询窗口的标题
            configInfo: {
                fieldInfo: "terminalId1~terminalNo1,id~terminalNo",
                whereSql: " and 1=1 and isDelete='0' and isUse='0' ",
                filter: "[{'type':'boolean','comparison':'=','value':'false','field':'isUse'}]",
                muiltSelect: false, //是否多选
                width :950,
                height:500,
            }
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            xtype: "textfield",
            fieldLabel: "终端ID2",
            name: "terminalId2",
            hidden: true
        }, {
            columnWidth: 0.4,
            xtype: "textfield",
            fieldLabel: "门牌号2",
            name: "houseNo02",
            readOnly: true
        }, {
            columnWidth: 0.6,
            fieldLabel: "分配终端",
            xtype: "basefuncfield",
            name: "terminalNo2",
            refController: "wisdomclass.roomterm.othercontroller", //该功能主控制器
            funcPanel: "baseset.terminal.mainlayout", //该功能显示的主视图
            funcTitle: "选择终端", //查询窗口的标题
            configInfo: {
                fieldInfo: "terminalId2~terminalNo2,id~terminalNo",
                whereSql: " and 1=1 and isDelete='0' ",
                filter: "[{'type':'boolean','comparison':'=','value':'false','field':'isUse'}]",
                muiltSelect: false, //是否多选
                width :950,
                height:500,
            }
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            xtype: "textfield",
            fieldLabel: "终端ID3",
            name: "terminalId3",
            hidden: true
        }, {
            columnWidth: 0.4,
            xtype: "textfield",
            fieldLabel: "门牌号3",
            name: "houseNo03",
            readOnly: true
        }, {
            columnWidth: 0.6,
            fieldLabel: "分配终端",
            xtype: "basefuncfield",
            name: "terminalNo3",
            refController: "wisdomclass.roomterm.othercontroller", //该功能主控制器
            funcPanel: "baseset.terminal.mainlayout", //该功能显示的主视图
            funcTitle: "选择终端", //查询窗口的标题
            configInfo: {
                fieldInfo: "terminalId3~terminalNo3,id~terminalNo",
                whereSql: " and 1=1 and isDelete='0' ",
                filter: "[{'type':'boolean','comparison':'=','value':'false','field':'isUse'}]",
                muiltSelect: false, //是否多选
                width :950,
                height:500,
            }
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            xtype: "textfield",
            fieldLabel: "终端ID4",
            name: "terminalId4",
            hidden: true
        }, {
            columnWidth: 0.4,
            xtype: "textfield",
            fieldLabel: "门牌号4",
            name: "houseNo04",
            readOnly: true
        }, {
            columnWidth: 0.6,
            fieldLabel: "分配终端",
            xtype: "basefuncfield",
            name: "terminalNo4",
            refController: "wisdomclass.roomterm.othercontroller", //该功能主控制器
            funcPanel: "baseset.terminal.mainlayout", //该功能显示的主视图
            funcTitle: "选择终端", //查询窗口的标题
            configInfo: {
                fieldInfo: "terminalId4~terminalNo4,id~terminalNo",
                whereSql: " and 1=1 and isDelete='0' ",
                filter: "[{'type':'boolean','comparison':'=','value':'false','field':'isUse'}]",
                muiltSelect: false, //是否多选
                width :950,
                height:500,
            }
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            xtype: "textfield",
            fieldLabel: "终端ID5",
            name: "terminalId5",
            hidden: true
        }, {
            columnWidth: 0.4,
            xtype: "textfield",
            fieldLabel: "门牌号5",
            name: "houseNo05",
            readOnly: true
        }, {
            columnWidth: 0.6,
            fieldLabel: "分配终端",
            xtype: "basefuncfield",
            name: "terminalNo5",
            refController: "wisdomclass.roomterm.othercontroller", //该功能主控制器
            funcPanel: "baseset.terminal.mainlayout", //该功能显示的主视图
            funcTitle: "选择终端", //查询窗口的标题
            configInfo: {
                fieldInfo: "terminalId5~terminalNo5,id~terminalNo",
                whereSql: " and 1=1 and isDelete='0' and isUse='0' ",
                filter: "[{'type':'boolean','comparison':'=','value':'false','field':'isUse'}]",
                muiltSelect: false ,//是否多选
                width :950,
                height:500,
            }
        }]
    }]
});