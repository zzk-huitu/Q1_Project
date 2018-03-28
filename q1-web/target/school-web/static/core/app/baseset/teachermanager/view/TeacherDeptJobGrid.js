Ext.define("core.baseset.teachermanager.view.TeacherDeptJobGrid", {
    extend: "core.base.view.BaseGrid",
    alias: "widget.baseset.teachermanager.teacherdeptjobgrid",
    dataUrl: comm.get('baseUrl') + "/SysUser/userDeptJobList",
    model: 'com.zd.school.plartform.baseset.model.BaseUserdeptjob',
    title:"用户部门岗位",
    noPagging:true,
    al:false,
    remoteSort:false,
  
    tbar: [{
        xtype: 'button',
        text: '添加岗位',
        ref: 'gridAdd',
        iconCls: 'x-fa fa-plus-circle'
    }, {
        xtype: 'button',
        text: '解除岗位',
        ref: 'gridDelete',
        iconCls: 'x-fa fa-minus-circle'
    }, {
        xtype: 'button',
        text: '设置主部门',
        ref: 'setMasterDept',
        iconCls: 'x-fa fa-pencil-square'
    }],
    panelTopBar:null,
    panelButtomBar:null,
    //排序字段及模式定义
    defSort: [{
        property: 'orderIndex',
        direction: 'DESC'
    }],
    columns: { 
        defaults:{
            //flex:1,     //【若使用了 selType: "checkboxmodel"；则不要在这设定此属性了，否则多选框的宽度也会变大 】
            align:'center',
            titleAlign:"center"
        },
        items:[{
            text: "主键",
            dataIndex: "uuid",
            hidden: true
        }, {
            xtype: "rownumberer",
            flex:0,
            width: 50,
            text: '序号',
            align: 'center'
        },{
            text: "部门名称",
            dataIndex: "allDeptName",
            flex:1,
            minWidth:400,
            renderer: function(value, metaData,record) {          
                if(!value)
                    return record.get('deptName'); 
                else
                    return value;          
              
            } 
        }, {
            text: "岗位名称",
            dataIndex: "jobName",
            width:300
        },{
            text: "岗位级别",
            dataIndex: "jobLevel",
            width:200
        },{
            width:100,
            text: "是否主部门",
            dataIndex: "masterDept",
            renderer: function(value, metaData) {          
               if(value==1)
                    return "<span style='color:green'>是</span>";            
                else 
                    return "<span style='color:red'>否</span>";            
            }  
        }]
    }
});