Ext.define("core.consumemanager.consumeterm.view.batchAddLayout", {
	extend: "core.base.view.BasePanel",
	alias: "widget.consumemanager.consumeterm.batchaddlayout",
	funCode: "consumeterm_batchadd",
	
	  /*关联此视图控制器*/
    controller: 'consumemanager.consumeterm.detailcontroller',
     /*设置最小宽度，并且自动滚动*/
    minWidth:750,
    scrollable:true,
    layout: 'card',
    dockedItems: [{
        xtype: 'toolbar',
        dock: 'bottom',
        items: [{
            xtype: 'button',
            text: '上一步',
            ref: 'btnBack',
            id: 'move-prev',
            iconCls: 'x-fa fa-minus-circle',
            disabled: true
        },{
            xtype: 'button',
            text: '下一步',
            ref: 'btnNext',
            id: 'move-next',
            iconCls: 'x-fa fa-minus-circle',
        },{
            xtype: 'button',
            text: '取消',
            ref: 'btnOut',
            iconCls: 'x-fa fa-minus-circle',
            handler: function(btn) {
                 btn.up('window').close();
            }
          }]
    }],
   
	items: [{
		//title:"批量添加设备简介",
		xtype: "consumemanager.consumeterm.batchaddterm.batchaddintroform"
	},{
        xtype: "consumemanager.consumeterm.detailform",
    },{
        xtype: "consumemanager.consumeterm.consumebaseparamform",
    }]
});