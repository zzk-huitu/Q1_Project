Ext.define("core.reportcenter.ptpowerresidue.view.RoomInfoTree", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.reportcenter.ptpowerresidue.roominfotree",
    dataUrl: comm.get('baseUrl') + "/MjUserRight/treeStuDormList",
    model: "com.yc.q1.pojo.base.pt.CommTree",
    expandFirst:true,
    sortableColumns:false,
    selModel: {
        //mode : 'single',
    },
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
            titleAlign:'right',
        }]
    },
    extParams: {
        whereSql: "",
        orderSql: "",
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
            flex: 1
        },/* {
            text: "顺序号",
            dataIndex: "orderIndex",
            width:60
        },*/{
            text:"主键",
            dataIndex:'id',
            hidden:true
        }]
    },
})