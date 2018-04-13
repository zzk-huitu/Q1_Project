Ext.define("core.basedevice.ptirroomdevice.view.IrDeviceGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.basedevice.ptirroomdevice.irdevicegrid",
    dataUrl: comm.get('baseUrl') + "/PtIrDeviceBrand/list",
    model: "com.yc.q1.model.base.pt.device.PtIrDeviceBrand",

    extParams: {
        whereSql: " and isDelete='0' ",
        brandId:  "d9012b05-e85e-449d-82fc-4a424dee9b00",
        level : 1
    }, 
    panelTopBar:{
        xtype:'toolbar',
        items: ['->',{
            xtype:'textfield',
            name:'brandId',
            hidden:true,
            funCode:"girdFastSearchText"
        },{
            xtype:'textfield',
            name:'level',
            hidden:true,
            funCode:"girdFastSearchText"
        },{
            width:200,
            emptyText: '请选择品牌',
            xtype: "basetreefield",
            name:"brandName",
            rootId: "ROOT",
            funCode:'girdFastSearchText',
            configInfo: {
                multiSelect: false,
                fieldInfo: "brandName~brandId~level,text~id~level",
                whereSql: " and isDelete='0' ",
                orderSql: " order by parentNode,orderIndex asc",
                url:comm.get('baseUrl') + "/PtIrDeviceBrand/treelist"
            }
        },{
            xtype: 'button',
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型
            ref: 'gridFastSearchBtn',
            iconCls: 'x-fa fa-search'
        }],
    }, 


    panelButtomBar:null,
    
    //排序字段及模式定义
    defSort: [{
        property: 'createTime',
        direction: 'DESC'
    }],
 columns:  {        
        defaults:{
            titleAlign:"center"
        },
        items: [{
            xtype: "rownumberer",
            width: 50,
            text: '序号',
            align: 'center'
        },{
            text: "主键",
            dataIndex: "id",
            hidden: true
        },{
            text: "型号",
            dataIndex: "productModel",
            width: 150,
        }, {
            text: "品牌",
            dataIndex: "brandName",
            width: 150,
        }, {
            text: "备注",
            dataIndex: "notes",
            flex:1
        }]
    }    
});