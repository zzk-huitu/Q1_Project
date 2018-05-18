Ext.define("core.cardCenter.subsidyConfig.UserGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.cardCenter.subsidyConfig.userGrid",
    model: "com.yc.q1.model.storage.pt.PtSubsidyFillMoneyItem",
    dataUrl: comm.get('baseUrl') + "/PtSubsidyFillMoneyMain/getFillMoneyUsers",
    extParams: {
    },
    al:false,
    remoteSort:false,
    noPagging: true,
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '补助钱包人员设置（双击删除或批量删除）',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800
            }
        },'->',{
            xtype: 'button',
            text: '添加人员',
            ref: 'selectUser',
            iconCls: 'x-fa fa-plus-circle'
        }, {
            xtype: 'button',
            text: '批量删除',
            ref: 'gridDelete',
            iconCls: 'x-fa fa-minus-circle'
        }]
    },
    columns:{
        defaults:{
            titleAlign:"center"
        },
        items: [{
            text: "主键",
            dataIndex: "userId",
            hidden: true
        }, {
            width:120,
            text: "人员编号",
            dataIndex: "userNumb",
          
        }, {
            width:120,
            text: "姓名",
            dataIndex: "name",
        }, {
            width:120,
            text: "所在部门",
            dataIndex: "deptName",
          
        }, {
            width: 120,
            text: "卡号",
            dataIndex: "cardId",
          
        }, {
            width:120,
            text: "充值金额",
            dataIndex: "fillMoney",
            renderer: function(value) {
               return "￥"+value;
            }
        }, {
            width:120,
            text: "到账日期",
            dataIndex: "fillDate",
          
        }, {
            flex:1,
            minWidth:100,
            text: "说明",
            dataIndex: "notes",
          
        }]
    },
    listeners: {
        beforeitemdblclick: function(grid, record, item, index, e, eOpts) {
            var store=grid.getStore();
            store.removeAt(index); //将选中的移除
        }                        
    }, 
});