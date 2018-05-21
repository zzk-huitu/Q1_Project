Ext.define("core.smartControlSystem.irManage.controller.OtherController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.smartControlSystem.irManage.otherController',
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
       "baseformtab[detCode=irManage_detail] button[ref=formSave]": {
            beforeclick: function (btn) {                
                this.saveIrBaseParam_Tab(btn);
                return false;
            },
        },
    },

    saveIrBaseParam_Tab:function(btn){
        var self=this;
        var basetab = btn.up('baseformtab');
        var tabPanel = btn.up("tabpanel[xtype=app-main]");
        var tabItemId = basetab.tabItemId;
        var tabItem = tabPanel.getComponent(tabItemId);   //当前tab页
        var id = tabItem.id;

        var detCode = basetab.detCode;
        var detLayout = basetab.detLayout;  

        var objForm = basetab.down("baseform");
        var formObj = objForm.getForm();
        var params = self.getFormValue(formObj); 
       
        //批量参数
        params.termRadio=objForm.down("radiogroup[ref=termRadio]").getChecked()[0].inputValue;
        //设备类型
        //params.termTypeId= basetab.recordData.termTypeId;  //11红外
        
        //处理红外设备的表单数据
        var str = '1#';
        var time = '';
        var status = '';
        for (var i = 0; i < 4; i++) {
            time += params["time" + i] + "|";
        };
        time = time.substring(0, time.length - 1);
        for (var i = 0; i < 4; i++) {
          //使用表单获取值的方式去查找数据（貌似根据勾选的inputValue的1和0来得到的true或false）
            if(params["status" + i]==true){
                status+=1;
            }else{
                status+=0;
            }
        };
        str += params.type + "#" + time + "#" + status;
        params['tlvs[3].valStr']=str;  

        Ext.apply(params, objForm.baseFormData);
        
        
        //判断当前是保存还是修改操作
        if (formObj.isValid()) {

            var loading = new Ext.LoadMask(basetab, {
                msg: '正在提交，请稍等...',
                removeMask: true// 完成后移除
            });
            loading.show();

            self.asyncAjax({
                url: comm.get('baseUrl') + "/PtTerm/doSetBaseParam",
                params: params,
                //回调代码必须写在里面
                success: function (response) {
                    var data = Ext.decode(Ext.valueFrom(response.responseText, '{}'));

                    if (data.success) {

                        self.msgbox("提交成功!");
                        basetab.baseGrid.getStore().load();
                        loading.hide();
                        tabPanel.remove(tabItem);
                     
                    } else {
                        self.Error(data.obj);
                        loading.hide();
                    }
                },
                failure: function(response) {                   
                    Ext.Msg.alert('请求失败', '错误信息：\n' + response.responseText);
                    loading.hide();
                }
            });

        } else {
            var errors = ["前台验证失败，错误信息："];
            formObj.getFields().each(function (f) {
                if (!f.isValid()) {
                    errors.push("<font color=red>" + f.fieldLabel + "</font>：" + f.getErrors().join(","));
                }
            });
            self.msgbox(errors.join("<br/>"));
        }
    },
});