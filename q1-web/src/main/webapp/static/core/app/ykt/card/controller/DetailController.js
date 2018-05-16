/**
    ( *非必须，只要需要使用时，才创建他 )
    此视图控制器，提供于DeatilLayout范围内的界面组件注册事件
*/
Ext.define("core.ykt.card.controller.DetailController", {
	  extend: "Ext.app.ViewController",
	    alias: 'controller.ykt.card.detailcontroller',
	    mixins: {
	    	suppleUtil: "core.util.SuppleUtil",
	        messageUtil: "core.util.MessageUtil",
	        formUtil: "core.util.FormUtil",
	        gridActionUtil: "core.util.GridActionUtil",
	        dateUtil: 'core.util.DateUtil'
	        
	    },
	    init: function() {},
	    /** 该视图内的组件事件注册 */
	    control: {
	     /* 错扣补款->补款操作*/
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

         //错扣补款->补款操作 :点击单元格，将数据选入
        "basegrid[xtype=ykt.card.consumedetailgrid]":{
            cellclick: function(table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                this.doSelectConsume(table,cellIndex,record);       
            }

        },
        /*错扣补款->补款操作 :快速搜索框 待完成*/
        "basegrid[xtype=ykt.card.consumedetailgrid] field[funCode=girdFastSearchText]":{
        	specialkey: function (field, e) {
        		if (e.getKey() == e.ENTER) {
        			this.doFastSearch(field,"consumedetail");  
              return false;              
        		}
        	}
        },
        "basegrid[xtype=ykt.card.consumedetailgrid] button[ref=gridFastSearchBtn]":{
        	beforeclick: function(btn) {
        		this.doFastSearch(btn,"consumedetail");
        		return false;
        	} 
        },

/////////////////////////////////////////////////////////////////////////
        /*充值/退款-> 用户操作 ：充值 */
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=userRecharge]":{
        	beforeclick: function(btn) {
        		this.doAccountOperator(btn,"recharge");
        		return false;
        	} 
        },
        /* 充值/退款-> 用户操作 ： 退款*/
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=userRefund]":{
        	beforeclick: function(btn) {
        		this.doAccountOperator(btn,"refund");
        		return false;
        	} 
        },
         /*  充值/退款-> 用户操作 ： 充值 退款 */
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
         /*充值/退款-> 用户操作：快速搜索框*/
        "basegrid[xtype=ykt.card.accountoperatorgrid] field[funCode=girdFastSearchText]":{
         specialkey: function (field, e) {
          if (e.getKey() == e.ENTER) {
            this.doFastSearch(field,"accountoperator");   
            return false;             
          }
        }
      },
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=gridFastSearchBtn]":{
          beforeclick: function(btn) {
            this.doFastSearch(btn,"accountoperator");
            return false;
          } 
        },
////////////////////////////////////////////////////////////////////
		/*补助设置->补助钱包充值设置： 添加 */
		"basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridAdd]":{
			beforeclick: function(btn) {
				this.doAddAndEdit_Win(btn,"add");
				return false;
			} 
		},
		//补助设置->补助钱包充值设置：  编辑
		"basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridEdit]":{
			beforeclick: function(btn) {
				this.doAddAndEdit_Win(btn,"edit");
				return false;
			} 
		},
		//补助设置->补助钱包充值设置： 删除
		"basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridDelete]":{
			beforeclick: function(btn) {
				this.doDeleteSubsidy(btn);
				return false;
			} 
		},
		//补助设置->补助钱包充值设置： 审核
		"basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridAudit]":{
			beforeclick: function(btn) {
				this.doAudit(btn);
				return false;
			} 
		},
		/*补助设置->补助钱包充值设置:快速搜索框*/
		"basegrid[xtype=ykt.card.subsidysetgrid] field[funCode=girdFastSearchText]":{
			specialkey: function (field, e) {
				if (e.getKey() == e.ENTER) {
					this.doFastSearch(field,"subsidyset"); 
          return false;               
				}
			}
		},
		"basegrid[xtype=ykt.card.subsidysetgrid] button[ref=gridFastSearchBtn]":{
			beforeclick: function(btn) {
				this.doFastSearch(btn,"subsidyset");
				return false;
			} 
		},
      // 补助设置->补助钱包充值设置: 隐藏按钮事件
      "basegrid[xtype=ykt.card.subsidysetgrid]": {
        beforeitemclick: function(grid, record, item, index, e, eOpts) {
          this.disabledFuncBtn(grid);
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
                return;

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
 
      doSelectConsume:function(table,cellIndex,record){
          var self=this;
          var detailLayout = table.up("basepanel[xtype=ykt.card.filloperatelayout]");
          var baseform = detailLayout.down("baseform[xtype=ykt.card.consumedetailform]");
          var formObject = baseform.getForm();
          record.data.consumeDate = Ext.Date.format(new Date(record.data.consumeDate*1), 'Y/m/d');
          self.setFormValue(formObject, record.data);
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
      var sign = 0; //0b表示充值
      var funcPanel = "ykt.card.accountoperatorform";
      var funcTitle = "充值";// 某某充值/退款
      var otherController = 'ykt.card.othercontroller';
      if(cmd=="refund"){
	      	funcTitle = "退款";
	        sign = 1; // 1:退款
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
	    	sign:sign,
	    	items: [{
	    		xtype: funcPanel
	    	}]
	    });
	    win.show();
	    var baseForm = win.down("baseform[xtype='"+win.funcPanel+"']");
	    var formDeptObj = baseForm.getForm();
	    self.setFormValue(formDeptObj, recordData);
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

      }else if(cmd=="subsidyset"){
      	if(value&&value!='all'){
      		filter.push({"type": "boolean", "value": girdSearchTexts[0].getValue(), "field": "isAudit", "comparison": ""});
      	}
      	if(filter.length==0)
      		filter=null;
      	else 
      		filter = JSON.stringify(filter);
      	proxy.extraParams.filter = filter;
      	store.loadPage(1);

        }else if(cmd=="consumedetail"){//消费机类型 未加 
        	if(value){
        		proxy.extraParams.consumeDate = value;
        		store.loadPage(1);
        	}
        }

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

});