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