Ext.define("core.baseset.teacherdorm.view.TeacherDormTree", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.baseset.teacherdorm.teacherdormtree",
    dataUrl: comm.get('baseUrl') + "/PtTeacherDorm/treelist",
    model: "com.yc.q1.pojo.base.pt.RoomAreaTree",
    selModel: {},
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
            titleAlign:'right',
        }]
    },
    extParams: {
        whereSql: ""
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