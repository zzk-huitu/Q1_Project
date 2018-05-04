Ext.define("core.ykt.operatorBind.view.selectStationGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.operatorBind.selectStationGrid",
    dataUrl: comm.get('baseUrl') + "/PtWorkStation/selectList",
    model: 'com.yc.q1.model.base.pt.system.PtWorkStation',
    selModel: {
        type: "checkboxmodel",   
        headerWidth:30,    //设置这个值为50。 但columns中的defaults中设置宽度，会影响他
        //mode:'single',  //multi,simple,single；默认为多选multi
        checkOnly:true,    //如果值为true，则只用点击checkbox列才能选中此条记录
        //allowDeselect:true, //如果值true，并且mode值为单选（single）时，可以通过点击checkbox取消对其的选择
    },
    al: false,
    tbar: [],
    panelTopBar:{
        xtype:'toolbar',
        items: [ {
            xtype: 'tbtext',
            html: '待选工作站(选中后拖动添加)',
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
            name:'workStationName',
            funCode:'girdFastSearchText', 
            isNotForm:true,   //由于文本框重写了baseform下面的funcode值，所以使用这个属性，防止重写这里设定的fundcode值。
            emptyText: '请输入工作站名称'
        },{
            xtype: 'button',
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型
            ref: 'gridFastSearchBtn',   
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
              
                var basePanel = grid.up("panel[xtype=ykt.operatorBind.selectStationLayout]");
                var data = record.data;
                var selectStore = grid.getStore();
                var isSelectGrid;
                if(basePanel){
                    isSelectGrid = basePanel.down("panel[xtype=ykt.operatorBind.isSelectStationGrid]");
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
    //排序字段及模式定义
    defSort: [{
        property: 'orderIndex',
        direction: 'DESC'
    }],
    extParams: {
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
            text: "工作站名称",
            dataIndex: "workStationName",
            type: "string",
            flex:1,        
            minWidth:120,
        }, {
            text: "计算机名称",
            dataIndex: "computerName",
            type: "string",
            width:120,
        }/*, {
            text: "IP地址",
            dataIndex: "workStationIP",
            type: "string",
            width:160,
        }, {
            text: "MAC地址",
            dataIndex: "nic",
            type: "string",
            width:160,
        }, {
            text: "服务器端口",
            dataIndex: "msServerPort",
            type: "Integer",
            width:160,
        }, {
            text: "是否在线",
            dataIndex: "onLine",
            width:160,
            renderer:function(v){
                    return v==true?"<font color=red>是</font>":"<font color=green>否</font>"
                }
        }*/]
    }
});