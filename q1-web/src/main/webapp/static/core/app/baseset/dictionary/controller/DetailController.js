Ext.define("core.baseset.dictionary.controller.DetailController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.baseset.dictionary.detailcontroller',
    mixins: {},
    init: function () {
        /*执行一些初始化的代码*/
    },
    /** 该视图内的组件事件注册 */
    control: {
      "baseform[xtype=baseset.dictionary.dicform]": {
        afterrender: function(grid) {
          var admin = comm.get("isAdmin");
          var isSystem =  grid.down("field[name=isSystem]");
          if(admin=='1'){
           isSystem.setReadOnly(false);
         }else{
           isSystem.setReadOnly(true);
         }


       }
     },
   }
});