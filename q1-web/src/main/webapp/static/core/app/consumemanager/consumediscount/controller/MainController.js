Ext.define("core.consumemanager.consumediscount.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.consumemanager.consumediscount.maincontroller',
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
           "basegrid[xtype=consumemanager.consumediscount.maingrid] button[ref=discountTermBing]": {
            beforeclick: function(btn) {
                this.doDiscountTermBing(btn);
                return false;
            }
        },

    });
},

     doDiscountTermBing:function(btn){
        var self = this;
        var baseGrid = btn.up("basegrid");

        //得到组件
        var funCode = baseGrid.funCode; 
        var basePanel = baseGrid.up("basepanel[funCode=" + funCode +"]");
        var tabPanel=baseGrid.up("tabpanel[xtype=app-main]");   //获取整个tabpanel

        //得到配置信息
        var funData = basePanel.funData;
        var detCode =  "consumetermmanager_main";  
        var detLayout = "public.consumetermmanager.consumetermmanagerlayout";
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
        //获取主键值
        var pkName = funData.pkName;
        var pkValue='bing';
        
        var tabTitle = "消费折扣设备绑定";
        var tabItemId = funCode+"_gridTermBing"; 

        //获取tabItem；若不存在，则表示要新建tab页，否则直接打开
        var tabItem=tabPanel.getComponent(tabItemId);
        if(!tabItem){
            //创建一个新的TAB
            tabItem=Ext.create({
                xtype:'container',
                title: tabTitle,
                //iconCls: 'x-fa fa-clipboard',
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
                    controller:otherController,         //指定重写事件的控制器
                    funCode:funCode,                    //指定mainLayout的funcode
                    detCode:detCode,                    //指定detailLayout的funcode
                    tabItemId:tabItemId,                //指定tab页的itemId
                    insertObj:insertObj,                //保存一些需要默认值，提供给提交事件中使用
                    funData: popFunData,                //保存funData数据，提供给提交事件中使用
                    items:[{
                        xtype:detLayout,                        
                        funCode: detCode             
                    }]
                }); 
                tabItem.add(item);  
            },30);
            
        }else if(tabItem.itemPKV&&tabItem.itemPKV!=pkValue){     //判断是否点击的是同一条数据
            self.Warning("您当前已经打开了一个编辑窗口了！");
            return;
        }
        tabPanel.setActiveTab( tabItem);     

     },

});