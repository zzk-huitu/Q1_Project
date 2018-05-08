/**
    ( *非必须，只要需要使用时，才创建他 )
    此视图控制器，用于注册window之类的组件的事件，该类组件不属于 mainLayout和detailLayout范围内。
    但需要在创建window中，使用controller属性来指定此视图控制器，才可生效
*/
Ext.define("core.ykt.operatorBind.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.ykt.operatorBind.othercontroller',
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
        //绑定工作站选择后确定事件
        "baseformwin[funCode=operatorBind_selectStationmain] button[ref=formSave]": {
            beforeclick: function(btn) {
                this.doAddStation(btn);            
                return false;
            }
        }, 

         //绑定账户选择后确定事件
        "baseformwin[funCode=operatorBind_selectAccountmain] button[ref=formSave]": {
            beforeclick: function(btn) {
                this.doAddAccount(btn);            
                return false;
            }
        }, 

        //房间树单击事件
        "basetreegrid[xtype=ykt.operatorBind.areagrid]":{
                itemclick: function (tree, record, item, index, e, eOpts) {
                    this.loadMainGridStore(tree,record);                
                    return false;
                }
        }, 

        //绑定账户选择后确定事件
        "baseformwin[funCode=operatorBind_selectRoommain] button[ref=formSave]": {
            beforeclick: function(btn) {
                this.doAddRoom(btn);            
                return false;
            }
        },             

    },


    doAddStation:function(btn){
        var self=this;
            
        var win = btn.up('window');
        var funCode = win.funCode;
        var funData = win.funData;
        var userId = funData.userId;
        var basePanel = win.down("basepanel[funCode=" + funCode + "]");
        var isSelectGrid = basePanel.down("panel[xtype=ykt.operatorBind.isSelectStationGrid]");
        var isSelectStore = isSelectGrid.getStore();
        var iCount = isSelectStore.getCount(); //已选的工作站个数

        //拼装所选择的工作站
        var ids = new Array();
        for (var i = 0; i < iCount; i++) {
            var record = isSelectStore.getAt(i);
            var pkValue = record.get("id");
            if(ids.indexOf(pkValue)==-1)
                ids.push(pkValue);
        }
        if (ids.length > 0) {
             //显示loadMask
            var myMask = self.LoadMask(win);
            //提交入库
            self.asyncAjax({
                url: funData.action + "/doAdd",
                params: {
                    userId: userId,
                    ids: ids.join(",")
                },
                //回调代码必须写在里面
                success: function(response) {
                    data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));
                    
                    myMask.hide();
                    if (data.success) { 
                        self.msgbox("保存成功!");
                        var grid = win.funData.grid; //窗体是否有grid参数
                        if (!Ext.isEmpty(grid)) {
                            var store = grid.getStore();
                            var proxy = store.getProxy();
                            proxy.extraParams = {
                                whereSql: win.funData.whereSql,
                                orderSql: win.funData.orderSql,
                                userId: userId
                            };
                            store.load(); //刷新父窗体的grid
                            win.close();
                        }
                    }else{
                        self.Error(data.obj);   
                    }
                },
                failure: function(response) {           
                    Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);           
                    myMask.hide();
                }
            });                   
        } else {
            self.msgbox("没有绑定工作站");
        }
    },

    doAddAccount:function(btn){
        var self=this;
            
        var win = btn.up('window');
        var funCode = win.funCode;
        var funData = win.funData;
        var userId = funData.userId;
        var basePanel = win.down("basepanel[funCode=" + funCode + "]");
        var isSelectGrid = basePanel.down("panel[xtype=ykt.operatorBind.isSelectAccountGrid]");
        var isSelectStore = isSelectGrid.getStore();
        var iCount = isSelectStore.getCount(); //已选的工作站个数

        //拼装所选择的工作站
        var ids = new Array();
        for (var i = 0; i < iCount; i++) {
            var record = isSelectStore.getAt(i);
            var pkValue = record.get("id");
            if(ids.indexOf(pkValue)==-1)
                ids.push(pkValue);
        }
        if (ids.length > 0) {
             //显示loadMask
            var myMask = self.LoadMask(win);
            //提交入库
            self.asyncAjax({
                url: funData.action + "/doAdd",
                params: {
                    userId: userId,
                    ids: ids.join(",")
                },
                //回调代码必须写在里面
                success: function(response) {
                    data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));
                    
                    myMask.hide();
                    if (data.success) { 
                        self.msgbox("保存成功!");
                        var grid = win.funData.grid; //窗体是否有grid参数
                        if (!Ext.isEmpty(grid)) {
                            var store = grid.getStore();
                            var proxy = store.getProxy();
                            proxy.extraParams = {
                                whereSql: win.funData.whereSql,
                                orderSql: win.funData.orderSql,
                                userId: userId
                            };
                            store.load(); //刷新父窗体的grid
                            win.close();
                        }
                    }else{
                        self.Error(data.obj);   
                    }
                },
                failure: function(response) {           
                    Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);           
                    myMask.hide();
                }
            });                   
        } else {
            self.msgbox("没有绑定工作站");
        }
    },

    loadMainGridStore:function(tree,record){
        var self = this;
        var mainLayout = tree.up("panel[xtype=ykt.operatorBind.selectRoomLayout]");
        var win = Ext.getCmp("operatorBind_selectRoommain_win");
        var winFunData = win.funData;
        var userId = winFunData.userId;
        var areaType = record.get("areaType");
        var areaId = record.get("id");
        var roomGrid = mainLayout.down("panel[xtype=ykt.operatorBind.selectRoomGrid]");

        var filter=this.getFastSearchFilter(roomGrid);
        // if(areaType=="04")
        //       filter.push({"type":"string","comparison":"=","value": areaId ,"field":"areaId"});
        if(filter.length==0)
            filter=null;
        else 
            filter = JSON.stringify(filter);

        Ext.apply(mainLayout.funData, {
            areaId: record.get("id"),
            areaType: record.get("areaType"),
            areaName: record.get("text"),
        });

        // 加载对应的房间信息
        var store = roomGrid.getStore();
        var proxy = store.getProxy();

        //附带参数
        proxy.extraParams={
            areaId:areaId,           
            areaType:areaType,
            userId:userId,
            filter:filter
        }
        store.loadPage(1); // 给form赋值
    },    
   
    getFastSearchFilter:function(cpt){
        var girdSearchTexts = cpt.query("field[funCode=girdFastSearchText]");
        var filter=new Array();
        if(girdSearchTexts[0].getValue()){
            filter.push({"type": "string", "value": girdSearchTexts[0].getValue(), "field": "roomName", "comparison": ""})
        }
      
        return filter;
    },

    doAddRoom:function(btn){
        var self=this;
            
        var win = btn.up('window');
        var funCode = win.funCode;
        var funData = win.funData;
        var userId = funData.userId;
        var basePanel = win.down("basepanel[funCode=" + funCode + "]");
        var isSelectGrid = basePanel.down("panel[xtype=ykt.operatorBind.isSelectRoomGrid]");
        var isSelectStore = isSelectGrid.getStore();
        var iCount = isSelectStore.getCount(); //已选的工作站个数

        //拼装所选择的工作站
        var ids = new Array();
        for (var i = 0; i < iCount; i++) {
            var record = isSelectStore.getAt(i);
            var pkValue = record.get("id");
            if(ids.indexOf(pkValue)==-1)
                ids.push(pkValue);
        }
        if (ids.length > 0) {
             //显示loadMask
            var myMask = self.LoadMask(win);
            //提交入库
            self.asyncAjax({
                url: funData.action + "/doAdd",
                params: {
                    userId: userId,
                    ids: ids.join(",")
                },
                //回调代码必须写在里面
                success: function(response) {
                    data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));
                    
                    myMask.hide();
                    if (data.success) { 
                        self.msgbox("保存成功!");
                        var grid = win.funData.grid; //窗体是否有grid参数
                        if (!Ext.isEmpty(grid)) {
                            var store = grid.getStore();
                            var proxy = store.getProxy();
                            proxy.extraParams = {
                                whereSql: win.funData.whereSql,
                                orderSql: win.funData.orderSql,
                                userId: userId
                            };
                            store.load(); //刷新父窗体的grid
                            win.close();
                        }
                    }else{
                        self.Error(data.obj);   
                    }
                },
                failure: function(response) {           
                    Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);           
                    myMask.hide();
                }
            });                   
        } else {
            self.msgbox("没有绑定工作站");
        }
    },    
});