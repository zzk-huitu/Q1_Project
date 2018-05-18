Ext.define("core.skSystem.skPrice.view.SkMainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.skSystem.skPrice.skMainLayout',
    minWidth:1000,
    scrollable:'x',
    layout:'border',
    funCode:"rateBinding_layout",
    items: [{
        region: "west",
        xtype: "skSystem.skPrice.roomInfoTree",
        width: 250,
        margin:'5',
    }, {
        region: "center",
        xtype: "skSystem.skPrice.skDataGrid",
        margin:'5',
    }, {
        region: "east",
        xtype: "skSystem.skPrice.skDataGridTwo",
        width: 280,
        margin:'5' ,
    }]
})