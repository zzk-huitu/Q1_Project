Ext.define("core.baseset.calendar.view.CalendarDetailLayout", {
	extend: "core.base.view.BasePanel",
	alias: 'widget.baseset.calendar.calendardetaillayout',
	funCode: "calendar_detail",
	funData: {
		action: comm.get('baseUrl') + "/PtCalender", //请求Action
		pkName: "id",
		defaultObj: {}
	},
	/*关联此视图控制器*/
	controller: 'baseset.calendar.detailcontroller',
	/*设置最小宽度，并且自动滚动*/
    minWidth:1000,
    scrollable:true,
	items: [{
		xtype: "baseset.calendar.calendarform"
	}]
});