Ext.define("core.ykt.card.view.UserGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.ykt.card.usergrid",
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
            dataIndex: "userNumb"
        }, {
            text: "用户名",
            dataIndex: "userName"
        }, {
            text: "性别",
            dataIndex: "sex",
            columnType: "basecombobox", //列类型
            ddCode: "XBM" //字典代码           
        }, {
            text: "岗位",
            dataIndex: "jobName",
        }, {
            xtype: 'actiontextcolumn',
            text: "操作",
            align: 'center',
            width: 250,
            fixed: true,
            items: [{
                text:'编辑',  
                style:'font-size:12px;', 
                tooltip: '编辑',
                ref: 'gridEdit',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="JOBINFO";     // 此菜单的前缀
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
            }, {
                text:'详细',  
                style:'font-size:12px;', 
                tooltip: '详细',
                ref: 'gridDetail',
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('detailClick_Tab', {
                        view: view.grid,
                        record: rec
                    });
                }
            }, {
                text:'删除',  
                style:'font-size:12px;', 
                tooltip: '删除',
                ref: 'gridDelete',
                getClass :function(v,metadata,record,rowIndex,colIndex,store){                            
                    if(comm.get("isAdmin")!="1"){
                        var menuCode="JOBINFO";     // 此菜单的前缀
                        var userBtn=comm.get("userBtn");   
                        if(userBtn.indexOf(menuCode+"_gridDelete")==-1){
                            return 'x-hidden-display';
                        }
                    }
                    return null; 
                },  
                handler: function(view, rowIndex, colIndex, item) {
                    var rec = view.getStore().getAt(rowIndex);
                    this.fireEvent('deleteClick', {
                        view: view.grid,
                        record: rec
                    });
                }
            }]
        }]
    }    
});