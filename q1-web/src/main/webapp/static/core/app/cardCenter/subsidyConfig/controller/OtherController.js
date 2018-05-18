Ext.define("core.cardCenter.subsidyConfig.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.cardCenter.subsidyConfig.otherController',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
      },
    init: function () {
        /*执行一些初始化的代码*/
    },
    /** 该视图内的组件事件注册 */
    control: {
    	/*补助设置->添加/编辑-> 添加人员: 人员选择部门列表刷新树*/
    	"basetreegrid[xtype=cardCenter.subsidyConfig.deptTree] button[ref=gridRefresh]": {
    		beforeclick: function(btn) {
    			btn.up('basetreegrid').getStore().load();
    			var mainLayout = btn.up("panel[xtype=cardCenter.subsidyConfig.selectUserLayout]");        
    			var userGrid = mainLayout.down("panel[xtype=cardCenter.subsidyConfig.selectUserGrid]");
    			var store = userGrid.getStore();
    			var proxy = store.getProxy();
    			proxy.extraParams.deptId="";
    			return false;
    		}
    	},

    	/*补助设置->添加/编辑-> 添加人员: 保存选择人员事件*/
    	"baseformwin[funCode=selectUser_main] button[ref=formSave]": {
    		beforeclick: function(btn) {
    			this.doSaveUser_Win(btn);
    			return false;
    		}
    	},


      /*补助设置->添加/编辑: 保存按钮事件*/
      "baseformwin[funCode=subsidyConfig_detail] button[ref=formSave]": {
        beforeclick: function(btn) {
         this.doSaveAddAndEdit_Win(btn);
         return false;
       }
     },
    },



    doSaveUser_Win:function(btn){
      var self = this;
      var win = btn.up("baseformwin[funCode=selectUser_main]");
      var fillMoney = win.fillMoney;//充值金额
      var fillDate = win.fillDate;//到账日期
      var userGrid = win.grid;
      var userStore =  userGrid.getStore();
      var userCount = userStore.getCount();
      var newRecord = new Object();
      var basePanel = win.down("basepanel[xtype=cardCenter.subsidyConfig.selectUserLayout]");
      var isSelectUser = basePanel.down("panel[xtype=cardCenter.subsidyConfig.isSelectUserGrid]");

      var ids =new Array();
      var store = isSelectUser.getStore();
      if(store.getCount()<=0){
        self.msgbox("请选择数据！");
        return;

      }
      for(var j = 0; j <userCount; j++){
       var userRecode = userStore.getAt(j);
       var userId = userRecode.get("id");
       if(ids.indexOf(userId)==-1)
         ids.push(userId);
     }

     for(var i = 0; i < store.getCount(); i++){
        var record = store.getAt(i);
        var pkValue = record.get("id");
        if(ids.indexOf(pkValue)==-1){
          newRecord = new Object();
          newRecord["userId"] = record.get("id");
          newRecord["name"] = record.get("name");
          newRecord["userNumb"] = record.get("userNumb");
          newRecord["cardId"] = record.get("cardId");
          newRecord["deptName"] = record.get("deptName");
          newRecord["fillMoney"] = fillMoney;
          newRecord["fillDate"] = fillDate;
          userStore.insert(0, newRecord);
        }
       
      } 

     win.close();

   },
    doSaveAddAndEdit_Win:function(btn){
      var self = this;
      var win = btn.up("baseformwin");
      var basePanel = win.down("basepanel[funCode='"+win.funCode+"']");
      var payMoneyForm = basePanel.down("form[xtype=cardCenter.subsidyConfig.detailForm]");

      var formObj = payMoneyForm.getForm();
      var funData = basePanel.funData;
      var pkName = funData.pkName;
      var pkField = formObj.findField(pkName);
      var params = self.getFormValue(formObj);

      var baseGrid = basePanel.down("basegrid[xtype=cardCenter.subsidyConfig.userGrid]");
      var store = baseGrid.getStore();
      var ids = new Array();
      if(store.getCount()<=0){
        self.Warning("请添加需要设置补助钱包充值的人员！");
        return;
       }
      for(var j = 0; j <store.getCount(); j++){
       var userRecode = store.getAt(j);
       var userId = userRecode.get("userId");
       ids.push(userId);
      }
      params["userIds"] =ids.join(',');
      //判断当前是保存还是修改操作
        var act = Ext.isEmpty(pkField.getValue()) ? "doAdd" : "doUpdate";
        if (formObj.isValid()) {

            var loading = new Ext.LoadMask(win, {
                msg: '正在提交，请稍等...',
                removeMask: true// 完成后移除
            });
            loading.show();

            self.asyncAjax({
                url: funData.action + "/" + act,
                params: params,
                //回调代码必须写在里面
                success: function (response) {
                    var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                    if (data.success) {

                        self.msgbox("提交成功!");
                       
                        var grid = win.grid; //窗体是否有grid参数
                        if (!Ext.isEmpty(grid)) {
                            var store = grid.getStore();
                            act=="doAdd"?store.loadPage(1):store.load();
                        }

                        loading.hide();
                        win.close();
                     
                    } else {
                        self.Error(data.obj);
                        loading.hide();
                    }
                },
                failure: function(response) {                   
                    Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);
                    loading.hide();
                }
            });

        } else {

            var errors = ["前台验证失败，错误信息："];
            formObj.getFields().each(function (f) {
                if (!f.isValid()) {
                    errors.push("<font color=red>" + f.fieldLabel + "</font>:" + f.getErrors().join(","));
                }
            });
            self.msgbox(errors.join("<br/>"));
        }

    },
});