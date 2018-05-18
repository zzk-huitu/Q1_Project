Ext.define("core.system.versioninfo.view.DetailHtml", {
    extend: "Ext.Container",
    alias: "widget.system.versioninfo.detailhtml",
    layout: "form", 
    margin: '0 0 0 10',
    scrollable: true,
    width: '100%',
    items: [{
        xtype: 'button',
        margin:"10 0 0 0 ",
        text: "编辑配置",
        ref:"editDetail",
        xtype: 'button',
        iconCls: 'x-fa fa-pencil-square'
        //scale: "medium"
    },{
        xtype: 'container',
        ref: 'versionBaseInfo',
        tpl: new Ext.XTemplate(
            '<div class="trainClass_classInfo"  style="background: #b4e0ff;margin: 10px 0px;">',
            '<div class="trainClass_title" style="color: #097db5;font-size: 14px;">LOG图片信息：</div>',
            '<div class="" style="padding:0px;">',
                     
                '<span style=" margin: 10px; display: block;width:65px;height:65px;background-image: url(/static/core/resources/images/index_logo.png)"></span>',
                
                '<span style="  margin: 10px;display: block;width:320px;height:55px;background-image: url(/static/core/resources/images/index_title.png)"></span>',

                '<span  style=" margin: 10px;display: block;width:500px;height:100px; background-image: url(/static/core/resources/images/login/login_logo.png)"></span>',
    
            '</div>',
            '</div>'
        ),
        data: {}
    },{
        xtype: 'container',
        ref: 'versionBaseInfo2',
        tpl: new Ext.XTemplate(
             '<div class="trainClass_classInfo" style="background: #b4e0ff;">',
            '<div class="trainClass_title" style="color: #097db5;font-size: 14px;">版本基础信息：</div>',
            '<div class="" style="padding:0px;">',

                '<div style="padding: 5px;color: #000;">',
                    '<span>客户名称：{clientName}</span>',
                    // '<span>参数名称：{clientNameName}</span>',
                    // '<span>参数编码：{clientNameCode}</span>',
                '</div>',

                '<div  style="padding: 5px;color: #000;">',
                    '<span>公司名称：{serviceName}</span>',
                    // '<span>参数名称：{serviceNameName}</span>',
                    // '<span>参数编码：{serviceNameCode}</span>',
                '</div>',

                '<div style="padding: 5px;color: #000;">',
                    '<span>版本名称：{varsionName}</span>',
                    // '<span>参数名称：{varsionNameName}</span>',
                    // '<span>参数编码：{varsionNameCode}</span>',
                '</div>',

            '</div>',
            '</div>'
        ),
        data: {}
    }],
});