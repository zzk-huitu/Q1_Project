/**
    ( *非必须，只要需要使用时，才创建他 )
    此视图控制器，用于注册window之类的组件的事件，该类组件不属于 mainLayout和detailLayout范围内。
    但需要在创建window中，使用controller属性来指定此视图控制器，才可生效
*/
Ext.define("core.ykt.card.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.ykt.card.othercontroller',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },
    init: function() {
    },
    /** 该视图内的组件事件注册 */
    control: {
        /* 错扣补款->补款操作： 保存*/
        "baseformwin[funCode=filloperate_detail] button[ref=formSave]": {
          beforeclick: function(btn) {
            this.doSaveFillOpe_Win(btn);
           return false;
         }
       },
      /* 补助设置->补助钱包充值设置->添加/编辑: 添加人员  */
        "basegrid[xtype=ykt.card.SubsidySet.usergrid] button[ref=selectUser]":{
          beforeclick: function(btn) {
            this.doSelectUser(btn);
            return false;
          } 
       },
      /* 补助设置->补助钱包充值设置->添加/编辑: 批量删除  */
       "basegrid[xtype=ykt.card.SubsidySet.usergrid] button[ref=gridDelete]":{
          beforeclick: function(btn) {
            this.doDeleteUser(btn);
            return false;
        } 
       },
        /*补助设置->补助钱包充值设置->添加/编辑-> 添加人员: 人员选择部门列表刷新树*/
        "basetreegrid[xtype=ykt.card.SubsidySet.depttree] button[ref=gridRefresh]": {
          beforeclick: function(btn) {
            btn.up('basetreegrid').getStore().load();
            var mainLayout = btn.up("panel[xtype=ykt.card.SubsidySet.selectuserlayout]");        
            var userGrid = mainLayout.down("panel[xtype=ykt.card.selectusergrid]");
            var store = userGrid.getStore();
            var proxy = store.getProxy();
            proxy.extraParams.deptId="";
            return false;
          }
        },

         /*补助设置->补助钱包充值设置->添加/编辑-> 添加人员: 保存选择人员事件*/
        "baseformwin[funCode=selectUser_main] button[ref=formSave]": {
          beforeclick: function(btn) {
           this.doSaveUser_Win(btn);
           return false;
         }
       },

      /*补助设置->补助钱包充值设置->添加/编辑: 保存按钮事件*/
        "baseformwin[funCode=subsidybagpayset_main] button[ref=formSave]": {
          beforeclick: function(btn) {
           this.doSaveAddAndEdit_Win(btn);
           return false;
          }
        },

          /*充值/退款-> 用户操作 ：保存   */
        "baseformwin[funCode=accountOperator_main] button[ref=formSave]": {
          beforeclick: function(btn) {
           this.doSaveAccount_Win(btn);
           return false;
          }
        },
       /*充值/退款-> 用户操作 ：form表单界面渲染*/
     "baseform[xtype=ykt.card.accountoperatorform]":{
          afterrender: function(grid) {
            var win = grid.up("baseformwin[funCode=accountOperator_main]");
            var cmd = win.cmd;
            //退款
            var refundContainer = grid.down("container[ref=refundMoneyContainer]");
            var qiutFeeContainer = grid.down("container[ref=qiutFeeContainer]");
            //充值
            var rechargeContainer = grid.down("container[ref=rechargeMoneyContainer]");
            var putFeeContainer = grid.down("container[ref=putFeeContainer]");

            if(cmd=="recharge"){
              refundContainer.setVisible(false);
              qiutFeeContainer.setVisible(false);
              rechargeContainer.setVisible(true);
              putFeeContainer.setVisible(true);

            }else{
              refundContainer.setVisible(true);
              qiutFeeContainer.setVisible(true);
              rechargeContainer.setVisible(false);
              putFeeContainer.setVisible(false);

            }

            return false;
          } 
        },
      },

  doSaveFillOpe_Win:function(btn){
      var self = this;
    //  debugger;
      var win = btn.up("baseformwin[funCode=filloperate_detail]");
      var consumeDetailGrid = win.down("basegrid[xtype=ykt.card.consumedetailgrid]");
      var consumeDetailForm = win.down("form[xtype=ykt.card.consumedetailform]");
      var formObj = consumeDetailForm.getForm();
      var params = self.getFormValue(formObj);
      if (formObj.isValid()) {
            var loading = new Ext.LoadMask(win, {
                msg: '正在提交，请稍等...',
                removeMask: true// 完成后移除
            });
            loading.show();
            self.asyncAjax({
                url: comm.get('baseUrl') + "/PtCard/doAddFillOperate" ,
                params: params,
                //回调代码必须写在里面
                success: function (response) {
                      var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                    if (data.success) {

                        self.msgbox("提交成功!");
                       
                        var grid = win.grid; //窗体是否有grid参数
                        if (!Ext.isEmpty(grid)) {
                            var store = grid.getStore();
                            store.loadPage(1)
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

    doSelectUser :function(btn){
        var self=this;
        var basepanel = btn.up('basepanel[xtype=ykt.card.SubsidySet.subsidybagpaysetlayout]');
        var payMoneyForm = basepanel.down("form[xtype=ykt.card.SubsidySet.paymoneyform]");
        var fillMoneyValue = payMoneyForm.query("field[name=fillMoney]")[0].getValue();
        if(!fillMoneyValue||fillMoneyValue<0.1||fillMoneyValue>5000){
          self.Warning("[充值金额]必须介于0.1元至5000.00元之间！");
          return ;
        }
        var fillDateValue = payMoneyForm.query("field[name=fillDate]")[0].getValue();
        var baseGrid = basepanel.down('basegrid[xtype=ykt.card.SubsidySet.usergrid]')
        var funcPanel = "ykt.card.SubsidySet.selectuserlayout";
        var funcTitle = "人员选择";
        var otherController = 'ykt.card.othercontroller';
        var win = Ext.create('core.base.view.BaseFormWin', {
          operType: "add",
          title: funcTitle,
          width: 1050,
          height: 500,
          controller: otherController,
          grid: baseGrid,
          funCode: "selectUser_main",
          funcPanel:funcPanel,
            fillMoney:fillMoneyValue,//充值金额
            fillDate:fillDateValue,//到账日期
            items: [{
              xtype: funcPanel
            }]
          });
        win.show();
      },

    doDeleteUser :function(btn){
        var self=this;
        var userGrid = btn.up('basegrid[xtype=ykt.card.SubsidySet.usergrid]');
        var rows = userGrid.getSelectionModel().getSelection();
        if (rows <= 0) {
          self.msgbox("请选择要删除的数据!");
          return;
        }
        userGrid.getStore().remove(rows); 
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
      var basePanel = win.down("basepanel[xtype=ykt.card.SubsidySet.selectuserlayout]");
      var isSelectUser = basePanel.down("panel[xtype=ykt.card.isselectusergrid]");

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
          newRecord["id"] = record.get("id");
          newRecord["name"] = record.get("name");
          newRecord["userNumb"] = record.get("userNumb");
          newRecord["upCardId"] = record.get("upCardId");
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
      var win = btn.up("baseformwin[funCode=subsidybagpayset_main]");
      var basePanel = win.down("basepanel[funCode=subsidybagpayset_main]");
      var payMoneyForm = basePanel.down("form[xtype=ykt.card.SubsidySet.paymoneyform]");

      var formObj = payMoneyForm.getForm();
      var funData = basePanel.funData;
      var pkName = funData.pkName;
      var pkField = formObj.findField(pkName);
      var params = self.getFormValue(formObj);

      var baseGrid = basePanel.down("basegrid[xtype=ykt.card.SubsidySet.usergrid]");
      var store = baseGrid.getStore();
      var ids = new Array();
      if(store.getCount()<=0){
        self.Warning("请添加需要设置补助钱包充值的人员！");
        return;
       }
      for(var j = 0; j <store.getCount(); j++){
       var userRecode = store.getAt(j);
       var userId = userRecode.get("id");
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
 
   doSaveAccount_Win:function(btn){
   
      var self = this;
      var win = btn.up("baseformwin[funCode=accountOperator_main]");
      var form = win.down("form[xtype=ykt.card.accountoperatorform]");
      var sign = win.sign;
      var formObj = form.getForm();
      var params = self.getFormValue(formObj);
      params['sign'] = sign;
      if (formObj.isValid()) {
               var loading = new Ext.LoadMask(win, {
                msg: '正在提交，请稍等...',
                removeMask: true// 完成后移除
            });
            loading.show();
            self.asyncAjax({
                url: comm.get('baseUrl') + "/PtCard/doAddAcountOperator" ,
                params: params,
                //回调代码必须写在里面
                success: function (response) {
                    var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                    if (data.success) {

                        self.msgbox("提交成功!");
                       
                        var grid = win.grid; //窗体是否有grid参数
                        if (!Ext.isEmpty(grid)) {
                            var store = grid.getStore();
                            store.loadPage(1)
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