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

    "baseform[xtype=consumemanager.consumediscount.detailform] ": {
      afterrender: function(grid) {
       /* var baseformtab =  grid.up("baseformtab[funCode=consumediscount_main]");
        var operType = baseformtab.operType;
        var consumeContainer =  grid.down("container[ref=consumeContainer]");
        if(operType=='edit'){
          var cardTypeName=consumeContainer.down('field[name=cardTypeName]');
          var mealName=consumeContainer.down('field[name=mealName]');
          cardTypeName.setReadOnly(true);
          mealName.setReadOnly(true);
        }
        return true;*/

      },

    },

},

});