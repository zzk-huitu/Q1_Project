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
       /*补款操作*/
        "basegrid[xtype=ykt.card.errorfillmoneygrid] button[ref=errorFillMoneyOperate]":{
              beforeclick: function(btn) {
                    this.dofillOperate(btn);
                    return false;
                }

        },
        "basegrid[xtype=ykt.card.errorfillmoneygrid] actioncolumn":{
            fillOperating_Win:function(data){
                this.dofillOperate(null,data.view,data.record);
                return false;
            },
        },
         //补款操作 点击单元格，将数据选入
        "basegrid[xtype=ykt.card.consumedetailgrid]":{
            cellclick: function(table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                this.doSelectConsume(table,cellIndex,record);       
            }

        },
        
      /*补助设置 添加 编辑 删除 审核 快速搜索框查询*/
        "basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridAdd]":{
          beforeclick: function(btn) {
            this.doAddAndEdit_Win(btn,"add");
            return false;
          } 
        },
        "basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridEdit]":{
          beforeclick: function(btn) {
            this.doAddAndEdit_Win(btn,"edit");
            return false;
          } 
        },
        "basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridDelete]":{
          beforeclick: function(btn) {
            this.doDeleteSubsidy(btn);
            return false;
          } 
        },
        "basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridAudit]":{
          beforeclick: function(btn) {
            this.doAudit(btn);
            return false;
          } 
        },
        /*补助设置快速搜索框*/
        "basegrid[xtype=ykt.card.subsidysetgrid] field[funCode=girdFastSearchText]":{
         specialkey: function (field, e) {
          if (e.getKey() == e.ENTER) {
            this.doFastSearch(field,"subsidyset");                
          }
        }
      },
        "basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridFastSearchBtn]":{
          beforeclick: function(btn) {
            this.doFastSearch(btn,"subsidyset");
            return false;
          } 
        },


        /* 补助设置 添加 编辑  选择选中人员  */
        "basegrid[xtype=ykt.card.SubsidySet.usergrid] button[ref=selectUser]":{
          beforeclick: function(btn) {
            this.doSelectUser(btn);
            return false;
          } 
       },
      /* 补助设置 批量删除  */
       "basegrid[xtype=ykt.card.SubsidySet.usergrid] button[ref=gridDelete]":{
          beforeclick: function(btn) {
            this.doDeleteUser(btn);
            return false;
        } 
       },
        /*补助设置 人员选择界面部门列表刷新树*/
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

         /*补助设置 保存选择人员事件*/
        "baseformwin[funCode=selectUser_main] button[ref=formSave]": {
          beforeclick: function(btn) {
           this.doSave_Win(btn);
           return false;
          }
        },

      /*补助设置 新增 编辑 保存*/
        "baseformwin[funCode=subsidybagpayset_main] button[ref=formSave]": {
          beforeclick: function(btn) {
           this.doSaveAddAndEdit_Win(btn);
           return false;
          }
        },
      // 补助设置 隐藏按钮事件
      "basegrid[xtype=ykt.card.subsidysetgrid]": {
      /*  afterrender : function(grid) {
          this.hideFuncBtn(grid);
        },*/
        beforeitemclick: function(grid, record, item, index, e, eOpts) {
          this.disabledFuncBtn(grid);
        }
      },

         /* 账户操作充值 */
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=userRecharge]":{
          beforeclick: function(btn) {
            this.doAccountOperator(btn,"recharge");
            return false;
          } 
       },
       /* 账户操作 退款*/
       "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=userRefund]":{
          beforeclick: function(btn) {
            this.doAccountOperator(btn,"refund");
            return false;
        } 
       },
         /* 账户操作操作列 充值 退款 */
       "basegrid[xtype=ykt.card.accountoperatorgrid] actioncolumn":{
        userRecharge_Win: function(data){
          this.doAccountOperator(null,"recharge",data.view,data.record);
          return false;

        },
         userRefund_Win: function(data){
          this.doAccountOperator(null,"refund",data.view,data.record);
          return false;

        },
      },
         /*账户操作快速搜索框*/
        "basegrid[xtype=ykt.card.accountoperatorgrid] field[funCode=girdFastSearchText]":{
         specialkey: function (field, e) {
          if (e.getKey() == e.ENTER) {
            this.doFastSearch(field,"accountoperator");                
          }
        }
      },
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=gridFastSearchBtn]":{
          beforeclick: function(btn) {
            this.doFastSearch(btn,"accountoperator");
            return false;
          } 
        },
       /*对充值 退款form表单界面渲染*/
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

    dofillOperate :function(btn,grid,recode){
        var self=this;
        //得到组件
       
        var recodeDate;
        var baseGrid = grid;
        if(!baseGrid){
            baseGrid =  btn.up("basegrid");
            var recodes = baseGrid.getSelectionModel().getSelection();
            if(recodes.length!=1){
                self.msgbox("请选择一条数据！");
                return ;

            }
         recodeDate = recodes[0].getData();

        }else{
          recodeDate = recode.getData();
        }
       
        var iconCls= 'x-fa fa-plus-circle';
        var width = 800;
        var height = 600;

        var win = Ext.create('core.base.view.BaseFormWin', {
            iconCls:iconCls,
            title: "补款操作",
            operType: "add",
            width: width,
            height: height,
            tittle:"补款操作",
            controller: "ykt.card.othercontroller",
            grid: baseGrid,
            funCode: "filloperate_detail",
            recodeDate:recodeDate,//用户id
            items: [{
                xtype: "ykt.card.filloperatelayout"
            }]
        });
        win.show();
        var detPanel = win.down("basepanel[funCode='"+win.funCode+"']");
        var baseGrid = detPanel.down("basegrid[xtype=ykt.card.consumedetailgrid]");
        var store = baseGrid.getStore();
        var proxy = store.getProxy();
        proxy.extraParams.userId = recodeDate.userId;
        store.load();

      },

    doAddAndEdit_Win :function(btn,cmd){
        var self=this;
        //得到组件
        var baseGrid = btn.up("basegrid");

        var iconCls= 'x-fa fa-plus-circle';
        //处理特殊默认值
        if(cmd!="add"){
            var rescords = baseGrid.getSelectionModel().getSelection();
            if (rescords.length != 1) {
                self.msgbox("请选择数据");
                return;
            }
            insertObj = rescords[0].getData();

            if(cmd=="edit")
                 iconCls= 'x-fa fa-pencil-square';
            else 
                 iconCls= 'x-fa fa-file-text';
        }
        var width = 1000;
        var height = 600;

        var win = Ext.create('core.base.view.BaseFormWin', {
            iconCls:iconCls,
            operType: "add",
            width: width,
            height: height,
            controller: "ykt.card.othercontroller",
            grid: baseGrid,
            funCode: "subsidybagpayset_main",
            items: [{
                xtype: "ykt.card.SubsidySet.subsidybagpaysetlayout"
            }]
        });
        win.show();
        if(cmd=="edit"){
          var detPanel = win.down("basepanel[funCode=subsidybagpayset_main]");
          var objDetForm = detPanel.down("baseform[xtype=ykt.card.SubsidySet.paymoneyform]");
          var formDeptObj = objDetForm.getForm();
          self.setFormValue(formDeptObj, insertObj);
        
          var baseGrid = detPanel.down("basegrid[xtype=ykt.card.SubsidySet.usergrid]");
          var store =  baseGrid.getStore();
          var proxy = store.getProxy();
          var mainId = objDetForm.query("field[name=id]")[0].getValue();
          var fillMoney = objDetForm.query("field[name=fillMoney]")[0].getValue();
          var fillDate = objDetForm.query("field[name=fillDate]")[0].getValue();
          var notes = objDetForm.query("field[name=notes]")[0].getValue();
          proxy.extraParams={
           mainId:mainId
          };
          store.load(function(records, operation, success) {
              for(var i=0;i<records.length;i++){
                var rec=records[i];
                rec.data["fillMoney"]=fillMoney;
                rec.data["fillDate"]=fillDate;
                rec.data["notes"]=notes;
                rec.commit();
              }
          });
         // store.loadPage(1);
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

    doAccountOperator :function(btn,cmd,grid,record){
      var self=this;
      var baseGrid = grid;
      var recordData;
      if(!baseGrid){
        baseGrid = btn.up('basegrid');
        var selectRecords = baseGrid.getSelectionModel().getSelection();
        if(selectRecords.length!=1){
           // self.msgbox("请选择一个用户！");
           // return;

         }
        //recordData = selectRecords[0].getData();
      }else{
       recordData = record.getData();

     }

      var funcPanel = "ykt.card.accountoperatorform";
      var funcTitle = "充值";// 某某充值/退款
      var otherController = 'ykt.card.othercontroller';
      if(cmd=="refund"){
       funcTitle = "退款";
      }
      var win = Ext.create('core.base.view.BaseFormWin', {
            operType: "add",
            title: funcTitle,
            width: 600,
            height: 300,
            controller: otherController,
            grid: baseGrid,
            recordData:recordData,
            funCode: "accountOperator_main",
            funcPanel:funcPanel,
            cmd:cmd,
            items: [{
                xtype: funcPanel
            }]
        });
         win.show();
      },
      doFastSearch:function(com,cmd){
        var baseGrid = com.up("basegrid");
        var toolBar = com.up("toolbar");

        var girdSearchTexts = toolBar.query("field[funCode=girdFastSearchText]");
        var filter = new Array();
        var value = girdSearchTexts[0].getValue();
        var store = baseGrid.getStore();
        var proxy = store.getProxy();
        if(cmd=="accountoperator"){
          // filter.push({"type": "string", "value": girdSearchTexts[0].getValue(), "field": "bagCode", "comparison": ""});
          if(value){
            proxy.extraParams.bagCode = value;
            store.loadPage(1);
          }
        
        }else{
          if(value&&value!='all'){
            filter.push({"type": "boolean", "value": girdSearchTexts[0].getValue(), "field": "isAudit", "comparison": ""});
          }
          if(filter.length==0)
            filter=null;
          else 
            filter = JSON.stringify(filter);
            proxy.extraParams.filter = filter;
            store.loadPage(1);

        }
       
      },

     doSave_Win:function(btn){
    //  debugger;
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

    doDeleteSubsidy:function(btn){
         var self=this;
        //得到组件
        var baseGrid = btn.up("basegrid");
        //得到选中数据
        var records = baseGrid.getSelectionModel().getSelection();
        if (records.length > 0) {
            var msg='是否删除数据?';
            if(btn.msg)
                msg=btn.msg;
            
            //封装ids数组
            Ext.Msg.confirm('提示', msg, function (btn, text) {
                if (btn == 'yes') {
                    
                    var loading = new Ext.LoadMask(baseGrid, {
                        msg: '正在提交，请稍等...',
                        removeMask: true// 完成后移除
                    });
                    loading.show();

                    var ids = new Array();
                    Ext.each(records, function (rec) {
                        var pkValue = rec.get("id");
                        ids.push(pkValue);
                    });

                    self.asyncAjax({
                        url:comm.get('baseUrl') + "/PtSubsidyFillMoneyMain/doDelete",
                        params: {
                            ids: ids.join(","),
                        },                       
                        success: function(response) {
                            var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                            if(data.success){
                                var store=baseGrid.getStore();
                                if(store.getData().length==records.length&&store.currentPage>1){    
                                    store.loadPage(store.currentPage-1);
                                }else{
                                    store.remove(records); //不刷新的方式
                                }
                                
                            
                                self.msgbox(data.obj);                               
                            }else {
                                self.Error(data.obj);
                            }           
                            loading.hide();
                        },
                        failure: function(response) {                   
                            Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);
                            loading.hide();
                        }
                    });     
                }
            });
        } else {
            self.msgbox("请选择数据");
        }
    },

    doAudit:function(btn){
      var self = this;
      var baseGrid = btn.up("basegrid[xtype=ykt.card.subsidysetgrid]");
      var records = baseGrid.getSelectionModel().getSelection();
      if(records.length<=0){
        self.msgbox("请选择需要审核的数据！");
        return ;

      }
      var auditUser = comm.get("userName");

      Ext.Msg.confirm('提示', "确定要审核当前这张补助充值申请单吗？当前审核人:'"+auditUser+"'", function (btn, text) {
        if (btn == 'yes') {

          var loading = new Ext.LoadMask(baseGrid, {
            msg: '正在审核，请稍等...',
            removeMask: true// 完成后移除
          });
          loading.show();

          var ids = new Array();
          Ext.each(records, function (rec) {
            var pkValue = rec.get("id");
            ids.push(pkValue);
          });

          self.asyncAjax({
            url: comm.get('baseUrl') + "/PtSubsidyFillMoneyMain/doAudit", 
            params: {
              ids: ids.join(","),
              auditUser:auditUser,
            },                       
            success: function(response) {
              var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

              if(data.success){
                var store=baseGrid.getStore();
                store.loadPage(1);
                self.msgbox(data.obj);                               
              }else {
                self.Error(data.obj);
              }           
              loading.hide();
            },
            failure: function(response) {                   
              Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);
              loading.hide();
            }
          });     
        }
      });
    },

     disabledFuncBtn:function(grid){
         var basegrid = grid.up("basegrid[xtype=ykt.card.subsidysetgrid]");
         var records = basegrid.getSelectionModel().getSelection();
         var btnGridEdit = basegrid.down("button[ref=gridEdit]");
         var btnGridDelete = basegrid.down("button[ref=gridDelete]");
         var btnGridAudit = basegrid.down("button[ref=gridAudit]");
         if (records.length == 0) {
              btnGridEdit.setDisabled(true);
              btnGridDelete.setDisabled(true);
              btnGridAudit.setDisabled(true);

            } else if (records.length == 1) {
              var isAudit = records[0].get("isAudit");
              if(isAudit==true){
                btnGridEdit.setDisabled(true);
                btnGridDelete.setDisabled(true);
                btnGridAudit.setDisabled(true);
              }else{
                btnGridEdit.setDisabled(false);
                btnGridDelete.setDisabled(false);
                btnGridAudit.setDisabled(false);
              }

            } else {
              for(var i in records){
               var isAudit = records[i].get("isAudit");
               if(isAudit==true){
                btnGridEdit.setDisabled(true);
                btnGridDelete.setDisabled(true);
                btnGridAudit.setDisabled(true);
                break;
              }else{
                btnGridEdit.setDisabled(true);
                btnGridDelete.setDisabled(false);
                btnGridAudit.setDisabled(false);
              }

            }
          }
        },


        doSelectConsume:function(table,cellIndex,record){
          var self=this;
          var detailLayout = table.up("basepanel[xtype=ykt.card.filloperatelayout]");
          var baseform = detailLayout.down("baseform[xtype=ykt.card.consumedetailform]");
          var formObject = baseform.getForm();
          self.setFormValue(formObject, record.data);
        },
});