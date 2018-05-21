Ext.define("core.dkSystem.dkPrice.view.DkDataGrid", {
	extend: "core.base.view.BaseGrid",
    alias: "widget.dkSystem.dkPrice.dkDataGrid",
    dataUrl: comm.get('baseUrl') + "/PtTerm/list",
    model: "com.yc.q1.model.base.pt.device.PtTerm",
    extParams: {
        whereSql: " where termTypeID=9"
    },
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '房间设备<font color=red>（往右拖动或者双击选择）</font>',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800,
                lineHeight:'32px',
            }
        }],
    }, 
    panelButtomBar:false,
    al:false,
    pageDisplayInfo:false,
    columns: [{
        text: "主键",
        dataIndex: "id",
        hidden: true
    }, {
        text: "设备名称",
        dataIndex: "termName",
        flex:1,
        minWidth:100
    }, {
        text: "序列号",
        dataIndex: "termSn",
        flex:1,
        minWidth:100
    }, {
        text: "设备类型",
        dataIndex: "termTypeId",
        columnType: "basecombobox", //列类型
        ddCode: "PTTERMTYPE", //字典代码
        width:80
    },{
        text: "当前费率",
        dataIndex: "price",
        width:80,
        renderer:function(value,matedate,record){
            if(record.get("termTypeId")==8){
              return record.get("skprice");
            }else if(record.get("termTypeId")==9){
              return record.get("dkprice");
          }

      }
    }],
    viewConfig: {
        plugins: {
            ptype: 'gridviewdragdrop',
            ddGroup: "DrapDropGroup"            //与下面的2行代码一样的效果
        },
        listeners: {
            drop: function (node, data, dropRec, dropPosition) {
            },
            beforeitemdblclick: function (grid, record, item, index, e, eOpts) {
                var data = record.data;
                selectStore = grid.getStore();
               
                var basePanel = grid.up("panel[xtype=dkSystem.dkPrice.skMainLayout]");
                var isSelectGrid;
                if (basePanel) {
                    isSelectGrid = basePanel.down("panel[xtype=dkSystem.dkPrice.skDataGridTwo]");
                    var isSelectStore = isSelectGrid.getStore();
                    for (var i = 0; i < isSelectStore.getCount(); i++) {
                        if (data.id == isSelectStore.getAt(i).get('id')) {
                            Ext.Msg.alert("提示", "该设备已存在选中列表，请勿重复操作");
                            return;
                        }
                    };
                    selectStore.removeAt(index);
                    isSelectStore.insert(0, [record]);
                }

                return false;
            }
        }
    },

});