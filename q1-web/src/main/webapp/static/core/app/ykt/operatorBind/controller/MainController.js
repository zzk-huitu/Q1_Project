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
        	 //添加用户所属角色事件
            "panel[xtype=ykt.operatorBind.workStationBindGrid] button[ref=gridBindWorkstation]": {
                beforeclick: function(btn) {
                    this.openSelectStation(btn);            
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
        	selectUserId = userSelect[0].get(id);
        }else{
        	 self.msgbox("请选择数据");
        	 return false;
        }
        //选择的用户
       
        var detCode = "user_selectStationmain";
        var popFunData = Ext.apply(funData, {
            grid: workStationBindGrid,
            userId: selectUserId
        }); //
        var winId = detCode + "_win";
        var win = Ext.getCmp(winId);
        if (!win) {
            win = Ext.create('core.base.view.BaseFormWin', {
                id: winId,
                title: "绑定工作站选择",
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
});