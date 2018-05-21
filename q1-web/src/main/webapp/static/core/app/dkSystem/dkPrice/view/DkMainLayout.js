Ext.define("core.dkSystem.dkPrice.view.DkMainLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.dkSystem.dkPrice.dkMainLayout',
    minWidth:1000,
    scrollable:'x',
    layout:'border',
    funCode:"rateBinding_layout",
    items: [{
        region: "west",
        xtype: "dkSystem.dkPrice.roomInfoTree",
        width: 250,
        margin:'5',
    }, {
        region: "center",
        xtype: "dkSystem.dkPrice.dkDataGrid",
        margin:'5',
    }, {
        region: "east",
        xtype: "dkSystem.dkPrice.dkDataGridTwo",
        width: 280,
        margin:'5' ,
    }]
})