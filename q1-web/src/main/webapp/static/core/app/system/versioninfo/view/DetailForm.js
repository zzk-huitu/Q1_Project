Ext.define("core.system.versioninfo.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.system.versioninfo.detailform",
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
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            columnWidth:0.61, 
            xtype: "container",
            layout: "vbox",
            labelAlign: "right",
            items: [{
                fieldLabel: "参数名称",
                width:'100%',
                grow: true,
                name: "sysParamName",
                xtype: "textfield",
                maxLength: 50,
                maxLengthText: "最多50个字符,汉字占2个字符",
                hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "sysParamCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
                maxLengthText: "最多20个字符",
                hidden: true
            }, {               
                beforeLabelTextTpl: comm.get("required"),
                allowBlank: false,
                fieldLabel: "登录LOG",
                width:'100%',
                grow: true,
                readOnly:true,
                name: "sysParamValue",
                xtype: "filefield",
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
                maxLength: 100,
                maxLengthText: "最多100个字符,汉字占2个字符"
            }]
    }, {
        xtype: "container",
        width:130,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
        height:30,
        margin:'0 0 0 10', 
        labelAlign: "right",
        style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
        },
        items: [{
                width:'100%',   
                height:238,            
                xtype:'image',
                ref:'photoImage',            
                src: '',
            }]
    }, {
        xtype: "container",
        labelAlign: "right",
        items: [{
                width:'100%',   
                height:238,
                xtype: "label",
                margin:'5 0 0 5 ',
                html: "<font color=red,size=12>（推荐65*65）</font>",
            }]
    }]
    }, {
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
                columnWidth:0.61, 
                xtype: "container",
                layout: "vbox",
                labelAlign: "right",
                items: [{
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "sysParamName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "sysParamCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
                maxLengthText: "最多20个字符",
                hidden: true
            }, {               
                beforeLabelTextTpl: comm.get("required"),
                allowBlank: false,
                fieldLabel: "标题LOG",
                width:'100%',
                grow: true,
                name: "sysParamValue",
                xtype: "filefield",
                readOnly:true,
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
                maxLength: 100,
                maxLengthText: "最多100个字符,汉字占2个字符"
            }]
        }, {
            xtype: "container",
            width:130,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
            height:30,
            margin:'0 0 0 10', 
            labelAlign: "right",
            style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
            },
            items: [{
                width:'100%',   
                height:238,            
                xtype:'image',
                ref:'photoImage',            
                src: '',
                }]
        }, {
            xtype: "container",
            labelAlign: "right",
            items: [{
                width:'100%',   
                height:238,
                xtype: "label",
                margin:'5 0 0 5 ',
                html: "<font color=red,size=12>（推荐65*65）</font>",
            }]
        }]
    }, {
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            columnWidth:0.61, 
            xtype: "container",
            layout: "vbox",
            labelAlign: "right",
            items: [{
                fieldLabel: "参数名称",
                width:'100%',
                grow: true,
                name: "sysParamName",
                xtype: "textfield",
                maxLength: 50,
                maxLengthText: "最多50个字符,汉字占2个字符",
                hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "sysParamCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
                maxLengthText: "最多20个字符",
                hidden: true
            }, {               
                beforeLabelTextTpl: comm.get("required"),
                allowBlank: false,
                fieldLabel: "标题文字图",
                width:'100%',
                grow: true,
                name: "sysParamValue",
                xtype: "filefield",
                readOnly:true,
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
                maxLength: 100,
                maxLengthText: "最多100个字符,汉字占2个字符"
                }]
            }, {
                xtype: "container",
                width:130,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
                height:30,
                margin:'0 0 0 10', 
                labelAlign: "right",
                style: {
                    background: '#f5f5f5',
                    border: '1px solid #e1e1e1'
                },
                items: [{
                    width:'100%',   
                    height:238,            
                    xtype:'image',
                    ref:'photoImage',            
                    src: '',
                }]
            }, {
                xtype: "container",
                labelAlign: "right",
                items: [{
                    width:'100%',   
                    height:238,
                    xtype: "label",
                    margin:'5 0 0 5 ',
                    html: "<font color=red,size=12>（推荐65*65）</font>",
                }]
            }]
        },{
            xtype: "container",
            layout: "column",
            labelAlign: "right",
            flex:1,
            items: [{
                columnWidth:0.61, 
                xtype: "container",
                layout: "vbox",
                labelAlign: "right",
                items: [{
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "sysParamName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
                }, {
                    fieldLabel: "参数编码",
                    width:'100%',
                    grow: true,
                    name: "sysParamCode",
                    xtype: "textfield",
                    vtype:"alpha",
                    maxLength: 20,
                    maxLengthText: "最多20个字符",
                    hidden: true
                }, {               
                    beforeLabelTextTpl: comm.get("required"),
                    allowBlank: false,
                    fieldLabel: "客户信息",
                    width:'100%',
                    grow: true,
                    name: "sysParamValue",
                    xtype: "textfield",
                    emptyText: '请输入客户信息(最大100个字符)',
                    blankText: "客户信息不能为空",
                    maxLength: 100,
                    maxLengthText: "最多100个字符,汉字占2个字符"
                }]
                }]
        },{
            xtype: "container",
            layout: "column",
            labelAlign: "right",
            flex:1,
            items: [{
                columnWidth:0.61, 
                xtype: "container",
                layout: "vbox",
                labelAlign: "right",
                items: [{
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "sysParamName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
                }, {
                    fieldLabel: "参数编码",
                    width:'100%',
                    grow: true,
                    name: "sysParamCode",
                    xtype: "textfield",
                    vtype:"alpha",
                    maxLength: 20,
                    maxLengthText: "最多20个字符",
                    hidden: true
                }, {               
                    beforeLabelTextTpl: comm.get("required"),
                    allowBlank: false,
                    fieldLabel: "公司信息",
                    width:'100%',
                    grow: true,
                    name: "sysParamValue",
                    xtype: "textfield",
                    emptyText: '请输入公司信息(最大100个字符)',
                    blankText: "公司信息不能为空",
                    maxLength: 100,
                    maxLengthText: "最多100个字符,汉字占2个字符"
                }]
                }]
        },{
            xtype: "container",
            layout: "column",
            labelAlign: "right",
            flex:1,
            items: [{
                columnWidth:0.61, 
                xtype: "container",
                layout: "vbox",
                labelAlign: "right",
                items: [{
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "sysParamName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
                }, {
                    fieldLabel: "参数编码",
                    width:'100%',
                    grow: true,
                    name: "sysParamCode",
                    xtype: "textfield",
                    vtype:"alpha",
                    maxLength: 20,
                    maxLengthText: "最多20个字符",
                    hidden: true
                }, {               
                    beforeLabelTextTpl: comm.get("required"),
                    allowBlank: false,
                    fieldLabel: "版本信息",
                    width:'100%',
                    grow: true,
                    name: "sysParamValue",
                    xtype: "textfield",
                    emptyText: '请输入版本信息(最大100个字符)',
                    blankText: "版本信息不能为空",
                    maxLength: 100,
                    maxLengthText: "最多100个字符,汉字占2个字符"
                }]
                }]
        
    }]

});