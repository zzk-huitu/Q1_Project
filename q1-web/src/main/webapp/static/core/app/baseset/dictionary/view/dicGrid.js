Ext.define("core.baseset.dictionary.view.DicGrid", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.baseset.dictionary.dicgrid",
    dataUrl: comm.get('baseUrl') + "/PtDataDict/list",
    model: factory.ModelFactory.getModelByName("com.yc.q1.pojo.base.pt.DataDictTree", "checked").modelName,
    al: true,
    //selModel: null,     //不显示多选框
    extParams: {
        whereSql: " and isDelete='0' ",
        orderSql: ""
        //filter: "[{'type':'string','comparison':'=','value':'0','field':'isDelete'}]"
    },
    menuCode:"DICTIONARY",
    sortableColumns:false,
    title: "数据字典目录",

    tools: [{
        type: 'refresh',
        qtip: '刷新',
        handler: function(event, toolEl, header) {
            var tree = header.ownerCt
            tree.getStore().load();
            tree.getSelectionModel().deselectAll(true);          
         }
    }],

    tbar: [{
        xtype: 'button',
        text: '添加下级',
        ref: 'gridAdd',
        iconCls: 'x-fa fa-plus-circle',
        disabled:true
    }, {
        xtype: 'button',
        text: '添加同级',
        ref: 'gridAddBrother',
        iconCls: 'x-fa fa-plus-circle',
        disabled:true
    }, {
        xtype: 'button',
        text: '编辑',
        ref: 'gridEdit',
        iconCls: 'x-fa fa-pencil-square',
        disabled:true
    }, {
        xtype: 'button',
        text: '删除',
        ref: 'gridDel',
        iconCls: 'x-fa fa-minus-circle',
        disabled:true
    }/*, {
        xtype: 'button',
        text: '刷新',
        ref: 'gridRefresh',
        iconCls: 'x-fa fa-refresh'
    }*/],
    columns: [ {
        text: "字典名称",
        dataIndex: "text",
        xtype:'treecolumn',
        width:280
    }, {
        text: "字典编码",
        dataIndex: "dicCode",
        flex:1
    }, {
        width:100,
        text: "是否系统字典",
        dataIndex: "isSystem",
        renderer: function(value) {
            return value==true?"<font color=green>是</font>":"<font color=red>否</font>"
        }
    }, {
        text: "顺序号",
        dataIndex: "orderIndex",
        width:80,
    },{
        text:"主键",
        dataIndex:'id',
        hidden:true
    }]
})