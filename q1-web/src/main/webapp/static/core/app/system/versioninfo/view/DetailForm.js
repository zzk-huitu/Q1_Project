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
            //columnWidth:0.6, 
            width:500,
            xtype: "container",
            layout: "vbox",
            labelAlign: "right",
            items: [{               
                // beforeLabelTextTpl: comm.get("required"),
                // allowBlank: false,
                fieldLabel: "图标文字LOG",
                width:'100%',
                grow: true,
                // readOnly:true,
                name: "file1",
                xtype: "filefield",
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG'
            }]
        }, {
            xtype: "container",
            width:200,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
            height:35,
            margin:'0 0 0 10', 
            style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
            },
            items: [{
                width:200,   
                height:35,            
                xtype:'image',
                ref:'photoImage1',            
                src: '',
            }]
        }, {
            xtype: "container",
            items: [{
                width:'100%',   
                height:35,
                xtype: "label",
                margin:'5 0 0 5 ',
                style:{
                    lineHeight: '30px'
                },
                html: "<font color=red>（推荐600*105）</font>",
            }]
        }]
    }, {
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            width:500,
            xtype: "container",
            layout: "vbox",
            labelAlign: "right",
            items: [{               
                // beforeLabelTextTpl: comm.get("required"),
                // allowBlank: false,
                fieldLabel: "图标LOG",
                width:'100%',
                grow: true,
                // readOnly:true,
                name: "file2",
                xtype: "filefield",
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG'
            }]
        }, {
            xtype: "container",
            width:35,                  // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
            height:35,
            margin:'0 0 0 10', 
            style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
            },
            items: [{
                width:35,   
                height:35,            
                xtype:'image',
                ref:'photoImage2',            
                src: '',
            }]
        }, {
            xtype: "container",
            items: [{
                width:'100%',   
                height:35,
                xtype: "label",
                margin:'5 0 0 5 ',
                style:{
                    lineHeight: '30px'
                },
                html: "<font color=red>（推荐65*65）</font>",
            }]
        }]
    }, {
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            width:500,
            xtype: "container",
            layout: "vbox",
            labelAlign: "right",
            items: [{               
                // beforeLabelTextTpl: comm.get("required"),
                // allowBlank: false,
                fieldLabel: "文字LOG",
                width:'100%',
                grow: true,
                name: "file3",
                xtype: "filefield",
                // readOnly:true,
                style:{
                    lineHeight: '30px'
                },
                buttonText:"选择图片",
                emptyText :'支持文件格式：PNG | JPG | JPEG',
            }]
        }, {
            xtype: "container",
            width:200,                 // 这里设置的具体的宽度，那么上边的容器设置的columnWidth就会自动减少可用距离
            height:35,
            margin:'0 0 0 10', 
            style: {
                background: '#f5f5f5',
                border: '1px solid #e1e1e1'
            },
            items: [{
                width:200,   
                height:35,            
                xtype:'image',
                ref:'photoImage3',            
                src: '',
            }]
        }, {
            xtype: "container",
            items: [{
                width:'100%',   
                height:35,
                xtype: "label",
                margin:'5 0 0 5 ',
                style:{
                    lineHeight: '30px'
                },
                html: "<font color=red>（推荐320*50）</font>",
            }]
        }]
    },{
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            width:800, 
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
                hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "clientNameCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
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
            }]
        }]
    },{
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            width:800,  
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
                hidden: true
            }, {
                fieldLabel: "参数编码",
                width:'100%',
                grow: true,
                name: "serviceNameCode",
                xtype: "textfield",
                vtype:"alpha",
                maxLength: 20,
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
            }]
        }]
    },{
        xtype: "container",
        layout: "column",
        labelAlign: "right",
        flex:1,
        items: [{
            width:800, 
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
            }]
        }]
        
    }]

});