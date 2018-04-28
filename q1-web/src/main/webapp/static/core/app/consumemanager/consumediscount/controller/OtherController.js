Ext.define("core.consumemanager.consumediscount.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.consumemanager.consumediscount.othercontroller',
    mixins: {  
       suppleUtil: "core.util.SuppleUtil",
       messageUtil: "core.util.MessageUtil",
       formUtil: "core.util.FormUtil"
   },
    init: function () {
        /*执行一些初始化的代码*/
    },
    /** 该视图内的组件事件注册 */
    control: {    
         
                //费率设备删除
        "basegrid[xtype=consumemanager.consumediscount.termbinggrid] button[ref=gridDelete]": {
            beforeclick: function(btn) {
                this.deleteDiscountTermBing(btn);
                return false;
             },
         },   
    },
});