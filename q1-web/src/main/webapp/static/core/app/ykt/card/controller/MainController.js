Ext.define("core.ykt.card.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.ykt.card.maincontroller',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },


    init: function() {
        var self = this;
            //事件注册
        this.control({
            //隐藏按钮事件
            "basepanel basegrid[xtype=ykt.card.maingrid]": {
                afterrender : function(grid) {
                    this.hideFuncBtn(grid);
                },
                beforeitemclick: function(grid, record, item, index, e, eOpts) {
                    this.disabledFuncBtn(grid);
               }
            },

            //挂失解挂按钮点击事件
            "basegrid[xtype=ykt.card.maingrid] button[ref=gridLockOn]": {
                beforeclick: function(btn) {
                    this.doLockOrOn(btn);
                    return false;
                }
            },

            //批量挂失
            "basegrid[xtype=ykt.card.maingrid] button[ref=gridLockBatch]": {
                beforeclick: function(btn) {
                    this.doLockBatch(btn);
                    return false;
                }
            },

            //批量解挂
            "basegrid[xtype=ykt.card.maingrid] button[ref=gridOnBatch]": {
                beforeclick: function(btn) {
                    this.doOnBatch(btn);
                    return false;
                }
            },

            //错扣补款
            "basegrid[xtype=ykt.card.usergrid] button[ref=gridErrorFillMoney]": {
                beforeclick: function(btn) {
                    this.doOpen_Tab(btn,"errorFillMoney");
                    return false;
                }
            },
            //充值和退款
            "basegrid[xtype=ykt.card.usergrid] button[ref=gridUserOperator]": {
                beforeclick: function(btn) {
                    this.doOpen_Tab(btn,"rechargeandrefund");
                    return false;
                }
            },
              //补助设置
            "basegrid[xtype=ykt.card.maingrid] button[ref=gridSubsidySet]": {
                beforeclick: function(btn) {
                    this.doOpen_Tab(btn,"subsidySet");
                    return false;
                }
            },
    
        });
    },

    hideFuncBtn:function(grid){
        if(comm.get("isAdmin")!="1"){
            var menuCode="SCHOOLCALENDAR";     // 此菜单的前缀
            var userBtn=comm.get("userBtn");
            if(userBtn.indexOf(menuCode+"_gridUse")==-1){
                var btnUse = grid.down("button[ref=gridUse]");
                btnUse.setHidden(true);
                
             }
             if(userBtn.indexOf(menuCode+"_gridDelTime")==-1){
                var btnUse = grid.down("button[ref=gridDelTime]");
                btnUse.setHidden(true);
                
            }
         }
    },

    disabledFuncBtn:function(grid){
        var basePanel = grid.up("basepanel");
        var basegrid = basePanel.down("basegrid[xtype=ykt.card.maingrid]");
        var records = basegrid.getSelectionModel().getSelection();
        var btnGridLockOn = basegrid.down("button[ref=gridLockOn]");
        var btnGridLockBatch = basegrid.down("button[ref=gridLockBatch]");
        var btnGridOnBatch = basegrid.down("button[ref=gridOnBatch]");
        if (records.length == 0) {
            btnGridLockOn.setDisabled(true);
            btnGridLockBatch.setDisabled(true);
            btnGridOnBatch.setDisabled(true);
        } else if (records.length == 1) {
            btnGridLockOn.setDisabled(false);
            btnGridLockBatch.setDisabled(false);
            btnGridOnBatch.setDisabled(false);
        } else {
            btnGridLockOn.setDisabled(true);
            btnGridLockBatch.setDisabled(false);
            btnGridOnBatch.setDisabled(false);
        }
    },


     doLockOrOn:function(btn) {
        var self = this;
        //得到组件
        var basegrid = btn.up("basegrid");
        var basepanel = basegrid.up('basepanel');
        var records = basegrid.getSelectionModel().getSelection();
        if(records.length==0){
            self.msgbox("请选择一个需要挂失的卡片!");
            return;
        }
        var id =records[0].get('id');
        var cardStatusId = records[0].get('cardStatusId');
        var msg="";
        if(cardStatusId==1){
            msg="确定挂失此卡？";
        }else{
            msg="确定解除挂失此卡？";
        }

        if (records.length > 0) {
            //封装ids数组
            Ext.Msg.confirm("提示", msg, function (btn, text) {
                if (btn == 'yes') {
                    var loading = self.LoadMask(basegrid);
                    self.asyncAjax({
                        url: comm.get('baseUrl') + "/PtCard/doLockOrOn", 
                        method: "POST",
                        async: true,
                        timeout: 60000,
                        params: {
                         id:id
                     },                      
                        success: function(response) {
                            var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                            if(data.success){
                               basegrid.getStore().load();
                               self.msgbox('操作成功');                               
                            }else {
                                self.Error('操作失败');
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
            this.msgbox("请选择一个需要挂失的卡片!");
        }

    }, 

     doLockBatch:function(btn) {
        var self = this;
        //得到组件
        var basegrid = btn.up("basegrid");
        var basepanel = basegrid.up('basepanel');
        var records = basegrid.getSelectionModel().getSelection();
        if(records.length==0){
            self.msgbox("请至少选择一个需要挂失的卡片!");
            return;
        }
        var ids = new Array();
        Ext.each(records, function (rec) {
            var id = rec.get("id");
            ids.push(id);
        });

        if (records.length > 0) {
            //封装ids数组
            Ext.Msg.confirm("提示", "是否挂失这些卡？", function (btn, text) {
                if (btn == 'yes') {
                    var loading = self.LoadMask(basegrid);
                    self.asyncAjax({
                        url: comm.get('baseUrl') + "/PtCard/doLockBatch", 
                        method: "POST",
                        async: true,
                        timeout: 60000,
                        params: {
                         ids: ids.join(",")
                     },                      
                        success: function(response) {
                            var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                            if(data.success){
                               basegrid.getStore().load();
                               self.msgbox('操作成功');                               
                            }else {
                                self.Error('操作失败');
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
            this.msgbox("请至少选择一个需要挂失的卡片!");
        }
    },        

    doOnBatch:function(btn) {
        var self = this;
        //得到组件
        var basegrid = btn.up("basegrid");
        var basepanel = basegrid.up('basepanel');
        var records = basegrid.getSelectionModel().getSelection();
        if(records.length==0){
            self.msgbox("请至少选择一个需要解除挂失的卡片!");
            return;
        }
        var ids = new Array();
        Ext.each(records, function (rec) {
            var id = rec.get("id");
            ids.push(id);
        });

        if (records.length > 0) {
            //封装ids数组
            Ext.Msg.confirm("提示", "是否解除挂失这些卡？", function (btn, text) {
                if (btn == 'yes') {
                    var loading = self.LoadMask(basegrid);
                    self.asyncAjax({
                        url: comm.get('baseUrl') + "/PtCard/doOnBatch", 
                        method: "POST",
                        async: true,
                        timeout: 60000,
                        params: {
                         ids: ids.join(",")
                     },                      
                        success: function(response) {
                            var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                            if(data.success){
                               basegrid.getStore().load();
                               self.msgbox('操作成功');                               
                            }else {
                                self.Error('操作失败');
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
            this.msgbox("请至少选择一个需要解除挂失的卡片!");
        }
    },   

      doOpen_Tab:function(btn,cmd){
        var self = this;
        var baseGrid = btn.up("basegrid");

        //得到组件
        var funCode = baseGrid.funCode; 
        var basePanel = baseGrid.up("basepanel[funCode=" + funCode +"]");
        var tabPanel=baseGrid.up("tabpanel[xtype=app-main]");   //获取整个tabpanel
       
        if(cmd!="subsidySet"){//补助设置
          var selectUser = baseGrid.getSelectionModel().getSelection();
          if(selectUser.length!=1){
            self.msgbox("请选中一个用户！");
            return;
            }     
        }

       //得到配置信息
        var funData = basePanel.funData;
        var defaultObj = funData.defaultObj;
        
        //处理特殊默认值
        var insertObj = self.getDefaultValue(defaultObj);
        var popFunData = Ext.apply(funData, {
            grid: baseGrid
        });
        //获取主键值
        var pkName = funData.pkName;
        var pkValue='errorFillMoney';
        
        var tabTitle = "错扣补款";
        var tabItemId = funCode+"_errorFillMoney"; 
        var detCode =  "card_errorFillMoney";  
        var detLayout = "ykt.card.errorfillmoneygrid";
        switch(cmd){
          case "subsidySet":
            tabTitle = "补助设置";
            tabItemId = funCode+"_subsidySet"; 
            detCode =  "card_subsidySet";  
            detLayout = "ykt.card.subsidysetgrid";
            pkValue='subsidySet';
            break;
         case "rechargeandrefund":
            tabTitle = "充值/退款";
            tabItemId = funCode+"_rechargeandrefund"; 
            detCode =  "card_rechargeandrefund";  
            detLayout = "ykt.card.accountoperatorgrid";
            pkValue='rechargeAndRefund';
            break;
        }
        //获取tabItem；若不存在，则表示要新建tab页，否则直接打开
        var tabItem=tabPanel.getComponent(tabItemId);
        if(!tabItem){
            //创建一个新的TAB
            tabItem=Ext.create({
                xtype:'container',
                title: tabTitle,
                scrollable :true, 
                itemId:tabItemId,
                itemPKV:pkValue,      //保存主键值
                layout:'fit', 
            });
            tabPanel.add(tabItem); 

            //延迟放入到tab中
            setTimeout(function(){
                //创建组件
                var item=Ext.widget("baseformtab",{
                    operType:'detail',                            
                    controller:'ykt.card.detailcontroller',         //指定重写事件的控制器
                    funCode:funCode,                    //指定mainLayout的funcode
                    detCode:detCode,                    //指定detailLayout的funcode
                    tabItemId:tabItemId,                //指定tab页的itemId
                    insertObj:insertObj,                //保存一些需要默认值，提供给提交事件中使用
                    funData: popFunData,                //保存funData数据，提供给提交事件中使用
                    selectUser:selectUser,
                    items:[{
                        xtype:detLayout,                        
                        funCode: detCode             
                    }]
                }); 
                tabItem.add(item);  
                if(cmd=="errorFillMoney"){//错扣补款
                   var userCardGrid = item.down("basegrid[xtype=ykt.card.errorfillmoneygrid]");
                   var userCardStore = userCardGrid.getStore();
                   var userCardProxy = userCardStore.getProxy();
                   userCardProxy.extraParams = {
                    userId: selectUser[0].id,
                };
                userCardStore.load();
            }
            if(cmd=="rechargeandrefund"){// 充值 / 退款
               var accountOperatorGrid = item.down("basegrid[xtype=ykt.card.accountoperatorgrid]");
               var accountOperatorStore = accountOperatorGrid.getStore();
               var accountOperatorProxy = accountOperatorStore.getProxy();
               accountOperatorProxy.extraParams = {
                userId: selectUser[0].id,
            };
            accountOperatorStore.load();
          }

            },30);
            
        }else if(tabItem.itemPKV&&tabItem.itemPKV!=pkValue){     //判断是否点击的是同一条数据
            self.Warning("您当前已经打开了一个编辑窗口了！");
            return;
        }
        tabPanel.setActiveTab( tabItem);     

     },
});