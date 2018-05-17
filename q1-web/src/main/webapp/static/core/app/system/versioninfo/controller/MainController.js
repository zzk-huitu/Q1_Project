Ext.define("core.system.versioninfo.controller.MainController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.system.versioninfo.maincontroller',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },
    init: function() {
    },
    control: {

        "container[xtype=system.versioninfo.detailhtml] ":{
            render:function(container){
                 //得到组件
                var funCode = "versioninfo_main";
                var basePanel = container.up("basepanel[funCode=" + funCode +"]");
                var tabPanel=container.up("tabpanel[xtype=app-main]");

                this.asyncAjax({
                    url: comm.get('baseUrl') + "/PtSysParameter/versionInfoList",
                    params: {
                        page: 1,
                        start: 0,
                        limit: 0,
                    },
                    success: function (response) {
                    var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));
                    var roleUserContainer = basePanel.down("container[ref=versionBaseInfo]");
                    var roleUserContainer2 = basePanel.down("container[ref=versionBaseInfo2]");
                    data.mainLogoPhoto = comm.get("virtualFileUrl")+"/"+data.mainLogo;
                    data.smallLogoPhoto = comm.get("virtualFileUrl")+"/"+data.smallLogo;
                    data.schoolLogoPhoto = comm.get("virtualFileUrl")+"/"+data.schoolLogo;  
                    roleUserContainer.setData(data);
                    roleUserContainer2.setData(data);
                    }
                });

            }
        },

        "container[xtype=system.versioninfo.detailhtml] [ref=editDetail]":{
            click:function(bth){
               this.openDetail_Tab(bth,"edit");
            }
        },
    },

    openDetail_Tab:function(btn, cmd) {
        var self = this;

        //得到组件
        var baseGrid=btn.up("container");
        if(baseGrid==null) { //如果找不到，就找treegrid
            baseGrid = btn.up("basetreegrid");  
            if(baseGrid==null){  //还找不到组件，结束执行。
                self.msgbox("找不到组件！");
                return;
            }
        }

        //得到组件
        var funCode = "versioninfo_main"; //creditrule_main
        var basePanel = baseGrid.up("basepanel[funCode=" + funCode +"]");
        var tabPanel=baseGrid.up("tabpanel[xtype=app-main]");   //获取整个tabpanel

        //得到配置信息
        var funData = basePanel.funData;
        var detCode =  basePanel.detCode;  
        var detLayout = basePanel.detLayout;
        var defaultObj = funData.defaultObj;
                
        //关键：打开新的tab视图界面的控制器
        var otherController = basePanel.otherController;
        if (!otherController)
            otherController = '';


        //处理特殊默认值
        // var insertObj = self.getDefaultValue(defaultObj);
        var roleUserContainer = basePanel.down("container[ref=versionBaseInfo]");
        var insertObj = roleUserContainer.getData();
        
        var popFunData = Ext.apply(funData, {
            grid: baseGrid
        });

        //本方法只提供班级详情页使用
        var tabTitle = funData.tabConfig.addTitle;
        //设置tab页的itemId
        var tabItemId=funCode+"_gridAdd";     //命名规则：funCode+'_ref名称',确保不重复
        var pkValue= null;
        var operType = cmd;    // 只显示关闭按钮
        var itemXtype=[{
            xtype:detLayout,                        
            funCode: detCode             
        }];
                
        tabTitle = funData.tabConfig.editTitle;
        tabItemId=funCode+"_gridEdit"; 
                       
        //获取tabItem；若不存在，则表示要新建tab页，否则直接打开
        var tabItem=tabPanel.getComponent(tabItemId);
        if(!tabItem){
            //创建一个新的TAB
            tabItem=Ext.create({
                xtype:'container',
                title: tabTitle,
                scrollable :true, 
                itemId:tabItemId,      //保存主键值
                layout:'fit', 
            });
            tabPanel.add(tabItem); 

            //延迟放入到tab中
            setTimeout(function(){
                //创建组件
                var item=Ext.widget("baseformtab",{
                    operType:operType,                            
                    controller:otherController,         //指定重写事件的控制器
                    funCode:funCode,                    //指定mainLayout的funcode
                    detCode:detCode,                    //指定detailLayout的funcode
                    tabItemId:tabItemId,                //指定tab页的itemId
                    insertObj:insertObj,    
                    funData: popFunData,                //保存funData数据，提供给提交事件中使用
                    items:itemXtype
                }); 
                tabItem.add(item);  
               
                //将数据显示到表单中（或者通过请求ajax后台数据之后，再对应的处理相应的数据，显示到界面中） 
                var objDetForm = item.down("baseform[funCode=" + detCode + "]");
                var formDeptObj = objDetForm.getForm();
                self.setFormValue(formDeptObj, insertObj);
                //显示照片
                
                objDetForm.down('image[ref=photoImage1]').setSrc("/static/core/resources/images/login/login_logo.png");
                objDetForm.down('image[ref=photoImage2]').setSrc("/static/core/resources/images/index_logo.png");
                objDetForm.down('image[ref=photoImage3]').setSrc("/static/core/resources/images/index_title.png");
                
            },30);
                           
        }else if(tabItem.itemPKV&&tabItem.itemPKV!=pkValue){     //判断是否点击的是同一条数据
            self.Warning("您当前已经打开了一个编辑窗口了！");
            return;
        }

        tabPanel.setActiveTab( tabItem);        
    },
 
});