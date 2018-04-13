Ext.define("core.system.user.view.isSelectUserGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.system.user.isselectusergrid",
    dataUrl: comm.get('baseUrl') + "/PtUser/list",
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.system.PtUser", "checked").modelName,
    al: false,
    //排序字段及模式定义
    defSort: [{
        property: 'userName',
        direction: 'DESC'
    }, {
        property: 'state',
        direction: 'DESC'
    }],
    extParams: {
        whereSql: "",
        orderSql: ""
    },
    title: "<font color='#ffeb00'>已选人员(选中后向左拖动或双击删除)</font>",
    noPagging: true,
    tbar: null,
    tbar: [],
    panelTopBar:null,
    panelButtomBar:null,
    viewConfig: {
        plugins: {
            ptype: 'gridviewdragdrop',
            dragGroup: 'secondGridDDGroup',
            dropGroup: 'firstGridDDGroup'
        },
        listeners: {
            drop: function(node, data, dropRec, dropPosition) {
                var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('name') : ' on empty view';
                //Ext.example.msg("Drag from right to left", 'Dropped ' + data.records[0].get('name') + dropOn);  
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
            text: "主键",
            dataIndex: "id",
            hidden: true
        }, {
            text: "编号",
            dataIndex: "userNumb",
            flex:1, 
        }, {
            text: "姓名",
            dataIndex: "name",
            flex:1, 
        }, {
            text: "性别",
            dataIndex: "sex",
            columnType: "basecombobox",
            ddCode: "XBM",
            flex:1, 
        }, {
            text: "身份",
            dataIndex: "category",
            ddCode: "CATEGORY",
            columnType: "basecombobox",
            flex:1, 
        }, {
            text: "岗位",
            dataIndex: "jobName",
            flex:1, 

        }]
    }
});