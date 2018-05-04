Ext.define("core.consumemanager.consumelimit.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.consumemanager.consumelimit.othercontroller',
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
      "baseform[xtype=consumemanager.consumelimit.detailform] ": {
        afterrender: function(grid) {
          var baseformtab =  grid.up("baseformtab[funCode=consumelimit_main]");
          var operType = baseformtab.operType;
          var consumeContainer =  grid.down("container[ref=consumeContainer]");
          if(operType=='edit'){
            var cardTypeId=consumeContainer.down('field[name=cardTypeId]');
            cardTypeId.setReadOnly(true);
          }
          return true;

        },

      },   
    },
});