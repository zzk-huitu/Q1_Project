Ext.define("core.coursemanage.funcroomcourse.view.DetailForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.coursemanage.funcroomcourse.detailform",

    items: [{
        fieldLabel: "主键",
        name: "id",
        xtype: "textfield",
        hidden: true
    }]
});