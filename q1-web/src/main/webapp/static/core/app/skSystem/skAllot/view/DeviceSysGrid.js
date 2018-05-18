Ext.define("core.skSystem.skAllot.view.DeviceSysGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.skSystem.skAllot.deviceSysGrid",
    //title: "<font color='#ffeb00'>选中的设备 (双击移除)</font>",
    columnLines: true,
    loadMask: true,
    multiSelect: true,
    selModel: {
        selType: "checkboxmodel",
        width: 10
    },
    viewConfig: {
        stripeRows: true
    },
    store: {
        type: "skSystem.skAllot.isSelectStore"
    },

    tbar:[{
        xtype: 'tbtext',
        html: '选中的设备（双击移除）',
        style: {
            fontSize: '16px',
            color: '#C44444',
            fontWeight:800,
            lineHeight:'32px'
        }
    }],

    columns:  {        
        defaults:{
            titleAlign:"center"
        },
        items: [{
            text: "主键",
            dataIndex: "id",
            hidden: true
        },/*{
            text: "机号",
            dataIndex: "termNo",
            hidden: true
        },*/{
            text: "房间名称",
            dataIndex: "roomName",
            flex:0.8,
            minWidth:80
        },{
            text: "序列号",
            dataIndex: "termSn",
            flex:1,
            minWidth:100,
        }, {
            text: "设备名称",
            dataIndex: "termName",
            flex:0.8,
            minWidth:80
        },/* {
            text: "设备类型",
            dataIndex: "termTypeID",
            columnType: "basecombobox", //列类型
            ddCode: "PTTERMTYPE", //字典代码
            width:80
        }, {
            text: "网关名称",
            dataIndex: "gatewayName",
            width:80
        }*/]
    },
    
    listeners: {
        beforeitemdblclick: function(grid, record, item, index, e, eOpts) {
            var mainlayout = grid.up('panel[xtype=skSystem.skAllot.deviceAllotLayout]');
            var deviceAllotGrid = mainlayout.down("panel[xtype=skSystem.skAllot.deviceAllotGrid]");

            var IsSelectStore = grid.getStore();
            IsSelectStore.removeAt(index);
            
            deviceAllotGrid.getStore().insert(0, record); //加入到新的grid
            return false;
        }
    }
    
});