Ext.define("core.ykt.operatorBind.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.ykt.operatorBind.maincontroller',
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
        	 //添加工作站
            "panel[xtype=ykt.operatorBind.workStationBindGrid] button[ref=gridBindWorkstation]": {
                beforeclick: function(btn) {
                    this.openSelectStation(btn);            
                    return false;
                }
            },

             //添加房间
            "panel[xtype=ykt.operatorBind.roomBindGrid] button[ref=gridBindRoom]": {
                beforeclick: function(btn) {
                    this.openSelectRoom(btn);            
                    return false;
                }
            },

              //添加账户
            "panel[xtype=ykt.operatorBind.accountBindGrid] button[ref=gridBindAccount]": {
                beforeclick: function(btn) {
                    this.openSelectAccount(btn);            
                    return false;
                }
            },

            //隐藏工作站解绑按钮事件
            "basepanel basegrid[xtype=ykt.operatorBind.workStationBindGrid]": {
                beforeitemclick: function(grid, record, item, index, e, eOpts) {
                    this.disabledStationFuncBtn(grid);
               }
            },

            //隐藏房间解绑按钮事件
            "basepanel basegrid[xtype=ykt.operatorBind.roomBindGrid]": {
                beforeitemclick: function(grid, record, item, index, e, eOpts) {
                    this.disabledRoomFuncBtn(grid);
               }
            },

            //隐藏账户解绑按钮事件
            "basepanel basegrid[xtype=ykt.operatorBind.accountBindGrid]": {
                beforeitemclick: function(grid, record, item, index, e, eOpts) {
                    this.disabledAccountFuncBtn(grid);
               }
            },

            //解绑工作站按钮事件
            "basegrid[xtype=ykt.operatorBind.workStationBindGrid] button[ref=gridReleaseWorkstation]": {
                beforeclick: function(btn) {
                    this.doReleaseWorkstation(btn);
                    return false;
                }
            },

            //解绑账户按钮事件
            "basegrid[xtype=ykt.operatorBind.accountBindGrid] button[ref=gridReleaseAccount]": {
                beforeclick: function(btn) {
                    this.doReleaseAccount(btn);
                    return false;
                }
            },

            //解绑房间按钮事件
            "basegrid[xtype=ykt.operatorBind.roomBindGrid] button[ref=gridReleaseRoom]": {
                beforeclick: function(btn) {
                    this.doReleaseRoom(btn);
                    return false;
                }
            },
        	
        });

    },
    
    
    openSelectStation:function(btn){
        var self=this;

        var workStationBindGrid = btn.up("basegrid");
        var mainLayout = workStationBindGrid.up("basepanel[xtype=ykt.operatorBind.mainlayout]");
        var funData = mainLayout.funData;
        var userGrid = mainLayout.down("basegrid[xtype=ykt.operatorBind.usergrid]");          
        var userSelect = userGrid.getSelectionModel().getSelection();
        var selectUserId ="";
        if (userSelect.length > 0) {
        	selectUserId = userSelect[0].id;
        }else{
        	 self.msgbox("请选择人员数据");
        	 return false;
        }
        //选择的用户
       
        var detCode = "operatorBind_selectStationmain";
        var popFunData = Ext.apply(funData, {
            grid: workStationBindGrid,
            userId: selectUserId,
            action: comm.get('baseUrl') + "/PtUserWorkStationBind",
        }); //
        var cmd = "edit";
        var winId = detCode + "_win";
        var win = Ext.getCmp(winId);
        if (!win) {
            win = Ext.create('core.base.view.BaseFormWin', {
                id: winId,
                title: "绑定工作站选择",
                operType: cmd,
                width: 1024,
                height: 600,
                resizable: false,
                controller:'ykt.operatorBind.othercontroller',
                iconCls: "x-fa fa-user",
                funData: popFunData,
                funCode: detCode,
                txtformSave: "确定",
                items: [{
                    xtype: "ykt.operatorBind.selectStationLayout"
                }]
            });
        }
        win.show();
        //待选的项目中要过虑掉已选择的
        var selectStationGrid = win.down("panel[xtype=ykt.operatorBind.selectStationGrid]");
        var selectStationStore = selectStationGrid.getStore();
        var selectStationProxy = selectStationStore.getProxy();
        selectStationProxy.extraParams = {
            userId: selectUserId
        };
        selectStationStore.load();
    },

    /*
     * 绑定房间弹窗
     */
    openSelectRoom:function(btn){
        var self=this;

        var roomBindGrid = btn.up("basegrid");
        var mainLayout = roomBindGrid.up("basepanel[xtype=ykt.operatorBind.mainlayout]");
        var funData = mainLayout.funData;
        var userGrid = mainLayout.down("basegrid[xtype=ykt.operatorBind.usergrid]");          
        var userSelect = userGrid.getSelectionModel().getSelection();
        var selectUserId ="";
        if (userSelect.length > 0) {
            selectUserId = userSelect[0].id;
        }else{
             self.msgbox("请选择人员数据");
             return false;
        }
        //选择的用户
       
        var detCode = "operatorBind_selectRoommain";
        var popFunData = Ext.apply(funData, {
            grid: roomBindGrid,
            userId: selectUserId,
            action: comm.get('baseUrl') + "/PtUserRoomBind",
        }); //
        var cmd = "edit";
        var winId = detCode + "_win";
        var win = Ext.getCmp(winId);
        if (!win) {
            win = Ext.create('core.base.view.BaseFormWin', {
                id: winId,
                title: "绑定房间选择",
                operType: cmd,
                width: 1200,
                height: 600,
                resizable: false,
                controller:'ykt.operatorBind.othercontroller',
                iconCls: "x-fa fa-user",
                funData: popFunData,
                funCode: detCode,
                txtformSave: "确定",
                items: [{
                    xtype: "ykt.operatorBind.selectRoomLayout"
                }]
            });
        }
        win.show();
        // //待选的项目中要过虑掉已选择的
        // var selectRoomGrid = win.down("panel[xtype=ykt.operatorBind.selectRoomGrid]");
        // var selectRoomStore = selectRoomGrid.getStore();
        // var selectRoomProxy = selectRoomStore.getProxy();
        // selectRoomProxy.extraParams = {
        //     userId: selectUserId
        // };
        // selectRoomStore.load();
    },

    /*
     * 选择账户弹窗
     */
    openSelectAccount:function(btn){
        var self=this;

        var accountBindGrid = btn.up("basegrid");
        var mainLayout = accountBindGrid.up("basepanel[xtype=ykt.operatorBind.mainlayout]");
        var funData = mainLayout.funData;
        var userGrid = mainLayout.down("basegrid[xtype=ykt.operatorBind.usergrid]");          
        var userSelect = userGrid.getSelectionModel().getSelection();
        var selectUserId ="";
        if (userSelect.length > 0) {
            selectUserId = userSelect[0].id;
        }else{
             self.msgbox("请选择人员数据");
             return false;
        }
        //选择的用户
       
        var detCode = "operatorBind_selectAccountmain";
        var popFunData = Ext.apply(funData, {
            grid: accountBindGrid,
            userId: selectUserId,
            action: comm.get('baseUrl') + "/PtUserAccountBind",
        }); //
        var cmd = "edit";
        var winId = detCode + "_win";
        var win = Ext.getCmp(winId);
        if (!win) {
            win = Ext.create('core.base.view.BaseFormWin', {
                id: winId,
                title: "绑定工作站选择",
                operType: cmd,
                width: 1024,
                height: 600,
                resizable: false,
                controller:'ykt.operatorBind.othercontroller',
                iconCls: "x-fa fa-user",
                funData: popFunData,
                funCode: detCode,
                txtformSave: "确定",
                items: [{
                    xtype: "ykt.operatorBind.selectAccountLayout"
                }]
            });
        }
        win.show();
        //待选的项目中要过虑掉已选择的
        var selectAccountGrid = win.down("panel[xtype=ykt.operatorBind.selectAccountGrid]");
        var selectAccountStore = selectAccountGrid.getStore();
        var selectAccountProxy = selectAccountStore.getProxy();
        selectAccountProxy.extraParams = {
            userId: selectUserId
        };
        selectAccountStore.load();
    },

    disabledStationFuncBtn:function(grid){
        var basePanel = grid.up("basepanel");
        var basegrid = basePanel.down("basegrid[xtype=ykt.operatorBind.workStationBindGrid]");

        var records = basegrid.getSelectionModel().getSelection();
        var btnGridReleaseWorkstation = basegrid.down("button[ref=gridReleaseWorkstation]");
        if (records.length == 0) {
            btnGridReleaseWorkstation.setDisabled(true);
        } else {
            btnGridReleaseWorkstation.setDisabled(false);
        }
    },

    disabledRoomFuncBtn:function(grid){
        var basePanel = grid.up("basepanel");
        var basegrid = basePanel.down("basegrid[xtype=ykt.operatorBind.roomBindGrid]");

        var records = basegrid.getSelectionModel().getSelection();
        var btnGridReleaseRoom = basegrid.down("button[ref=gridReleaseRoom]");
        if (records.length == 0) {
            btnGridReleaseRoom.setDisabled(true);
        } else {
            btnGridReleaseRoom.setDisabled(false);
        }
    },

    disabledAccountFuncBtn:function(grid){
        var basePanel = grid.up("basepanel");
        var basegrid = basePanel.down("basegrid[xtype=ykt.operatorBind.accountBindGrid]");

        var records = basegrid.getSelectionModel().getSelection();
        var btnGridReleaseAccount = basegrid.down("button[ref=gridReleaseAccount]");
        if (records.length == 0) {
            btnGridReleaseAccount.setDisabled(true);
        } else {
            btnGridReleaseAccount.setDisabled(false);
        }
    },

    doReleaseWorkstation:function(btn) {
        var self = this;
        //得到组件
        var basegrid = btn.up("basegrid");
        var basepanel = basegrid.up('basepanel');
        var records = basegrid.getSelectionModel().getSelection();
        if(records.length==0){
            self.msgbox("请至少选择一条需要解绑的工作站数据!");
            return;
        }
        var ids = new Array();
        Ext.each(records, function (rec) {
            var id = rec.get("id");
            ids.push(id);
        });

        if (records.length > 0) {
            //封装ids数组
            Ext.Msg.confirm("提示", "是否解绑这些工作站？", function (btn, text) {
                if (btn == 'yes') {
                    var loading = self.LoadMask(basegrid);
                    self.asyncAjax({
                        url: comm.get('baseUrl') + "/PtUserWorkStationBind/doDelete", 
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
            this.msgbox("请至少选择一条需要解绑的工作站数据!");
        }
    },

    doReleaseAccount:function(btn) {
        var self = this;
        //得到组件
        var basegrid = btn.up("basegrid");
        var basepanel = basegrid.up('basepanel');
        var records = basegrid.getSelectionModel().getSelection();
        if(records.length==0){
            self.msgbox("请至少选择一条需要解绑的账户数据!");
            return;
        }
        var ids = new Array();
        Ext.each(records, function (rec) {
            var id = rec.get("id");
            ids.push(id);
        });

        if (records.length > 0) {
            //封装ids数组
            Ext.Msg.confirm("提示", "是否解绑这些账户？", function (btn, text) {
                if (btn == 'yes') {
                    var loading = self.LoadMask(basegrid);
                    self.asyncAjax({
                        url: comm.get('baseUrl') + "/PtUserAccountBind/doDelete", 
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
            this.msgbox("请至少选择一条需要解绑的账户数据!");
        }
    }, 

    doReleaseRoom:function(btn) {
        var self = this;
        //得到组件
        var basegrid = btn.up("basegrid");
        var basepanel = basegrid.up('basepanel');
        var records = basegrid.getSelectionModel().getSelection();
        if(records.length==0){
            self.msgbox("请至少选择一条需要解绑的房间数据!");
            return;
        }
        var ids = new Array();
        Ext.each(records, function (rec) {
            var id = rec.get("id");
            ids.push(id);
        });

        if (records.length > 0) {
            //封装ids数组
            Ext.Msg.confirm("提示", "是否解绑这些房间？", function (btn, text) {
                if (btn == 'yes') {
                    var loading = self.LoadMask(basegrid);
                    self.asyncAjax({
                        url: comm.get('baseUrl') + "/PtUserRoomBind/doDelete", 
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
            this.msgbox("请至少选择一条需要解绑的房间数据!");
        }
    },       

});