Ext.define("core.baseset.dictionary.view.ItemDetailLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.baseset.dictionary.itemdetaillayout',
    funCode: "dicItem_detail",
    funData: {
        action: comm.get('baseUrl') + "/PtDataDict", //请求Action
        pkName: "jcId",
        modelName: "com.yc.q1.pojo.base.pt.DataDictTree", //实体全路径
        tableName: "" //表名       
    },
    items: [{
        xtype: "baseset.dictionary.itemform"
    }]
})