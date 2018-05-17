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
        text: "编辑配置",
        ref:"editDetail",
        scale: "medium"
    },{
        xtype: 'container',
        ref: 'versionBaseInfo',
        tpl: new Ext.XTemplate(
            '<div class="trainClass_classInfo">',
            '<div class="trainClass_title">图标信息：</div>',
            '<div class="versionInfo" style="padding:0px;">',
                '<div class="versionInfo_left">',
                    '<span class="logo" style="background-image: url(/static/core/resources/images/login/login_logo.png)"></span>',
                '</div>',

                '<div class="versionInfo_left">',
                    '<span class="logo" style="background-image: url(/static/core/resources/images/index_logo.png)"></span>',
                '</div>',

                '<div class="versionInfo_left">',
                    '<span class="logo" style="background-image: url(/static/core/resources/images/index_title.png)"></span>',
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
});