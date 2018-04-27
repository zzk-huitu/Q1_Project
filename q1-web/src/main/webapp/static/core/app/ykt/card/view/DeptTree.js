Ext.define("core.baseset.studentmanager.view.DeptTree", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.baseset.studentmanager.depttree",
    dataUrl: comm.get('baseUrl') + "/PtDepartment/getUserRightDeptTree",
    model: factory.ModelFactory.getModelByName("com.yc.q1.pojo.base.pt.DepartmentTree", "checked").modelName,
    al: true,
    expandFirst:true,
    scrollable:true,
    title: "部门列表",
    selModel: {
        selType: ""
    },
    extParams: {
        whereSql: " and isDelete='0' ",
        orderSql: " order by parentNode,orderIndex asc",
        excludes:"checked"
    },

    columnLines:false,
    multiSelect: false,
    selModel: null,
    lines:true,
    useArrows: false,
    viewConfig: {
        stripeRows: false
    },

    tools: [{
        type: 'refresh',
        qtip: '刷新',
        handler: function(event, toolEl, header) {
            var tree = header.ownerCt
            tree.getStore().load();
            tree.getSelectionModel().deselectAll(true);
            var mainlayout = tree.up("basepanel[xtype=ykt.card.mainlayout]");
            var mianGrid = mainlayout.down("basegrid[xtype=ykt.card.usergrid]");
            var store = mianGrid.getStore();
            var proxy = store.getProxy();
            proxy.extraParams.deptId="";
         }
    }],

    listeners: {
        itemclick: function(grid, record, item, index, e) {
            var mainLayout = grid.up("panel[xtype=ykt.card.mainlayout]");
            var userGrid = mainLayout.down("panel[xtype=ykt.card.usergrid]");

            var store = userGrid.getStore();
            var proxy = store.getProxy();

            var deptId = record.get("id");
            var isRight = record.get("isRight"); 
            var deptType =record.get("deptType");

            if(deptId!='2851655E-3390-4B80-B00C-52C7CA62CB39'&&record.get("isRight")==0){
                proxy.extraParams = {
                    deptId: "0",
                };
                store.loadPage(1);
                return false;
            }
            
            Ext.apply(mainLayout.funData, {
                deptId: record.get("id"),
                isRight:record.get("isRight"),
                deptType:record.get("deptType")
            });
           
            var store = userGrid.getStore();
            var proxy = store.getProxy();
            proxy.extraParams = {
                deptId: record.get("id")
            }
            store.load();

        }
    }
});