Ext.define("core.system.dept.store.DeptStore", {
    extend: "Ext.data.TreeStore",

    alias: 'store.system.dept.deptstore',

    defaultRootId: "0",
    model: factory.ModelFactory.getModelByName("com.yc.q1.pojo.base.pt.DepartmentTree", "checked").modelName,
    proxy: {
        type: "ajax",
        url: comm.get('baseUrl') + "/PtDepartment/treeList",
        extraParams: {
            excludes: 'checked',
            whereSql: "  and isDelete='0'",
            orderSql: ' ORDER BY nodelLevel,orderIndex'
        },
        reader: {
            type: "json"
        },
        writer: {
            type: "json"
        }
    },
    autoLoad: false
});