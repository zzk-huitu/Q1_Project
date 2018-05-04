Ext.define("core.baseset.roomallot.view.RoomAllotTree", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.baseset.roomallot.roomallottree",
    dataUrl: comm.get('baseUrl') + "/PtOfficeAllot/treelist",
    model: "com.yc.q1.pojo.base.pt.RoomAreaTree",
    selModel: {},
    al: true,
    expandFirst:true,
    sortableColumns:false,
    tbar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '区域信息',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:600
            }
        }, '->',{
            xtype: 'button',
            text: '刷新',
            ref: 'gridRefresh',
            iconCls: 'x-fa fa-refresh',
            titleAlign:'right'
        }]
    },
    extParams: {
        whereSql: "",
        excludes:"checked"
    },

    columns:{
        defaults:{
            titleAlign:"center"
        },
        items:[{
            text: "区域名称",
            dataIndex: "text",
            xtype:'treecolumn',
            flex: 1,
            minWidth:200
        },/* {
            text: "顺序号",
            dataIndex: "orderIndex",
            width:80
        },*/{
            text:"主键",
            dataIndex:'id',
            hidden:true
        }]
    },
})