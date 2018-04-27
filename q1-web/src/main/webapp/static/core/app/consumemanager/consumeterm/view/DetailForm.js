Ext.define("core.consumemanager.consumeterm.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumeterm.detailform",
    fieldDefaults: { // 统一设置表单字段默认属性
        labelSeparator: '：', // 分隔符
        msgTarget: 'qtip',
        labelAlign: "right",
        labelWidth: 120,     //label 的寬度
    },
    items: [{
        fieldLabel: "主键",
        name: "id",
        xtype: "textfield",
        hidden: true
    }, {
        xtype: "textfield",
        name: "",
        hidden: true
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            allowBlank: false,
            blankText: "终端号不能为空",
            fieldLabel: "终端号",
            name: "",
        }, {
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            allowBlank: false,
            blankText: "终端名称不能为空",
            fieldLabel: "终端名称",
            name: "",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            allowBlank: false,
            blankText: "工作区域不能为空",
            xtype: "basecombobox",
            name: "",
            ddCode: "",
            fieldLabel: "工作区域",
            name: "",
        },{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            allowBlank: false,
            blankText: "结算账户不能为空",
            xtype: "basecombobox",
            name: "",
            ddCode: "",
            fieldLabel: "结算账户",
            name: "",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            allowBlank: false,
            blankText: "终端类型不能为空",
            xtype: "basecombobox",
            name: "",
            ddCode: "",
            fieldLabel: "终端类型",
            name: "",
        }, {
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            allowBlank: false,
            blankText: "IP地址不能为空",
            fieldLabel: "IP地址",
            name: "",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [ {
            columnWidth: 0.5,
            beforeLabelTextTpl: comm.get('required'),
            xtype: "textfield",
            allowBlank: false,
            blankText: "端口不能为空",
            fieldLabel: "端口",
            name: "",
        }, {
            columnWidth: 0.5,
           // beforeLabelTextTpl: comm.get('required'),
            xtype: "basecombobox",
            name: "",
            ddCode: "",
            fieldLabel: "消费模式",
            name: "",
        }]
    }, {
        xtype: "container",
        layout: "column", // 从左往右的布局
        items: [{
            columnWidth: 0.8,
            xtype: 'checkboxgroup',
            ref:'',
            fieldLabel: '111',
            columns: 2,
            vertical: true,
            items: [            
                { boxLabel: '终端状态', name: '', inputValue: 0, width:100 },        
                { boxLabel: '自动下参数', name: '', inputValue: 1},  
            ],
            listeners:{
            }
            
        }]
    },{
        fieldLabel: "说明",
        name: "",
        xtype: "textarea",
        emptyText: "",
        maxLength: 512,
        height: 100,
        maxLengthText: ""
    }]
});