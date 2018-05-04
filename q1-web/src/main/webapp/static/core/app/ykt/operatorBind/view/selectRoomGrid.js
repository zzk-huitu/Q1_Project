Ext.define("core.ykt.operatorBind.view.selectRoomGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.operatorBind.selectRoomGrid",
    dataUrl: comm.get('baseUrl') + "/PtRoomInfo/selectList",
    model: "com.yc.q1.model.base.pt.build.PtRoomInfo",
    al:false,
    extParams: {
        whereSql: " and isDelete='0' ",
        orderSql: " order by roomName ",
        filter: '[{"type":"string","comparison":"=","value":"","field":"roomName"}]'
    },
    defSort: [{
        property: 'updateTime',
        direction: 'DESC'
    }/*{
        property: 'createTime',
        direction: 'DESC'
    }*/],
    tbar: [],
    panelTopBar:{
        xtype:'toolbar',
        items: [ {
            xtype: 'tbtext',
            html: '待选房间(选中后拖动添加)',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800
            }
        },'->',{
            xtype: 'tbtext', 
            html:'快速搜索：'
        },{
            xtype:'textfield',
            name:'roomName',
            funCode: 'girdFastSearchText',
            emptyText: '请输入房间名称'
        },{
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型 
            iconCls: 'x-fa fa-search',  
        }],
    },
    panelButtomBar:null,
    viewConfig: {
        plugins: {
            ptype: 'gridviewdragdrop',
            ddGroup: "DrapDropGroup"            //与下面的2行代码一样的效果
        },
        listeners: {
            drop: function(node, data, dropRec, dropPosition) {
            },
            beforeitemdblclick: function(grid, record, item, index, e, eOpts) {
              
                var basePanel = grid.up("panel[xtype=ykt.operatorBind.selectRoomLayout]");
                var data = record.data;
                var selectStore = grid.getStore();
                var isSelectGrid;
                if(basePanel){
                    isSelectGrid = basePanel.down("panel[xtype=ykt.operatorBind.isSelectRoomGrid]");
                    if(isSelectGrid.isVisible()==true){
                        var isSelectStore = isSelectGrid.getStore();
                        for (var i = 0; i < isSelectStore.getCount(); i++) {
                            if (data.id == isSelectStore.getAt(i).get('id')) {
                                Ext.Msg.alert("提示", data.roleName+"已存在!");
                                return ;
                            }
                        };
                      
                        selectStore.removeAt(index);
                        isSelectStore.insert(0, [record]);
                    }
                }
                
                return false;
            }
        }
    },
  
    columns:  { 
        defaults:{
            //flex:1,     //【若使用了 selType: "checkboxmodel"；则不要在这设定此属性了，否则多选框的宽度也会变大 】
            align:'center',
            titleAlign:"center"
        },
        items:[{
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        },{
            text: "主键",
            dataIndex: "id",
            hidden: true
        },{
            text : "房间名称",
            dataIndex : "roomName",
            type: "string",
            flex:1,        
            minWidth:120,
        }, {
            text : "房间编号",
            dataIndex : "roomCode",
            type: "string",
        }]
    }
});