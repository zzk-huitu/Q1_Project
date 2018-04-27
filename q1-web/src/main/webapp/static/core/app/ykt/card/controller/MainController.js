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
 
});