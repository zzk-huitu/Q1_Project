Ext.define("core.ykt.operatorBind.view.UserGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.operatorBind.usergrid",
    dataUrl: comm.get('baseUrl') + "/PtCard/userList",
    model: 'com.yc.q1.model.base.pt.basic.PtTeacherBaseInfo',
    al:false,
    menuCode:"JOBINFO", //new：此表格与权限相关的菜单编码
    panelTopBar:{
     xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '人员列表',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800,
                lineHeight:'30px',
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
            xtype: 'button',            
            ref: 'gridFastSearchBtn',  
            funCode:'girdSearchBtn',    //指定此类按钮为girdSearchBtn类型 
            iconCls: 'x-fa fa-search',  
        }]
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
            text: "姓名",
            dataIndex: "name",
            flex:1,        
            minWidth:120,
        }, {
            text: "工号",
            dataIndex: "userNumb",
            width:160
        }, {
            text: "用户名",
            dataIndex: "userName",
            width:160
        }/*, {
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

 listeners: {
        beforeitemdblclick: function(grid, record, item, index, e, eOpts) {
            return false;
        },
        beforeitemmousedown: function(grid, record, item, index, e, eOpts) {
            return false;
        },
        beforeitemclick: function(gridview, record, item, index, e, eOpts) {          
            //var grid=grid.view;    
            var grid=gridview.grid;
            var selectRow=grid.getSelection(); 
            if(selectRow.length!=1){
                self.msgbox("只能选择一条数据操作!");
                return;
            }
            var temp=selectRow[0].data;
            var id = temp.id;                  

            var mainlayout = grid.up('panel[xtype=ykt.operatorBind.mainlayout]');
            var baseGrid = mainlayout.down('panel[xtype=ykt.operatorBind.maingrid]');
            var stores = baseGrid.getStore();
            var proxys = stores.getProxy();
            var filter = "[{'type':'string','comparison':'in','value':'"+id +"','field':'userId'}]";
            proxys.extraParams = {
                 filter: filter
            };
            stores.loadPage(1); //刷新
        }
    }

});