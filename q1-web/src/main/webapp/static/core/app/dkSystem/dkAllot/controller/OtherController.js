Ext.define("core.dkSystem.dkAllot.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.dkSystem.dkAllot.otherController',
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
    	 "basetreegrid[xtype=dkSystem.dkAllot.roomInfoTree2] button[ref=gridRefresh]": {
            beforeclick: function(btn) {
             btn.up('basetreegrid').getStore().load();
             return false;
            }
        },
    	//弹出窗口的确认按钮
      "baseformwin[detCode=deviceallot_layout] button[ref=formSave]": {
        beforeclick: function(btn) {
          this.saveAllot(btn);
          return false;
        },
      },
      //快速搜索按按钮
      "basegrid[xtype=dkSystem.dkAllot.deviceAllotGrid] button[ref=gridFastSearchBtn]": {
          beforeclick: function (btn) {
            this.queryFastSearchForm(btn);
            return false;
            }
        },
      //快速搜索文本框回车事件
      "basegrid[xtype=dkSystem.dkAllot.deviceAllotGrid] field[funCode=girdFastSearchText]": {
        specialkey: function (field, e) {
          if (e.getKey() == e.ENTER) {
            this.queryFastSearchForm(field);   
            return false;            
          }
        }
      },
    },
    
  //确认绑定事件
    saveAllot:function(btn){
        var self = this; 
        var win = btn.up('window');
        var basegrid = win.baseGrid;
        var detCode = win.detCode;
        //找到详细布局视图
        var allotlayout = win.down("basepanel[funCode=" + detCode + "]");
        //var treegrid = allotlayout.down('basetreegrid[xtype=dkSystem.dkAllot.roominfotree2]');
        var baseGrid =allotlayout.down('panel[xtype=dkSystem.dkAllot.deviceSysGrid]');
        var deviceAllotGrid =allotlayout.down('panel[xtype=dkSystem.dkAllot.deviceAllotGrid]');
        var rows =  baseGrid.getStore().getCount();
        if (rows <= 0) {
          self.msgbox("选中的设备列表没有数据，请选择!");
          return;
        }
        var isSelectStore = baseGrid.getStore();
        var roomId = "";
        var id ="";
        for (var i = 0; i < rows; i++) {
            var record = isSelectStore.getAt(i);
            roomId+= record.get("roomId")+",";
            id += record.get("id") + "," ;
        }

        var loading = self.LoadMask(win);

        self.asyncAjax({
            url: comm.get('baseUrl') + "/PtTerm/doSetPtTerm",
            params: {
             roomId: roomId,
             id: id
           },              
         success: function (response) {
             var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));
             if (data.success) {
                self.msgbox("提交成功!");
                basegrid.getStore().load(); 
                baseGrid.getStore().removeAll();
                deviceAllotGrid.getStore().load();  
                loading.hide();
               // win.close();
             } else {
                loading.hide();
                self.Warning(data.obj);
               // win.close();
              }
          },
        failure: function(response) {                   
            Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);
            loading.hide();
          }
      });
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