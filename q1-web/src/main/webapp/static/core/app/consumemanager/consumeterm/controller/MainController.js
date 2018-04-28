Ext.define("core.consumemanager.consumeterm.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.consumemanager.consumeterm.maincontroller',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },
    init: function() {
        var self = this;
        this.control({ 

        "basegrid[xtype=consumemanager.consumeterm.maingrid]  button[ref=gridBatchAdd]":{
            beforeclick: function(btn) {
                self.doGridBatchAdd(btn);            
                return false;
            }
        },

         "basegrid[xtype=consumemanager.consumeterm.maingrid]  button[ref=gridHigh]":{
            beforeclick: function(btn) {
                self.openHighParamDetail(btn);            
                return false;
            }
        },
         "basegrid[xtype=consumemanager.consumeterm.maingrid]  button[ref=gridBase]":{
            beforeclick: function(btn) {
                self.openBaseParamDetail(btn);            
                return false;
            }
        },


        //绑定列表事件
        "basegrid[xtype=consumemanager.consumeterm.maingrid] actioncolumn": {
            setHighParamClick_Tab: function(data) {
                self.openHighParamDetail(null,data.view,data.record,"edit");        
            },

            setBaseParamClick_Tab: function(data) {            
                self.openBaseParamDetail(null,data.view,data.record,"edit");        
            }
        },
      });
    },
    openHighParamDetail:function(btn,grid,record,cmd){
        var self = this;
        var recordData;
        var baseGrid=grid;
        var xItemType="";
        if(!baseGrid) { 
            baseGrid = btn.up("basegrid");  
            var rescords = baseGrid.getSelectionModel().getSelection();
          /*  if (rescords.length != 1) {
                this.msgbox("请选择一条数据！");
                return;
            }
            recordData = rescords[0].getData();*/
        }else{
            recordData =  record.getData();
        }
       //var id =recordData.id;
        var id ="222";
        var basePanel = baseGrid.up("basepanel");
        var tabPanel = baseGrid.up("tabpanel[xtype=app-main]");
        var otherController = basePanel.otherController;    //关键：打开的tab页面的视图控制器
        if (!otherController)
            otherController = '';  

        //得到配置信息
        var funCode = basePanel.funCode;          //主界面的funCode
        var detCode = "consumeterm_detail";               //打开的tab也的detCode标识，可自定指定，用于查找唯一组件
        var detLayout = "consumemanager.consumeterm.detaillayout";            //打开的tab页的布局视图   
        var tabTitle = ""+"-消费设备高级参数"; 
        var tabItemId = funCode + "_gridHighParam";    //命名规则：funCode+'_ref名称',确保不重复              
        xItemType = "consumemanager.consumeterm.consumehighparamform";
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
                itemPKV: id,      //主键值
            });
            tabPanel.add(tabItem); 

            //延迟放入到tab中
            setTimeout(function(){
                var item=Ext.widget("baseformtab",{
                    operType:"edit",                            
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
            });
        }else if(tabItem.itemPKV&&tabItem.itemPKV!=id){     //判断是否点击的是同一条数据
            self.msgbox("您当前已经打开了一个编辑窗口了！");
            return;
        }
        tabPanel.setActiveTab( tabItem); 

    },
    openBaseParamDetail:function(btn,grid,record,cmd){
        var self = this;
        var recordData;
        var baseGrid=grid;
        var xItemType="";
        if(!baseGrid) { 
            baseGrid = btn.up("basegrid");  
            var rescords = baseGrid.getSelectionModel().getSelection();
          /*  if (rescords.length != 1) {
                this.msgbox("请选择一条数据！");
                return;
            }
            recordData = rescords[0].getData();*/
        }else{
            recordData =  record.getData();
        }
        //var id =recordData.id;
        var id ="111";
        var basePanel = baseGrid.up("basepanel");
        var tabPanel = baseGrid.up("tabpanel[xtype=app-main]");
        var otherController = basePanel.otherController;    //关键：打开的tab页面的视图控制器
        if (!otherController)
            otherController = '';  

        //得到配置信息
        var funCode = basePanel.funCode;          //主界面的funCode
        var detCode = "consumeterm_detail";               //打开的tab也的detCode标识，可自定指定，用于查找唯一组件
        var detLayout = "consumemanager.consumeterm.detaillayout";            //打开的tab页的布局视图   
        var tabTitle = ""+"-消费设备基础参数"; 
        var tabItemId = funCode + "_gridBaseParam";    //命名规则：funCode+'_ref名称',确保不重复              
        xItemType = "consumemanager.consumeterm.consumebaseparamform";
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
                itemPKV: id,      //主键值
            });
            tabPanel.add(tabItem); 

            //延迟放入到tab中
            setTimeout(function(){
                var item=Ext.widget("baseformtab",{
                    operType:"edit",                            
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
            });
        }else if(tabItem.itemPKV&&tabItem.itemPKV!=id){     //判断是否点击的是同一条数据
            self.msgbox("您当前已经打开了一个编辑窗口了！");
            return;
        }
        tabPanel.setActiveTab( tabItem);   

    },
    doGridBatchAdd:function(btn){
        var self = this;
        var baseGrid = btn.up("basegrid");

        //得到组件
        var funCode = baseGrid.funCode; 
        var basePanel = baseGrid.up("basepanel[funCode=" + funCode +"]");
        //得到配置信息
        var funData = basePanel.funData;
        var detCode =  "consumeterm_batchadd";  
        var detLayout = "consumemanager.consumeterm.batchaddlayout";
        var defaultObj = funData.defaultObj;

        //关键：打开新的tab视图界面的控制器
        var otherController = basePanel.otherController;
        if (!otherController)
            otherController = '';

        //处理特殊默认值
        var insertObj = self.getDefaultValue(defaultObj);
        var popFunData = Ext.apply(funData, {
            grid: baseGrid
        });
        
        var winTitle = "批量添加设备";
        var width = 750;
        var height = 550;
        if (funData.width)
            width = funData.width;
        if (funData.height)
            height = funData.height;

        var win = Ext.create('Ext.window.Window', {
            iconCls:'x-fa fa-file-text',
          //  operType: cmd,
            width: width,
            height:height,
            title:winTitle,
            controller: otherController,
            funData: popFunData,
            funCode: detCode,
            insertObj: insertObj,
            items: [{
                xtype: detLayout
            }]
        });
        win.show();
    }


});