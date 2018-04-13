Ext.define("core.coursemanage.specialcourseattend.view.UserAttendGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.coursemanage.specialcourseattend.userattendgrid",
    model: 'com.yc.q1.model.base.pt.system.PtUser',
    dataUrl: comm.get("baseUrl") + "/PtAttendUser/userAttendlist", //数据获取地址
    //title:"绑定费率的设备",
    al:false,
    tbar: [{
        xtype: 'button',
        text: '添加',
        ref: 'gridAdd',
        iconCls: 'x-fa fa-plus-circle'
    },{
        xtype: 'button',
        text: '删除',
        ref: 'gridDelete',
        iconCls: 'x-fa fa-minus-circle'
    }],
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
        defaults: {
            titleAlign: "center"
        },
        items: [{
            text: "主键",
            dataIndex: "id",
            hidden: true
        },{
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        },{
            flex:1,
            minWidth:100,
            text: "用户名",
            dataIndex: "userName"
        }, {
            flex:1,
            minWidth:100,
            text: "姓名",
            dataIndex: "name"
        },{
            flex:1,
            minWidth:100,
            text: "学号",
            dataIndex: "userNumb"
        },  {
            width:50,
            text: "性别",
            dataIndex: "sex",
            columnType: "basecombobox",
            ddCode: "XBM"
        }, {
            flex:1,
            minWidth:100,
            text: "部门",
            dataIndex: "deptName"
        }, {
            width:120,
            text: "岗位",
            dataIndex: "jobName"
        }]
    },
});