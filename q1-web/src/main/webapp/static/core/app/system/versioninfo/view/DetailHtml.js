Ext.define("core.system.versioninfo.view.DetailHtml", {
    extend: "Ext.Container",
    alias: "widget.system.versioninfo.detailhtml",
    layout: "form", 
    margin: '0 0 0 10',
    scrollable: true,
    width: '100%',
    items: [{
        xtype: 'button',
        margin:"10 0 0 10 ",
        id: "btn2",
        text: "编辑",
        ref:"editDetail",
        scale: "medium"
    },{
        xtype: 'container',
        ref: 'versionBaseInfo',
        tpl: new Ext.XTemplate(
             '<div class="trainClass_classInfo">',
            '<div class="trainClass_title">版本图标信息：</div>',
            '<div class="versionInfo" style="padding:0px;">',
                '<div class="versionInfo_left">',
                    '<span class="logo" style="background-image: url({mainLogo})"></span>',
                    '<span>{mainLogoName}</span>',
                    // '<span>参数编码：{mainLogoCode}</span>',
                '</div>',

                '<div class="versionInfo_left">',
                    '<span class="logo" style="background-image: url({smallLogo})"></span>',
                    '<span>{smallLogoName}</span>',
                    // '<span>参数编码：{smallLogoCode}</span>',
                '</div>',

                '<div class="versionInfo_left">',
                    '<span class="logo" style="background-image: url({schoolLogo})"></span>',
                    '<span>{schoolLogoName}</span>',
                    // '<span>参数编码：{schoolLogoCode}</span>',
                '</div>',

            '</div>',
            '</div>'
        ),
        data: {}
    },{
        xtype: 'container',
        ref: 'versionBaseInfo2',
        tpl: new Ext.XTemplate(
             '<div class="trainClass_classInfo">',
            '<div class="trainClass_title">版本基础信息：</div>',
            '<div class="versionInfo" style="padding:0px;">',

                '<div class="versionInfo_down">',
                    '<span>客户名称：{clientName}</span>',
                    // '<span>参数名称：{clientNameName}</span>',
                    // '<span>参数编码：{clientNameCode}</span>',
                '</div>',

                '<div class="versionInfo_down">',
                    '<span>公司名称：{serviceName}</span>',
                    // '<span>参数名称：{serviceNameName}</span>',
                    // '<span>参数编码：{serviceNameCode}</span>',
                '</div>',

                '<div class="versionInfo_down">',
                    '<span>版本名称：{varsionName}</span>',
                    // '<span>参数名称：{varsionNameName}</span>',
                    // '<span>参数编码：{varsionNameCode}</span>',
                '</div>',

            '</div>',
            '</div>'
        ),
        data: {}
    }],
  
initComponent: function() {

        if(!this.store){
            this.store = Ext.create('Ext.data.Store', {
                pageSize: this.storePageSize,
                remoteSort: this.remoteSort,
                autoLoad: this.al,
                model: factory.ModelFactory.getModelByName(this.model, "checked").modelName,
                sorters: this.defSort,
                proxy: {
                    type: 'ajax',
                    url: this.dataUrl,
                    extraParams: this.extParams,
                    reader: {
                        type: 'json',
                        rootProperty: 'rows',
                        totalProperty: 'totalCount'
                    },
                    writer: {
                        type: 'json'
                    }
                },
                listeners:{
                    load:function( store , records , successful , operation , eOpts ){
                                        
                        //(处理服务器登录超时的解决方式)若为false，则表明返回的数据不是proxy指定的格式；则弹出提示
                        if(successful==false) {    
                           
                            if(operation.getResponse()==null){      //请求无响应出错的时候      
                                return;
                            }

                            var result=Ext.decode(Ext.valueFrom(  operation.getResponse().responseText, '{}')); 
                            var msg=result.obj;
                            if(!msg||typeof(msg) != "string")
                                msg="请求失败，请刷新页面重试！";
                                                    
                            Ext.MessageBox.show({
                                title: "警告",
                                msg: msg,
                                buttons: Ext.MessageBox.OK,
                                icon: Ext.MessageBox.WARNING,
                                fn: function(btn) {
                                    location.reload()       
                                }
                            });
                            
                        }                    
                    }    
                }
            });

             //处理store分组的时候，但是autoLoad失效，会立即加载
            if(this.defGroup!=null){
                this.store.setGroupField(this.defGroup);
            }
        }

        var columns = new Array();

        // var columnIitems = this.columns.items;  //取items里的数据，现在多了个配置项defaults
        // if(!columnIitems)
        //     columnIitems=this.columns;          //若没有，则取columns
        
        // Ext.each(columnIitems, function(col) {         
        //     //字典项转换
        //     if (col.columnType == "basecombobox" || (col.field && col.field.xtype && col.field.xtype == "basecombobox")) {
        //         col.renderer = function(value, data, record) {                  
        //             var val = value;
        //             var ddCode = col.ddCode;
        //             var ddItem = factory.DDCache.getItemByDDCode(ddCode);
        
        //             for (var i = 0; i < ddItem.length; i++) {
        //                 var ddObj = ddItem[i];
        //                 var displayField = 'itemName';
        //                 var valueField = 'itemCode';
        //                 if (col.field && col.field.displayField) {
        //                     displayField = column.field.displayField;
        //                 } else if (col.displayField) {
        //                     displayField = col.displayField;
        //                 }
        //                 if (col.field && col.field.valueField) {
        //                     displayField = col.field.valueField;
        //                 } else if (col.displayField) {
        //                     displayField = col.displayField;
        //                 }
        //                 if (value == ddObj[valueField]) {
        //                     val = ddObj[displayField];
        //                     break;
        //                 }
        //             }
        //             return val;
        //         }
        //     }
        //     columns.push(col);
        // });

        this.callParent(arguments);
    }
});