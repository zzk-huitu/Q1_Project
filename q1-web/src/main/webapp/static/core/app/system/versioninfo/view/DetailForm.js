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
                fieldLabel: "照片地址", //用于表单提交时，提交此数据
                name: "mainLogoId",
                xtype: "textfield",
                hidden: true
            }, {
                fieldLabel: "参数名称",
                width:'100%',
                grow: true,
                name: "mainLogoName",
                xtype: "textfield",
                maxLength: 50,
                maxLengthText: "最多50个字符,汉字占2个字符",
                hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "mainLogoCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
                maxLengthText: "最多20个字符",
                hidden: true
            }, {
                fieldLabel: "照片地址", //用于表单提交时，提交此数据
                name: "mainLogo",
                xtype: "textfield",
                hidden: true
            }, {               
                // beforeLabelTextTpl: comm.get("required"),
                // allowBlank: false,
                fieldLabel: "登录LOG",
                width:'100%',
                grow: true,
                // readOnly:true,
                name: "file1",
                xtype: "filefield",
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
                maxLength: 100,
                maxLengthText: "最多100个字符,汉字占2个字符"
            }]
    }, {
        xtype: "container",
        width:130,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
        height:35,
        margin:'0 0 0 10', 
        labelAlign: "right",
        style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
        },
        items: [{
                width:'100%',   
                height:35,            
                xtype:'image',
                ref:'photoImage1',            
                src: '',
            }]
    }, {
        xtype: "container",
        labelAlign: "right",
        items: [{
                width:'100%',   
                height:35,
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
                    fieldLabel: "照片地址", //用于表单提交时，提交此数据
                    name: "smallLogoId",
                    xtype: "textfield",
                    hidden: true
            }, {
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "smallLogoName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "smallLogoCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
                maxLengthText: "最多20个字符",
                hidden: true
            }, {
                fieldLabel: "照片地址", //用于表单提交时，提交此数据
                name: "smallLogo",
                xtype: "textfield",
                hidden: true
            }, {               
                // beforeLabelTextTpl: comm.get("required"),
                // allowBlank: false,
                fieldLabel: "标题LOG",
                width:'100%',
                grow: true,
                name: "file2",
                xtype: "filefield",
                // readOnly:true,
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
                maxLength: 100,
                maxLengthText: "最多100个字符,汉字占2个字符"
            }]
        }, {
            xtype: "container",
            width:130,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
            height:35,
            margin:'0 0 0 10', 
            labelAlign: "right",
            style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
            },
            items: [{
                width:'100%',   
                height:35,            
                xtype:'image',
                ref:'photoImage2',            
                src: '',
                }]
        }, {
            xtype: "container",
            labelAlign: "right",
            items: [{
                width:'100%',   
                height:35,
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
                fieldLabel: "照片地址", //用于表单提交时，提交此数据
                name: "schoolLogoId",
                xtype: "textfield",
                hidden: true
            }, {
                fieldLabel: "参数名称",
                width:'100%',
                grow: true,
                name: "schoolLogoName",
                xtype: "textfield",
                maxLength: 50,
                maxLengthText: "最多50个字符,汉字占2个字符",
                hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "schoolLogoCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
                maxLengthText: "最多20个字符",
                hidden: true
            }, {
                fieldLabel: "照片地址", //用于表单提交时，提交此数据
                name: "schoolLogo",
                xtype: "textfield",
                hidden: true
            }, {               
                // beforeLabelTextTpl: comm.get("required"),
                // allowBlank: false,
                fieldLabel: "标题文字图",
                width:'100%',
                grow: true,
                name: "file3",
                xtype: "filefield",
                // readOnly:true,
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
                maxLength: 100,
                maxLengthText: "最多100个字符,汉字占2个字符"
                }]
            }, {
                xtype: "container",
                width:130,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
                height:35,
                margin:'0 0 0 10', 
                labelAlign: "right",
                style: {
                    background: '#f5f5f5',
                    border: '1px solid #e1e1e1'
                },
                items: [{
                    width:'100%',   
                    height:35,            
                    xtype:'image',
                    ref:'photoImage3',            
                    src: '',
                }]
            }, {
                xtype: "container",
                labelAlign: "right",
                items: [{
                    width:'100%',   
                    height:35,
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
                    fieldLabel: "客户参数ID", //用于表单提交时，提交此数据
                    name: "clientNameId",
                    xtype: "textfield",
                    hidden: true
                }, {
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "clientNameName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
                }, {
                    fieldLabel: "参数编码",
                    width:'100%',
                    grow: true,
                    name: "clientNameCode",
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
                    name: "clientName",
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
                    fieldLabel: "公司参数ID", //用于表单提交时，提交此数据
                    name: "serviceNameId",
                    xtype: "textfield",
                    hidden: true
                }, {
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "serviceNameName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
                }, {
                    fieldLabel: "参数编码",
                    width:'100%',
                    grow: true,
                    name: "serviceNameCode",
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
                    name: "serviceName",
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
                    fieldLabel: "版本参数ID", //用于表单提交时，提交此数据
                    name: "varsionNameId",
                    xtype: "textfield",
                    hidden: true
                }, {
                    fieldLabel: "参数名称",
                    width:'100%',
                    grow: true,
                    name: "varsionNameName",
                    xtype: "textfield",
                    maxLength: 50,
                    maxLengthText: "最多50个字符,汉字占2个字符",
                    hidden: true
                }, {
                    fieldLabel: "参数编码",
                    width:'100%',
                    grow: true,
                    name: "varsionNameCode",
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
                    name: "varsionName",
                    xtype: "textfield",
                    emptyText: '请输入版本信息(最大100个字符)',
                    blankText: "版本信息不能为空",
                    maxLength: 100,
                    maxLengthText: "最多100个字符,汉字占2个字符"
                }]
                }]
        
    }]

});