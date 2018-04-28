var consumeModestore = new Ext.data.ArrayStore({
    fields: ['id', 'mode'],
    data: [
        [0, '定值'],
        [1, '编号'],
        [2, '单价']
    ]
});

Ext.define("core.consumemanager.consumeterm.view.ConsumeHighParamForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumeterm.consumehighparamform",
    fieldDefaults: { // 统一设置表单字段默认属性
        xtype : 'textfield',
        labelSeparator: '：', // 分隔符
        labelWidth:150,
        labelAlign : 'right',
        msgTarget: 'qtip',
    },
    //autoScroll: false,
    items :[  {
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
                columnWidth: 0.45,
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
                mode: 'local',
                listeners:{
                    change :function(field,newValue){
                        var baseform=field.up("baseform");
                        var highParamSet=baseform.down('fieldset[ref=highParamSet]');
                        var useCardTYPE=baseform.down('fieldset[ref=useCardTYPE]');
                        var numberSet=baseform.down('fieldset[ref=numberSet]');
                        if(newValue==1){                            
                                numberSet.setVisible(true);
                                highParamSet.setVisible(false);
                                useCardTYPE.setVisible(false);
                            }else{
                                numberSet.setVisible(false);
                                highParamSet.setVisible(true);
                                useCardTYPE.setVisible(true);
                            }
                        }
                    }
               
            }, {
                columnWidth: 0.45,
                fieldLabel: '最大卡金额',
                xtype: 'numberfield',
                name:'tlvs[3].valInt',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.05,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [ {
                columnWidth: 0.7,
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
                columnWidth: 0.8,
                xtype: "label",
                html: "<div style='padding-left:30px;padding-top:40px;font-size:14px;line-height: 10px;text-align:left;color: rgb(196, 68, 68);'>"+
                "<br>模式说明:</br>"+
                "<br>单价：有营业员手工输入消费金额，其额度受单次最大消费限制</br>"+
                "<br>定值：每次使用固定的金额进行消费</br>"+
                "<br>编号：有营业员手工输入编号，实际消费额魏编号所代表的实际金额</br></div>"


            }]
        }]
    },{
       xtype: "fieldset",
       title: '消费设备高级参数设置',
       layout:'form',
       ref:"highParamSet",
       hidden:false,
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
        fieldDefaults: { // 统一设置表单字段默认属性
        xtype : 'textfield',
        labelSeparator: '：', // 分隔符
        labelWidth:170,
        labelAlign : 'right',
        msgTarget: 'qtip',
    },
        items: [{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '每天最大消费次数',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: false
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>每日限次</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '定值模式下的每次消费金额',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '每餐最大消费金额',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>每餐限额</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '二次消费间隔',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: false
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>分钟</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '每天最大消费次数',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: false
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>每餐限次</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '每次消费最大金额',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>单次限额</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.7,
                xtype: 'radiogroup',
                ref:'',
                fieldLabel: '卡片有效限制',
                columns: 2,
                vertical: true,
                items: [            
                { boxLabel: '启用', name: '', inputValue: 0,width:100 },        
                { boxLabel: '禁用', name: '', inputValue: 1,checked: true },   //以下4个数据，对应QYLX区域类型的编号
                ],
                listeners:{ }
            }]
        }]
    },{
        width:'99%',
        xtype: "fieldset",
        title: '允许使用的卡类',
        layout:'column',
        ref:"useCardTYPE",
        hidden:false,
        style: {
            fontSize: '16px',
            color: '#C44444',
            fontWeight:400,
            border: '#097db5 1px solid'
        },
        defaults:{
            width:'100%',
            margin:"10 5 0 5",
            xtype: "textfield",
            labelWidth:80
        },
        items: [{
            width:100,
            xtype: "button",
            text: "全选（反选）",
            ref: "checkall",        
        },{
            xtype: 'checkboxgroup',
            columns: 8,
            vertical: true,
            ref: 'comsumeTermChecbox',
            items: [],
            listeners: {
                render: function(component) {
                    for (var i = 1; i <= 8; i++) {
                        var checkbox = new Ext.form.Checkbox({
                            boxLabel: i + '类卡', //"Title"指的是返回的名字.
                            width: 100,
                            height: 25,
                            name: '',
                            inputValue: i,
                            checked: true
                        });
                        
                        component.items.add(checkbox);
                    }
                }
            }
        }]
    }, {
       xtype: "fieldset",
       title: '编号模式下的各编号金额',
       layout:'form',
       ref:"numberSet",
       hidden:true,
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
                columnWidth: 0.3,
                fieldLabel: '编号0',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '编号1',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '编号2',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '编号3',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '编号4',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '编号5',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '编号6',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '编号7',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.3,
                fieldLabel: '编号8',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }, {
                columnWidth: 0.3,
                fieldLabel: '编号9',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            }]
        }]
    }, {
       xtype: "fieldset",
       title: '营业时间段信息',
       layout:'form',
       style: {
            fontSize: '16px',
            color: '#C44444',
            fontWeight:400,
            border: '#097db5 1px solid'
        },
        defaults:{
            width:'99%',
            margin:"10 5 0 5",
            xtype: "textfield"
        },
         fieldDefaults: { 
                labelSeparator: '', 
                labelWidth:30,
                labelAlign : 'right',
            },
        items: [{
            width:150,
            xtype: "button",
            text: "获取营业时间段",
            ref: "getBusinessHours",        
        },{
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.2,
                xtype: 'label',
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>营业时间段(餐次)名称</div>"           
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>消费金额</div>"           
            }, {
                columnWidth: 0.2,
                xtype: 'label',
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>开始时间</div>"           
            }, {
                columnWidth: 0.2,
                xtype: "label",
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>结束时间</div>"           
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.2,
                xtype: 'label',
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>早餐：</div>"           
            }, {
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.05,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.2,
                xtype: 'label',
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>午餐：</div>"           
            }, {
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.05,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.2,
                xtype: 'label',
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>晚餐：</div>"           
            }, {
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.05,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.2,
                xtype: 'label',
                html: "<div style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;text-align:center;'>夜宵：</div>"           
            }, {
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true,
                step:0.01
            }, {
                columnWidth: 0.05,
                xtype: "label",
                html: "<font style='color: rgb(196, 68, 68);font-size: 14px;line-height: 30px;padding-left: 10px;'>元</font>"           
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.8,
                xtype: "label",
                html: "<div style='padding-left:30px;padding-top:40px;font-size:14px;line-height: 10px;text-align:left;color: rgb(196, 68, 68);'>"+
                "<br>时间段：仅在指定的时间段内允许消费，消费余额在营业时间段设置中单独设置。</br><br>某些机型中允许按卡类折扣，某些机型只有设置了时间段信息，则默认使用时间段消费模式。</br></div>"

            }]
        }]
    },{
           xtype: "fieldset",
           title: '自动重启时间',
           layout:'form',
           style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:400,
                border: '#097db5 1px solid'
            },
            defaults:{
                width:'99%',
                margin:"10 5 0 5",
                xtype: "textfield"
            },
            fieldDefaults: { 
                labelSeparator: '', 
                labelWidth:30,
                labelAlign : 'right',
            },
            items: [{
                xtype: "container",
                layout: "column", // 从左往右的布局
                items: [{
                   columnWidth: 0.2,
                   fieldLabel: ' ',
                   xtype: 'numberfield',
                   name:'',
                   value: '',
                   minValue: 0,
                   allowBlank: false,
                   allowDecimals: true
               }, {
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            },{
                columnWidth: 0.2,
                fieldLabel: ' ',
                xtype: 'numberfield',
                name:'',
                value: '',
                minValue: 0,
                allowBlank: false,
                allowDecimals: true
            }]
        }, {
            xtype: "container",
            layout: "column", // 从左往右的布局
            items: [{
                columnWidth: 0.8,
                xtype: "label",
                html: "<div style='padding-left:30px;padding-top:40px;font-size:14px;line-height: 10px;text-align:left;color: rgb(196, 68, 68);'>"+
                "<br>请设置4个时间点，终端将在这些时间点各重启一次</br><br>如果时间点设为00:00 ，则在该时间点时不重复</br></div>"

            }]
        }]
    }],

    baseFormData: {
       
    }
});