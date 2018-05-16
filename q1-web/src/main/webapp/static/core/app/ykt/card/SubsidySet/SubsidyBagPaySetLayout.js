 Ext.define("core.ykt.card.SubsidySet.SubsidyBagPaySetLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.ykt.card.SubsidySet.subsidybagpaysetlayout',
    funCode: "subsidybagpayset_main",
    funData: {
        action: comm.get('baseUrl') + "/PtSubsidyFillMoneyMain", //请求controller
        pkName: "id", //主键
        //默认的初始值设置
        defaultObj: {
        }
    },

    minWidth:1000,
    scrollable:'x',
    layout: 'border',
    items : [{  
        xtype: 'ykt.card.SubsidySet.paymoneyform',
        height: 160,
        padding:0,  
        region : 'north', 
    },{ 
       region : 'center',  
       xtype: 'ykt.card.SubsidySet.usergrid',
       flex : 1,
       margin:'5',
   }] 
})
