Ext.define("core.ykt.operatorBind.view.roomBindGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.operatorBind.roomBindGrid",
    dataUrl: comm.get('baseUrl') + "/PtUserRoomBind/list",
    model: 'com.yc.q1.model.base.pt.system.PtUserRoomBind',
    al:false,
    menuCode:"JOBINFO", //new：此表格与权限相关的菜单编码
    panelTopBar:{
     xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '绑定的房间',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800,
                lineHeight:'30px',
            }
        },{
            xtype: 'button',
            text: '绑定',
            ref: 'gridBindRoom',
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            iconCls: 'x-fa fa-plus-circle'
        },{
            xtype: 'button',
            text: '解绑',
            ref: 'gridReleaseRoom',
            disabled:true,
            funCode:'girdFuntionBtn',   //指定此类按钮为girdFuntionBtn类型，用于于右边的按钮进行功能区分
            disabled:true,
            iconCls: 'x-fa fa-minus-circle'
        }/*,'->',{
            xtype: 'tbtext', 
            html:'快速搜索：'
        },{
            xtype:'textfield',
            name:'name',
            funCode: 'girdFastSearchText',
            emptyText: '请输入姓名'
        },{
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型 
            iconCls: 'x-fa fa-search',  
        }*/]
    },
    panelButtomBar:null,
    
    //排序字段及模式定义
    defSort: [{
        property: 'updateTime',
        direction: 'DESC'
    },{
        property: 'createTime',
        direction: 'DESC'
    }],
    extParams: {},
    columns:  {        
        defaults:{
            titleAlign:"center",
            align:'center'
        },
        items: [{
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        },{
            text: "主键",
            dataIndex: "id",
            hidden: true
        }, {
            text: "房间名称",
            dataIndex: "roomName",
            flex:1,        
            minWidth:120,
        }, {
            text: "房间类型",
            dataIndex: "roomType",
            width:160,
            columnType: "basecombobox", //列类型
            ddCode: "FJLX" //字典代码
        }/*, {
            text: "用户名",
            dataIndex: "userName",
            width:160
        }, {
            text: "性别",
            dataIndex: "sex",
            columnType: "basecombobox", //列类型
            ddCode: "XBM", //字典代码
            width:160           
        }, {
            text: "岗位",
            dataIndex: "jobName",
            width:160
        }*/]
    } ,

});