Ext.define("core.cardCenter.subsidyConfig.view.DetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.cardCenter.subsidyConfig.detailLayout',
	funCode: "subsidyConfig_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtSubsidyFillMoneyMain", //请求Action	
		pkName: "id",
		defaultObj: {
		}
	},
	
    /*关联此视图控制器*/
	controller: 'cardCenter.subsidyConfig.detailController',

    minWidth:1000,
    scrollable:'x',
    layout: 'border',
    items : [{  
        xtype: 'cardCenter.subsidyConfig.detailForm',
        height: 160,
        padding:0,  
        region : 'north', 
    },{ 
       region : 'center',  
       xtype: 'cardCenter.subsidyConfig.userGrid',
       flex : 1,
       margin:'5',
   }] 

})