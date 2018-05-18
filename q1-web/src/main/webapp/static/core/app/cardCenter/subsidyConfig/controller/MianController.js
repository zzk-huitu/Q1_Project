Ext.define("core.cardCenter.subsidyConfig.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.cardCenter.subsidyConfig.mainController',
    mixins: {   
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil',
    },
    init: function () {
        /*执行一些初始化的代码*/
    },
    /** 该视图内的组件事件注册 */
    control: {
    	/*补助设置:快速搜索框*/
		"basegrid[xtype=cardCenter.subsidyConfig.mainGrid] field[funCode=girdFastSearchText]":{
			specialkey: function (field, e) {
				if (e.getKey() == e.ENTER) {
					this.doFastSearch(field); 
					return false;             
				}
			}
		},
		"basegrid[xtype=cardCenter.subsidyConfig.mainGrid] button[ref=gridFastSearchBtn]":{
			beforeclick: function(btn) {
				this.doFastSearch(btn);
				return false;
			} 
		},
       // 补助设置: 隐藏按钮事件
       "basegrid[xtype=cardCenter.subsidyConfig.mainGrid]": {   
	        beforeitemclick: function(grid, record, item, index, e, eOpts) {
	          this.disabledFuncBtn(grid);
              return false;
	        }
       },

       /*补助设置： 添加 */
       "basegrid[xtype=cardCenter.subsidyConfig.mainGrid] button[ref=gridAdd]":{
	      	beforeclick: function(btn) {
	      		this.doAddAndEdit_Win(btn,"add");
	      		return false;
	      	} 
       },
	    //补助设置：  编辑
	   "basegrid[xtype=cardCenter.subsidyConfig.mainGrid] button[ref=gridEdit]":{
			beforeclick: function(btn) {
				this.doAddAndEdit_Win(btn,"edit");
				return false;
			} 
	   },
       "basegrid[xtype=cardCenter.subsidyConfig.mainGrid] actioncolumn":{
            editClick_Win: function(data) {
                this.doAddAndEdit_Win(null,"edit",data.view,data.record);  
                return false;      
            },

            deleteClick:function(data){
                this.doDeleteSubsidy(null,data.view,data.record);
                return false;
            },
        },
	   //补助设置： 删除
	   "basegrid[xtype=cardCenter.subsidyConfig.mainGrid] button[ref=gridDelete]":{
		   	beforeclick: function(btn) {
		   		this.doDeleteSubsidy(btn);
		   		return false;
		   	} 
	   },
	   //补助设置： 审核
	   "basegrid[xtype=cardCenter.subsidyConfig.mainGrid] button[ref=gridAudit]":{
		   	beforeclick: function(btn) {
		   		this.doAudit(btn);
		   		return false;
		   	} 
	   },
    },


    doAddAndEdit_Win :function(btn,cmd,grid,recode){
    	var self=this;
        //得到组件
        var baseGrid = grid;
        var insertObj;
        if(!baseGrid){
           baseGrid = btn.up("basegrid");
        }

       
        var funCode = baseGrid.funCode;
        var basePanel = baseGrid.up("basepanel[funCode=" + funCode + "]");


        //得到配置信息
        var funData = basePanel.funData;
        var detCode = basePanel.detCode;
        var detLayout = basePanel.detLayout;
        var otherController = basePanel.otherController;    //关键：打开的tab页面的视图控制器
        if (!otherController)
            otherController = '';    

        var iconCls= 'x-fa fa-plus-circle';
        //处理特殊默认值
        if(cmd!="add"){
            if(grid){
                insertObj = recode.getData();

            }else{
                var rescords = baseGrid.getSelectionModel().getSelection();
                if (rescords.length != 1) {
                    self.msgbox("请选择数据");
                    return;
                }
                insertObj = rescords[0].getData();
            }
            iconCls= 'x-fa fa-pencil-square';  
        }
        var width = 1000;
        var height = 600;
        var popFunData = Ext.apply(funData, {
        	grid: baseGrid
        });
        var win = Ext.create('core.base.view.BaseFormWin', {
        	iconCls:iconCls,
        	operType: cmd,
        	width: width,
        	height: height,
        	controller: otherController,
        	grid: baseGrid,
        	funData: popFunData,
        	funCode: detCode,
            insertObj: insertObj,
        	items: [{
        		xtype: detLayout
        	}]
        });
        win.show();
        if(cmd=="edit"){
        	var detPanel = win.down("basepanel[funCode='"+win.funCode+"']");
        	var objDetForm = detPanel.down("baseform[xtype=cardCenter.subsidyConfig.detailForm]");
        	var formDeptObj = objDetForm.getForm();
        	self.setFormValue(formDeptObj, insertObj);

        	var baseGrid = detPanel.down("basegrid[xtype=cardCenter.subsidyConfig.userGrid]");
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
    

    doDeleteSubsidy:function(btn,grid,record){
         var self=this;
        //得到组件
        var baseGrid = grid;
        var records;
        if(!baseGrid){
            baseGrid = btn.up("basegrid");
            records= baseGrid.getSelectionModel().getSelection();

        }else{
            records=new Array();
            records.push(record);

        } 
        //得到选中数据
        
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
    	var baseGrid = btn.up("basegrid[xtype=cardCenter.subsidyConfig.mainGrid]");
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

    doFastSearch:function(com,cmd){
    	var baseGrid = com.up("basegrid");
    	var toolBar = com.up("toolbar");

    	var girdSearchTexts = toolBar.query("field[funCode=girdFastSearchText]");
    	var filter = new Array();
    	var value = girdSearchTexts[0].getValue();
    	var store = baseGrid.getStore();
    	var proxy = store.getProxy();

    	if(value&&value!='all'){
    		filter.push({"type": "boolean", "value": girdSearchTexts[0].getValue(), "field": "isAudit", "comparison": ""});
    	}
    	if(filter.length==0)
    		filter=null;
    	else 
    		filter = JSON.stringify(filter);
    	proxy.extraParams.filter = filter;
    	store.loadPage(1);
   },


    disabledFuncBtn:function(grid){
	   	var basegrid = grid.up("basegrid[xtype=cardCenter.subsidyConfig.mainGrid]");
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