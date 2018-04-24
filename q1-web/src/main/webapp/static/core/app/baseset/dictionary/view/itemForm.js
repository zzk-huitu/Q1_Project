var isSystem = Ext.create('Ext.data.Store', {
            fields: ['isSystem', 'name'],
            data : [
                {"isSystem":'false', "name":"否"},
                {"isSystem":'true', "name":"是"}
            ]
        })
Ext.define("core.baseset.dictionary.view.ItemForm", {
    extend: "core.base.view.BaseForm",
    alias: "widget.baseset.dictionary.itemform",
    layout: "form",
    align: "left",
    frame: false,
    fieldDefaults: { //统一设置表单字段默认属性
        labelSeparator: '：', //分隔符
        labelWidth: 100, //标签宽度
        msgTarget: 'qtip',
        width: 300
    },
    items: [{
        xtype: "textfield",
        fieldLabel: "主键",
        name: "id",
        hidden: true
    }, /*{
        xtype: "textfield",
        fieldLabel: "排序号",
        name: "orderIndex",
        hidden: true
    }, */{
        xtype: "textfield",
        fieldLabel: "字典ID",
        name: "dictId",
        hidden: true
    }, {
        xtype: "textfield",
        fieldLabel: "字典项名称",
        name: "dicName",
        readOnly: true
    }, {
        beforeLabelTextTpl: comm.get('required'),
        xtype: "textfield",
        fieldLabel: "字典项名称",
        name: "itemName",
        allowBlank: false,
        emptyText: '请输入字典项名称',
        blankText: "字典项不能为空",
        maxLength: 128
    }, {
        beforeLabelTextTpl: comm.get('required'),
        xtype: "textfield",
        name: 'itemCode',
        fieldLabel: '字典项编码',
        allowBlank: false,
        emptyText: '请输入字典项编码',
        blankText: "字典编码不能为空",
        maxLength: 16
    }, {
        beforeLabelTextTpl: comm.get('required'),
        allowBlank: false,
        blankText: "排序号不能为空",
        fieldLabel: '排序号',
        name: "orderIndex",
        //xtype: "numberfield",
        emptyText: "同级别字典项的显示顺序",
    }, /*{
        beforeLabelTextTpl: "",
        xtype: "textfield",
        name: 'itemDesc',
        fieldLabel: '字典项说明',
        allowBlank: true,
        emptyText: '请输入字典项说明',
        blankText: "字典项说明不能为空",
        maxLength: 128
    }*/{
            beforeLabelTextTpl: comm.get('required'),
            xtype: "combobox",
            itemId:'isSystemCombo',
            store: isSystem,
            fieldLabel: "是否是系统",
            name: "isSystem",
            queryMode: 'local',
            displayField: 'name',
            valueField: 'isSystem',
            value:'false',
            editable:false
        }]
});