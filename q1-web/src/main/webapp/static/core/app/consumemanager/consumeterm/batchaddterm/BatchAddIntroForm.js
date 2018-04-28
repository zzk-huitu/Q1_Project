Ext.define("core.consumemanager.consumeterm.batchaddterm.BatchAddIntroForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.consumemanager.consumeterm.batchaddterm.batchaddintroform",
    fieldDefaults: { // 统一设置表单字段默认属性
        labelSeparator: '：', // 分隔符
        msgTarget: 'qtip',
        labelAlign: "right",
        labelWidth: 120,     //label 的寬度
    },
    defaults:{
        width:'99%',
        margin:"20 40 20 55",
        xtype: "textfield"
    },
    items:[{
         xtype: "fieldset",
         title:'批量添加设备简介',
         layout:'form',
         width:'80%',
         height:400,
         style:{
            fontSize: '16px',
            color: '#C44444',
            fontWeight:400,
            border: '#097db5 1px solid',
        },
        items: [{
            xtype: "container",
               layout: "column", // 从左往右的布局
               items: [{
                columnWidth: 0.8,
                xtype: "label",
                html: "<div style='padding-left:120px;padding-top:40px;font-size:14px;line-height: 20px;text-align:left;color: rgb(196, 68, 68);'>"+
                "<br>提示：</br><br>1. 各种限制次数如填入0则表示不限制</br>"+
                "<br>2. 如需禁用消费限次，请取消勾选[启用消费限次]</br>"+
                "<br>3. 如同时启用日限次和餐限次功能；消费时的判断顺序是先判断日限次再判断餐限次</br></div>"
            }]
        }]

    }]
});