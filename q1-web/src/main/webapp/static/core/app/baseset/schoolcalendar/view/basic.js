Ext.Loader.setConfig({
    enabled: true,
    disableCaching: false,
    paths: {
        'Extensible': "/static/calendar/src",
        "Extensible.example": "/static/calendar/examples"
    }
});
Ext.require(['Extensible.calendar.data.MemoryEventStore', 'Extensible.calendar.CalendarPanel', 'Extensible.example.calendar.data.Events']);
Ext.onReady(function() {
    var eventStore = Ext.create('Extensible.calendar.data.EventStore', {
        autoLoad: true,
        autoSync: false,
        proxy: {
            type: 'ajax',
            url: '/PtSchoolCalendar/list',
            noCache: false,
            reader: {
                type: 'json',
                root: 'calendars'
            },
            writer: {
                type: 'json',
                nameProperty: 'calendars'
            }
        }
       // data: Extensible.example.calendar.data.Events.getData()
    });
    // 去除源码中store同步，改为手动AJAX添加
    Extensible.calendar.view.AbstractCalendar.override({
        save: function() {
            //if(!this.store.autoSync){
            //    this.store.sync();
            //}
            // 关闭窗口
            if (Ext.getCmp('ext-cal-editwin')) {
                Ext.getCmp('ext-cal-editwin').hide();
            }
        }
    });

    Ext.create('Extensible.calendar.CalendarPanel', {
        eventStore: eventStore,
        renderTo: 'SchoolCalendar',
        title: '校历信息',
        
        //width: 700,
        width: "100%",
        height: "100%",
        listeners: {
            'eventadd': function(the, record) {
                //alert('INSERT');
                save(record, '/PtSchoolCalendar/doAdd', '保存');
            },
            'eventdelete': function(the, record, el) {
                //alert('DELETE');
                save(record, '/PtSchoolCalendar/doDelete', '删除');
            },
            'eventupdate': function(the, record) {
                //alert('UPDATE')
                save(record, '/PtSchoolCalendar/doUpdate', '修改');
            }
        }
    });

    function conParam(data) {
        return {
            'title': data.Title,
            'start': data.StartDate,
            'end': data.EndDate,
            'loc': data.Location,
            'notes': data.Notes,
            'ad': data.IsAllDay,
            'url': data.Url,
            'rem': data.Reminder,
            'id': data.EventId
        };
    }

    function save(record, url, msg) {
        var params = conParam(record.data);
        Ext.Ajax.request({
            method: 'POST',
            url: url,
            params: params,
            success: function(response) {
                var result=eval('(' + response.responseText + ')');
                if (result.success) {
                    Ext.Msg.alert('系统提示', msg + '成功！');
                    eventStore.load();
                    eventStore.sync();
                }else{
                    Ext.Msg.alert('系统提示', result.obj);
                }
            },
            failure: function() {
                Ext.Msg.alert('系统提示', '系统异常！');
            }
        });

    }
});