Ext.define("core.public.consumetermmanager.view.ConsumeTermGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.public.consumetermmanager.consumetermgrid",
    model: '',
    dataUrl: comm.get('baseUrl') + "",
    //title:"绑定费率的设备",
    panelTopBar:null,
    panelButtomBar:null,
    remoteSort:false,
    //排序字段及模式定义
    defSort: [{
        property: 'orderIndex',
        direction: 'DESC'
    }],
    extParams: {
    },
   
    columns: { 
        defaults:{
            //flex:1,     //【若使用了 selType: "checkboxmodel"；则不要在这设定此属性了，否则多选框的宽度也会变大 】
            align:'center',
            titleAlign:"center"
        },
        items:[{
            text: "主键",
            dataIndex: "id",
            hidden: true
        }, {
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        },{
            text: "机号",
            dataIndex: "",
            flex:1,
        }, {
            text: "设备名称",
            dataIndex: "",
            flex:1,
        }]
    }
});