Ext.define("core.smartcontrol.roombagrule.view.RoomDefineTree", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.smartcontrol.roombagrule.roomdefinetree",
    dataUrl: comm.get('baseUrl') + "/BaseRoomarea/list",
    model: "com.zd.school.build.define.model.BuildRoomAreaTree",
    al: true,
    expandFirst:true,
    sortableColumns:false,
    selModel: {
      
    },
    extParams: {
        whereSql: " and isDelete='0' ",
        orderSql: "",
        excludes:"checked"
    },
    tbar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '区域信息',
            style: {
                fontSize: '16px',
                color: '#C44444',
                fontWeight:800
            }
        }, '->',{
            xtype: 'button',
            text: '刷新',
            ref: 'gridRefresh',
            iconCls: 'x-fa fa-refresh'
        }]
    },
    columns:{
      defaults:{
              titleAlign:"center"
          },
      items:[{
              text: "区域名称",
              dataIndex: "text",
              xtype:'treecolumn',
              flex: 1,
              minWidth:150
          }, /*{
              text: "顺序号",
              dataIndex: "orderIndex",
              width:100
          },*/{
              text:"主键",
              dataIndex:'id',
              hidden:true
          }]
    },
    listeners: {

      itemclick: function(view, record, item, index, e) {  

          var panel = view.up("basepanel");
          //var filter = "[{'type':'string','comparison':'=','value':'" + record.get("id") + "','field':'areaId'}"; 
          var filter = "[{'type':'string','comparison':'=','value':'1','field':'roomType'}]";
        
          // 加载人员信息
          var basegrid = panel.down("basegrid[xtype=smartcontrol.roombagrule.roomgrid]");
          var store = basegrid.getStore();
          var proxy = store.getProxy();
          proxy.extraParams = {
            filter: filter,
            areaId:record.get("id"),
            areaType:record.get("areaType")
          };
          store.loadPage(1);
      }
  }

})