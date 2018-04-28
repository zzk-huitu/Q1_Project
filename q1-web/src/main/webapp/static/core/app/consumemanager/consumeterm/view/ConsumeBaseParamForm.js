/*var consumeModestore = new Ext.data.ArrayStore({
    fields: ['id', 'mode'],
    data: [
        [0, '定值'],
        [1, '编号'],
        [2, '单价']
    ]
});*/

Ext.define("core.consumemanager.consumeterm.view.ConsumeBaseParamForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumeterm.consumebaseparamform",
    //autoScroll: false,
    items :[/* {
       xtype: "fieldset",
       title: '消费设备基本参数',
       layout:'form',
       style: {
            fontSize: '16px',
            color: '#C44444',
            fontWeight:400,
            border: '#097db5 1px solid'
        },
        defaults:{
            width:'100%',
            margin:"10 5 0 5",
            xtype: "textfield"
        },
        items: [{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.5,
                name:'',
                fieldLabel: '消费模式',
                xtype: "combobox",
                store: consumeModestore,
                displayField: 'mode',
                valueField: 'id',
                value: 0,
                triggerAction: 'all',
                emptyText: '请选择...',
                blankText: '请选择',
                editable: false,
                allowBlank: false,
                mode: 'local'
               
            }, {
                columnWidth: 0.5,
                xtype: 'radiogroup',
                ref:'',
                fieldLabel: '开机模式',
                columns: 2,
                vertical: true,
                items: [            
                { boxLabel: '正常开机', name: '', inputValue: 0,checked: true , width:100 },        
                { boxLabel: '开机后需要刷营业卡方可消费', name: '', inputValue: 1},   //以下4个数据，对应QYLX区域类型的编号
                ],
                listeners:{ }
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.7,
                fieldLabel: '最大卡金额',
                xtype: 'numberfield',
                name:'tlvs[3].valInt',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: false
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        }]
    },*/ {
       xtype: "fieldset",
       title: '消费设备基础参数',
       layout:'form',
       style: {
            fontSize: '16px',
            color: '#C44444',
            fontWeight:400,
            border: '#097db5 1px solid'
        },
        defaults:{
            width:'100%',
            margin:"10 5 0 5",
            xtype: "textfield"
        },
        items:[{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.7,
                fieldLabel: '心跳间隔时间',
                xtype: 'numberfield',
                name:'tlvs[3].valInt',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: false
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>秒</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.7,
                fieldLabel: '通讯超时时间',
                xtype: 'numberfield',
                name:'tlvs[3].valInt',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: false
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>每单位100毫秒</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.45,
                fieldLabel: '服务器IP地址',
                xtype: 'textfield',
               // allowBlank: false,
           }, {
             columnWidth: 0.45,
             fieldLabel: '服务器端口',
             xtype: 'textfield',
               // allowBlank: false,
           }]
        }]
    }, {
       xtype: "fieldset",
       title: '批量设置基础参数',
       layout:'form',
       style: {
            fontSize: '16px',
            color: '#C44444',
            fontWeight:400,
            border: '#097db5 1px solid'
        },
        defaults:{
            width:'100%',
            margin:"10 5 0 5",
            xtype: "textfield"
        },
        items: [{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.5,
                name:'',
                fieldLabel: '设备类型',
                ddCode: "", //字典代码     
                xtype: "basecombobox",
                editable: false,
                allowBlank: false,
                emptyText: '设备类型',
                blankText: "设备类型不能为空"
               
            }]
        }]
    }],

    baseFormData: {
       
    }
});