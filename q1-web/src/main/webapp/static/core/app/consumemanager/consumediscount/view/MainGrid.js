Ext.define("core.consumemanager.consumediscount.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.consumemanager.consumediscount.maingrid",
    dataUrl: comm.get("baseUrl") + "/XfRateSet/list", //数据获取地址
    model: "com.yc.q1.model.base.xf.XfRateSet", //对应的数据模型
    menuCode:"",

    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'button',
            text: '添加',
            ref: 'gridAdd_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-pencil-square'
        },{
            xtype: 'button',
            text: '编辑',
            ref: 'gridEdit_Tab',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true, 
            iconCls: 'x-fa fa-pencil-square'
        },{
            xtype: 'button',
            text: '折扣设备绑定',
            ref: 'discountTermBing',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle',
            disabled:false,
        }]
   },
    defSort: [{
        property: 'updateTime',
        direction: 'DESC'
    }],
    panelButtomBar:null,
    extParams: {
        
    },
    columns:{
         defaults:{
            titleAlign:"center",
            align:'center',
        },
        items: [{
            xtype : "rownumberer",
            width : 50,
            text : '序号',
            align : 'center'
        },{
            text: "主键",
            dataIndex: "id",
            hidden: true
        }, {
            text : "卡类名称",
            dataIndex : "cardTypeName",
            flex:1
        }, {
            text : "餐类",
            dataIndex : "mealName",
            flex:1
        }, {
            text : "折扣状态",
            dataIndex : "rateStatus",
            flex:1,
            renderer: function(value) {
                return (value == true) ? '<font color=green>启用</font>' : '<font color=red>禁用</font>';
            }
        },{
            text : "按日折扣",
            dataIndex : "rateType",        
            flex:1,
             renderer: function(value) {
                return (value == true) ? '<font color=green>启用</font>' : '<font color=red>禁用</font>';
            }
        },{
            text : "第一次费率",
            dataIndex : "ratePrice1",     
            flex:1
        },{
            text : "第二次费率",
            dataIndex : "ratePrice2",
            flex:1
        },{
            text : "第三次费率",
            dataIndex : "ratePrice3",
            flex:1
        },{
            text : "备注",
            dataIndex : "notes",
            flex:1
        },{
           xtype: 'actiontextcolumn',
           text: "操作",
           align: 'center',
           width: 100,
           fixed: true,
           items: [{
            text: '编辑',
            style: 'font-size:12px;',
            tooltip: '编辑消费折扣参数设置',
            getClass: function(view, metadata, record, rowIndex, colIndex, store) {
                if(comm.get("isAdmin")!="1"){
                        var menuCode="";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");                 
                        if(userBtn.indexOf(menuCode+"_gridEdit_Tab")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null;
                },
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('editClick_Tab', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }
});