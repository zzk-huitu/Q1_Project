Ext.define("core.ykt.operatorBind.view.AreaGrid", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.ykt.operatorBind.areagrid",
    dataUrl: comm.get('baseUrl') + "/PtRoomArea/list",
    model: "com.yc.q1.pojo.base.pt.RoomAreaTree",
    al: true,
    expandFirst:true,
    sortableColumns:false,
    extParams: {
        whereSql: " and isDelete='0' ",
        orderSql: "",
        excludes:"checked"
            //filter: "[{'type':'string','comparison':'=','value':'0','field':'isDelete'}]"
    },
    selModel: {
      
    },
    tbar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '区域信息',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800
            }
        }, '->',{
            xtype: 'button',
            text: '刷新',
            ref: 'gridRefresh',
            iconCls: 'x-fa fa-refresh'
        }]
    },
    columns:  {        
        defaults:{
            titleAlign:"center"
        },
        items: [{
            text: "区域名称",
            dataIndex: "text",
            xtype: 'treecolumn',
            //width: 250
            flex:1
        }, {
            text: "区域类型",
            dataIndex: "areaType",
            columnType: "basecombobox", //列类型
            ddCode: "BUILDAREATYPE", //字典代码        
            width:80,
        }, {
            text: "主键",
            dataIndex: 'id',
            hidden: true
        }]
    },


})