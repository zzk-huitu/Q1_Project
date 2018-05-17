Ext.define("core.system.versioninfo.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.system.versioninfo.othercontroller',
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
        "baseformtab[detCode=versioninfo_detail] button[ref=formSave]": {
                click: function (btn) {
                    this.saveVersionInfo_Tab(btn);
                    return false;
                }
            },

    },

    saveVersionInfo_Tab:function(btn){
        var self=this;
        var basetab = btn.up('baseformtab');
        var tabPanel = btn.up("tabpanel[xtype=app-main]");
        var tabItemId = basetab.tabItemId;
        var tabItem = tabPanel.getComponent(tabItemId);   //当前tab页

        var funCode = basetab.funCode;      //mainLayout的funcode
        var detCode = basetab.detCode;      //detailLayout的funcode

        var detPanel = basetab.down("basepanel[funCode=" + detCode + "]");
        var objForm = detPanel.down("baseform[funCode=" + detCode + "]");

        var formObj = objForm.getForm();
        var funData = detPanel.funData;
        var pkName = funData.pkName;
        var pkField = formObj.findField(pkName);
        var params = self.getFormValue(formObj);   

        /*处理提交的参数*/

        //判断当前是保存还是修改操作
        var act = "doUpdateVer";
        if (formObj.isValid()) {
            formObj.submit({
                url: comm.get('baseUrl') + "/PtSysParameter" + "/" + act,
                //params: params,       //表单的参数会自动上传
                submitEmptyText: false,     //不提交表单为空值的数据
                waitMsg: '正在提交，请等待...',
                success: function (fp, action) {
                    formObj.reset();
                    self.Info("保存成功!");

                    var grid = basetab.funData.grid; //此tab是否保存有grid参数
                    var funCode = "versioninfo_main";
                    var basePanel = grid.up("basepanel[funCode=" + funCode +"]");
                    var tabPanel=grid.up("tabpanel[xtype=app-main]");

                    self.asyncAjax({
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
                        tabPanel.remove(tabItem);
                          }
                    });

                },
                failure: function (form, action) {
                    if (!Ext.isEmpty(action.result.obj))
                        self.Info(action.result.obj);
                }
            });

        } else {

            var errors = ["前台验证失败，错误信息："];
            formObj.getFields().each(function (f) {
                if (!f.isValid()) {
                    errors.push("<font color=red>" + f.fieldLabel + "</font>:" + f.getErrors().join(","));
                }
            });
            self.msgbox(errors.join("<br/>"));
        }
    },  
   
});