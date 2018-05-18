Ext.define("core.cardCenter.subsidyConfig.SelectUserGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.cardCenter.subsidyConfig.selectUserGrid",
    al:false,
    frame: false,
    columnLines: false,
    dataUrl: comm.get("baseUrl") + "/PtUser/list", //数据获取地址
    model: "com.yc.q1.model.base.pt.system.PtUser",
    pageDisplayInfo:false,
    defSort: [{
        property: "deptName", //字段名
        direction: "DESC" //升降序
    },{
        property: "name", //字段名
        direction: "DESC" //升降序
    }],
    selModel: {
        type: "checkboxmodel",   
        headerWidth:30,    //设置这个值为50。 但columns中的defaults中设置宽度，会影响他
        //mode:'single',  //multi,simple,single；默认为多选multi
        checkOnly:true,    //如果值为true，则只用点击checkbox列才能选中此条记录
        //allowDeselect:true, //如果值true，并且mode值为单选（single）时，可以通过点击checkbox取消对其的选择
    },
    extParams: {
    },
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '待选用户',
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
            name:'name',
            funCode:'girdFastSearchText',
            emptyText: '请输入姓名'
        },{
            xtype:'textfield',
            name:'userNumb',
            funCode:"girdFastSearchText",
            emptyText: '请输入工号/学号'
        }, {
            xtype: 'button',
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型
            ref: 'gridFastSearchBtn',
            iconCls: 'x-fa fa-search'
        }]
    },
    /**
     * 高级查询面板
     */
    panelButtomBar: null,
    viewConfig: {
        plugins: {
            ptype: 'gridviewdragdrop',
            ddGroup: "DrapDropGroup"            //与下面的2行代码一样的效果
        },
        listeners: {
            drop: function(node, data, dropRec, dropPosition) {
            },
            beforeitemdblclick: function(grid, record, item, index, e, eOpts) {
              
                var basePanel = grid.up("panel[xtype=cardCenter.subsidyConfig.selectUserLayout]");
                var data = record.data;
                var selectStore = grid.getStore();
                var isSelectGrid;
                if(basePanel){
                    isSelectGrid = basePanel.down("panel[xtype=cardCenter.subsidyConfig.isSelectUserGrid]");
                    if(isSelectGrid.isVisible()==true){
                        var isSelectStore = isSelectGrid.getStore();
                        for (var i = 0; i < isSelectStore.getCount(); i++) {
                            if (data.id == isSelectStore.getAt(i).get('id')) {
                                Ext.Msg.alert("提示", data.name+"已存在!");
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

   
    columns: {
        defaults: {
            titleAlign: "center"
        },
        items: [{
            text: "主键",
            dataIndex: "id",
            hidden: true
        }/*,{
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        }*/,{
            flex:1,
            minWidth:90,
            text: "用户名",
            dataIndex: "userName"
        }, {
            flex:1,
            minWidth:90,
            text: "姓名",
            dataIndex: "name"
        },{
            flex:1,
            minWidth:90,
            text: "工号/学号",
            dataIndex: "userNumb"
        }, {
            flex:1,
            minWidth:90,
            text: "卡号",
            dataIndex: "upCardId"
        }]
    },
    emptyText: '<span style="width:100%;text-align:center;display: block;">暂无数据</span>'
});