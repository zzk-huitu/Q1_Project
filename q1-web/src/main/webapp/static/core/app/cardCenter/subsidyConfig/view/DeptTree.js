Ext.define("core.cardCenter.subsidyConfig.DeptTree", {
    extend: "core.base.view.BaseTreeGrid",
    alias: "widget.cardCenter.subsidyConfig.deptTree",
    dataUrl: comm.get('baseUrl') + "/PtDepartment/getUserRightDeptTree",
    model: " com.yc.q1.pojo.base.pt.DepartmentTree",
    al: true,
    expandFirst:true,
    sortableColumns:false,
    scrollable:true,
   // title: "部门列表",
    selModel: {
        selType: ""
    },
    extParams: {
        whereSql: " and isDelete='0' ",
        orderSql: " order by parentNode,orderIndex asc",
        excludes:"checked"      //排除这个字段，不显示复选框
    },
    tbar:{
        xtype:'toolbar',
        items: [{
            xtype: 'tbtext',
            html: '部门列表',
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


    columnLines:false,
    multiSelect: false,
    selModel: null,
    lines:true,
    useArrows: false,
    viewConfig: {
        stripeRows: false
    },



    listeners: {
        itemclick: function(grid, record, item, index, e) {
            var mainLayout = grid.up("panel[xtype=cardCenter.subsidyConfig.selectUserLayout]");        
            var userGrid = mainLayout.down("basegrid[xtype=cardCenter.subsidyConfig.selectUserGrid]");
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
         
              //获取快速搜索框的参数
            var girdSearchTexts = userGrid.query("field[funCode=girdFastSearchText]");
            var filter=new Array();
            if(girdSearchTexts[0].getValue()){
                filter.push({"type": "string", "value": girdSearchTexts[0].getValue(), "field": "name", "comparison": ""})
            }
            if(girdSearchTexts[1].getValue()){
                filter.push({"type": "string", "value": girdSearchTexts[1].getValue(), "field": "userNumb", "comparison": ""})
            }
            if(filter.length==0)
                filter=null;
            else 
                filter = JSON.stringify(filter);
            
            proxy.extraParams = {
                deptId: record.get("id"),
                filter:filter
            };
            store.loadPage(1);
         }
    }
});