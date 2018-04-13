Ext.define("core.baseset.studentdorm.view.DormAllotDetailGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.baseset.studentdorm.dormallotdetailgrid",
    dataUrl: comm.get('baseUrl') + "/PtStudentDorm/onKeyList",
    model: "com.yc.q1.model.base.pt.build.PtStudentDorm",
    extParams: {
        whereSql:''
    },
    selModel:null,
    noPagging: true,
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '当前数据信息',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800
            }
        }]
    },
    columns:{
        defaults:{
            titleAlign:"center",
            align:"center"
        },
        items: [{
                width:100,
                text: "男生数量",
                dataIndex: "maleCount",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "女生数量",
                dataIndex: "femaleCount",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "<font color=red>合计总人数</font>",
                dataIndex: "studentCount",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "男生所需床位",
                dataIndex: "maleNeedBed",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "女生所需床位",
                dataIndex: "femaleNeedBed",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "<font color=red>合计所需床位</font>",
                dataIndex: "needDormBed",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "男生有效宿舍",
                dataIndex: "maleDorm",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "女生有效宿舍",
                dataIndex: "femaleDorm",
                field: {
                    xtype: "textfield"
                }
            }, {
                width:100,
                text: "混班有效宿舍",
                dataIndex: "mixedDorm",
                field: {
                    xtype: "textfield"
                }
            }, {
                flex:1,
                minWidth:100,
                text: "<font color=red>合计有效宿舍</font>",
                dataIndex: "validDorm",
                field: {
                    xtype: "textfield"
                }
            }
        ]

    }
});