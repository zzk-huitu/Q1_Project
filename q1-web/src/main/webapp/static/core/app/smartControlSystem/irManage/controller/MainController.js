Ext.define("core.smartControlSystem.irManage.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.smartControlSystem.irManage.mainController',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },
    
    init: function() {

    },
    control:{
        "basetreegrid[xtype=smartControlSystem.irManage.roomInfoTree] ": {
            /*
                当点击了这个树的子项后，在查询列表的条件中，要做如下工作：
                1. 附带树节点的相关参数
                2. 当存在basegrid的默认参数，则附带上去
                3. 附带快速搜索中的参数（为了防止文本框的数据与实际查询的数据不一致，所以在下面代码中主动获取了文本框的数据）
                4. reset清除高级搜索中的条件数据 以及 proxy.extraParams中的相关数据
            */
            itemclick: function(tree, record, item, index, e, eOpts) {                   
                this.loadMainGridStore(tree,record);   
                return false;
            }
        },


        //区域列表刷新按钮事件
        "basetreegrid[xtype=smartControlSystem.irManage.roomInfoTree] button[ref=gridRefresh]": {
            click: function(btn) {
                this.refreshTreeStore(btn);
                return false;
            }
        },


       "basegrid[xtype=smartControlSystem.irManage.mainGrid] actioncolumn":{
         /*   setIrHighParam_Tab:function(data){
                this.openIrHighParamDetail(data.view,data.record);

            },*/
            setIrBaseParam_Tab: function(data) {            
                this.openIrBaseParamDetail(data.view,data.record);        

            },
        },

        "basegrid[xtype=smartControlSystem.irManage.mainGrid] button[ref=gridExport]": {
            beforeclick: function(btn) {
                this.doExport(btn);
                return false;
            }
        },


    },

      /*此方法用于打开红外设备管理的的基础数据界面，初始化数据；  
    */
    openIrBaseParamDetail:function(grid,record){
        var self = this;
       
        //得到组件
        var baseGrid = grid;
        var recordData=record.getData();

        var id =recordData.id;
        var termName=recordData.termName;
        var termTypeId = recordData.termTypeId;  // 11：红外设备
        var xItemType = "smartControlSystem.irManage.irBaseParamForm";          
       
      
        var basePanel = baseGrid.up("basepanel");
        var tabPanel = baseGrid.up("tabpanel[xtype=app-main]");
        var otherController = basePanel.otherController;    //关键：打开的tab页面的视图控制器
        if (!otherController)
            otherController = '';  

        //得到配置信息
        var funCode = basePanel.funCode;          //主界面的funCode
        var detCode = basePanel.detCode;               //打开的tab也的detCode标识，可自定指定，用于查找唯一组件
        var detLayout = basePanel.detLayout;            //打开的tab页的布局视图   
        var tabTitle = termName+"-红外设备基础参数"; 
        var tabItemId = funCode + "_gridIrBaseParam";    //命名规则：funCode+'_ref名称',确保不重复              
              
        //获取tabItem；若不存在，则表示要新建tab页，否则直接打开
        var tabItem=tabPanel.getComponent(tabItemId);
        if(!tabItem){

            //创建tabItem
            var tabItem = Ext.create({
                xtype:'container',
                title: tabTitle,
                scrollable :true, 
                itemId:tabItemId,            
                layout:'fit', 
                pkValue: id,      //主键值
            });
            tabPanel.add(tabItem); 

            //延迟放入到tab中
            setTimeout(function(){
                var item=Ext.widget("baseformtab",{
                    operType:"add",                            
                    controller:otherController,         //指定重写事件的控制器
                    funCode:funCode,                    //指定mainLayout的funcode
                    detCode:detCode,
                    detLayout:detLayout,                   
                    tabItemId:tabItemId,                //指定tab页的itemId
                    recordData:recordData,                    //保存一些需要默认值，提供给提交事件中使用
                    baseGrid:baseGrid,                     //保存funData数据，提供给提交事件中使用
                    items:[{
                        xtype:detLayout,
                        items:[{
                            xtype:xItemType
                        }]
                    }]
                }); 
              
                tabItem.add(item);  
              
                //处理打开界面之后，显示的初始数据
                var objForm = item.down("baseform");
                var formObj = objForm.getForm();     
                var baseParams =  objForm.baseFormData;
                baseParams.id = id;
                self.asyncAjax({                      
                    url: comm.get('baseUrl') + "/PtTerm/baseParam_read",
                    params: baseParams,                      
                        //回调代码必须写在里面
                        success: function(response) {
                            var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));                        
                            var valInt = null;
                            var controlVal = null;

                            if(data.tlvs){
                                for (var i = 0; i < data.tlvs.length; i++) {
                                    valInt = "tlvs[" + i + "].valInt";
                                    controlVal = formObj.findField(valInt);
                                    if (data.tlvs[i].tag == 0xC001) {
                                        var arr1 = data.tlvs[i].valStr.split("#");
                                        var arr2 = arr1[2].split("|");
                                        for (var i = 0; i < arr2.length; i++) {
                                            controlVal = formObj.findField("time" + i)
                                            if (controlVal != null) {
                                                controlVal.setValue(arr2[i]);
                                            }
                                        };
                                        if (arr1[1] != null) {
                                            controlVal = formObj.findField("type");
                                            if (controlVal != null) {
                                                controlVal.setValue(arr1[1]);
                                            }
                                        }
                                        if (arr1[3] != null) {
                                            for (var i = 0; i < arr1[3].length; i++) {
                                                controlVal=objForm.query("field[name=status" + i+"]");  
                                                if (arr1[3][i] == 0) {
                                                    controlVal[0].setValue(true);
                                                    controlVal[1].setValue(false);
                                                } else {
                                                    controlVal[0].setValue(false);
                                                    controlVal[1].setValue(true);
                                                }      
                                            };
                                        }
                                    } else if (controlVal != null) {
                                        controlVal.setValue(data.tlvs[i].valInt);
                                    }
                                }

                                if (data['notes'] != null && data[''] != '') {
                                    controlVal = formObj.findField("notes");
                                    if (controlVal != null) {
                                        controlVal.setValue(data['notes']);
                                    }
                                }
                            }                          
                        }
                    });                   

          },30);
                           
        }else if(tabItem.pkValue&&tabItem.pkValue!=id){     //判断是否点击的是同一条数据
            self.msgbox("您当前已经打开了一个编辑窗口了！");
            return;
        }

        tabPanel.setActiveTab(tabItem);   
           
    },
    doExport:function(btn){
        var self = this;
        var baseGrid = btn.up("basegrid");
        var mainlayout=baseGrid.up("panel[xtype=smartControlSystem.irManage.mainLayout]");

        var roominfotreegrid=mainlayout.down("panel[xtype=smartControlSystem.irManage.roomInfoTree]");
        var records = roominfotreegrid.getSelectionModel().getSelection();
        var roomId ="";
        var roomLeaf ="";
        if(records.length>0){
            roomId = records[0].get('id');
            roomLeaf = records[0].get("leaf");
            if(roomLeaf==true)
                roomLeaf="1";
            else
                roomLeaf="0";
        }
        var toolBar = btn.up("toolbar");
        var girdSearchTexts = toolBar.query("field[funCode=girdFastSearchText]");
        var termName ="";
        if(girdSearchTexts[0]!=null){
            termName = girdSearchTexts[0].getValue();
        }
        var title = "确定要导出红外设备管理的信息吗？";
        Ext.Msg.confirm('提示', title, function (btn, text) {
            if (btn == "yes") {
                Ext.Msg.wait('正在导出中,请稍后...', '温馨提示');
                var component = Ext.create('Ext.Component', {
                    title: 'HelloWorld',
                    width: 0,
                    height: 0,
                    hidden: true,
                    html: '<iframe src="' + comm.get('baseUrl') + '/PtTerm/doExportExcel?termName='+termName+'&roomId='+roomId+'&roomLeaf='+roomLeaf+'&termTypeId=11 "></iframe>',
                    renderTo: Ext.getBody()
                });

                var time = function () {
                    self.syncAjax({
                        url: comm.get('baseUrl') + '/PtTerm/checkExportEnd',
                        timeout: 1000 * 60 * 30,        //半个小时
                        //回调代码必须写在里面
                        success: function (response) {
                            data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));
                            if (data.success) {
                                Ext.Msg.hide();
                                self.msgbox(data.obj);
                                component.destroy();
                            } else {
                                if (data.obj == 0) {    //当为此值，则表明导出失败
                                    Ext.Msg.hide();
                                    self.Error("导出失败，请重试或联系管理员！");
                                    component.destroy();
                                } else {
                                    setTimeout(function () {
                                        time()
                                    }, 1000);
                                }
                            }
                        },
                        failure: function (response) {
                            Ext.Msg.hide();
                            Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);
                            component.destroy();
                        }
                    });
                };
                setTimeout(function () {
                    time()
                }, 1000);    //延迟1秒执行
            }
        });
       return false;
    },

    loadMainGridStore:function(tree,record){
        var mainLayout = tree.up("panel[xtype=smartControlSystem.irManage.mainLayout]");
        var funData = mainLayout.funData;
        mainLayout.funData = Ext.apply(funData, {
            roomId: record.get("id"),
            leaf : record.get("leaf"),//true: 房间 false:区域
            arealevel: record.get("level"),
        });
        // 加载房间的人员信息
        var mianGrid = mainLayout.down("panel[xtype=smartControlSystem.irManage.mainGrid]");
        var girdSearchTexts = mianGrid.query("field[funCode=girdFastSearchText]");
        var filter=new Array();
        filter.push({"type" : "string","value" : "11","field" : "termTypeId","comparison" : "="}); //红外设备
        if(girdSearchTexts[0].getValue()){
            filter.push({"type": "string", "value": girdSearchTexts[0].getValue(), "field": "termName", "comparison": ""})
        }
        filter = JSON.stringify(filter);
        //获取点击树节点的参数
        var roomId= record.get("id");
        var roomLeaf=record.get("leaf");
        if(roomLeaf==true)
            roomLeaf="1";
        else
            roomLeaf="0";

        var store = mianGrid.getStore();
        var proxy = store.getProxy();
        proxy.extraParams={
            roomId:roomId,
            roomLeaf:roomLeaf,
            filter:filter
        };
       // proxy.extraParams.roomId=roomId;
        store.loadPage(1);
    },


    refreshTreeStore:function(btn){        
        var baseGrid = btn.up("basetreegrid");
        var store = baseGrid.getStore();
        store.load(); //刷新父窗体的grid
        var mainlayout = btn.up("basepanel[xtype=smartControlSystem.irManage.mainLayout]");
        var mianGrid = mainlayout.down("basegrid[xtype=smartControlSystem.irManage.mainGrid]");
        var store = mianGrid.getStore();
        var proxy = store.getProxy();
        proxy.extraParams.roomId="";
        proxy.extraParams.roomLeaf="";
    }
});