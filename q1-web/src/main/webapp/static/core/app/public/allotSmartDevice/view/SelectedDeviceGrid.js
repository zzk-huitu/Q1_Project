Ext.define("core.public.allotSmartDevice.view.SelectedDeviceGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.public.allotSmartDevice.selectedDeviceGrid",
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
        type: "public.allotSmartDevice.isSelectStore"
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
        },{
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
        },]
    },
    
    listeners: {
        beforeitemdblclick: function(grid, record, item, index, e, eOpts) {
            var mainlayout = grid.up('panel[xtype=public.allotSmartDevice.MainLayout]');
            var deviceAllotGrid = mainlayout.down("panel[xtype=public.allotSmartDevice.allotDeviceGrid]");

            var IsSelectStore = grid.getStore();
            IsSelectStore.removeAt(index);
            
            deviceAllotGrid.getStore().insert(0, record); //加入到新的grid
            return false;
        }
    }
    
});