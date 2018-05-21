Ext.define("core.public.allotSmartDevice.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.public.allotSmartDevice.mainController',
    mixins: {
    	  suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },
    init: function () {
    	
    	
    },
    /** 该视图内的组件事件注册 */
    control: {
    	 "basetreegrid[xtype=public.allotSmartDevice.roomInfoTree] button[ref=gridRefresh]": {
            beforeclick: function(btn) {
             btn.up('basetreegrid').getStore().load();
             return false;
            }
        },

      //快速搜索按按钮
      "basegrid[xtype=public.allotSmartDevice.allotDeviceGrid] button[ref=gridFastSearchBtn]": {
          click: function (btn) {
            this.queryFastSearchForm(btn);
            return false;
          }
      },
      //快速搜索文本框回车事件
      "basegrid[xtype=public.allotSmartDevice.allotDeviceGrid] field[funCode=girdFastSearchText]": {
          specialkey: function (field, e) {
            if (e.getKey() == e.ENTER) {
              this.queryFastSearchForm(field);   
              return false;            
            }
          }
      },
    },

    queryFastSearchForm:function(component){
        //得到组件                 
        var baseGrid = component.up("basegrid");
        var toolBar = component.up("toolbar");
        var filter = [];
        var filterStr = [];
        //可能存在多个文本框       
        var girdSearchTexts = toolBar.query("field[funCode=girdFastSearchText]");
        for (var i in girdSearchTexts) {
            var name = girdSearchTexts[i].getName();
            var value = girdSearchTexts[i].getValue();
            if(girdSearchTexts[i].dataType=='numeric'){
              if(value==null){
                 filter= [];
              }else{
                filter[i]={"type": "numeric", "value": value, "field": name, "comparison": ""};
              }
            
            }else{
                filterStr[i]={"type": "string", "value": value, "field": name, "comparison": ""};
            }
          }
       for(var j in filter){
          filterStr.push(filter[j]);
        }
        var store = baseGrid.getStore();
        var proxy = store.getProxy();
        if(filterStr.length==0)
          delete proxy.extraParams.filter;
        else 
          proxy.extraParams.filter = JSON.stringify(filterStr);
        store.loadPage(1);
    },
});