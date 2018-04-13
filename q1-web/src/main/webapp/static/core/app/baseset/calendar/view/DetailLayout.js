Ext.define("core.baseset.calendar.view.DetailLayout", {
    extend: "core.base.view.BasePanel",
    alias: 'widget.baseset.calendar.detaillayout',
    funCode: "calendarMain_detail",
    funData: {
        action: comm.get('baseUrl') + "/PtCalenderDetail", //请求Action
        pkName: "id",
        modelName: "com.yc.q1.model.base.pt.basic.PtCalenderDetail", //实体全路径
        defaultObj: {}
    },
    /*关联此视图控制器*/
    controller: 'baseset.calendar.detailcontroller',
    /*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
    items: [{
        xtype: "baseset.calendar.detailform"
    }]
})