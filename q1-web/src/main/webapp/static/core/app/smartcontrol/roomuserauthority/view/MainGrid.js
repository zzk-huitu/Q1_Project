Ext.define("core.smartcontrol.roomuserauthority.view.MainGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.smartcontrol.roomuserauthority.maingrid",
    dataUrl: comm.get("baseUrl") + "/MjUserRight/roomUserRightList", //数据获取地址
    model:"com.yc.q1.model.base.mj.MjUserRight", //对应的数据模型
    menuCode:"ROOM_ACCESS_CONTROL",
    al: false,
    //pageDisplayInfo:false,
    //工具栏操作按钮
    panelTopBar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '数据列表',
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
            funCode: 'girdFastSearchText',
            emptyText: '请输入姓名'
        },{
            xtype:'textfield',
            name:'termName',
            funCode: 'girdFastSearchText',
            emptyText: '请输入设备名称'
        },{
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型  
            iconCls: 'x-fa fa-search',  
        }]
    },
    defSort: [{
        property: 'termId',
        direction: 'ASC'
    }],
    panelButtomBar:{},
    //扩展参数
    extParams: {
        //orderSql :"order by USER_ID"
    },
    columns: {        
        defaults:{
            titleAlign:"center",
            align:'center'
        },
        items: [{
        xtype: "rownumberer",
        width: 50,
        text: '序号',
        align: 'center'
    }, {
        text: "主键",
        dataIndex: "id",
        hidden: true
    }, {
        text: "用户ID",
        dataIndex: "userId",
        hidden: true
    },{
        text: "用户姓名",
        dataIndex: "name",
        minWidth:100,
        flex:1,
    },{
        text: "房间名称",
        dataIndex: "roomName",
        minWidth:100,
        flex:1,
    },{
        text: "设备名称",
        dataIndex: "termName",
        minWidth:100,
        flex:1,
    },{
        text: "设备序列号",
        dataIndex: "termSn",
        minWidth:100,
        flex:1,
    }]
    }
});