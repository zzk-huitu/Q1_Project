Ext.define("core.baseset.studentmanager.view.DetailHtml", {
    extend: "Ext.Container",
    alias: "widget.baseset.studentmanager.detailhtml",
    layout: "form", 
    margin: '0 0 0 10',
    scrollable: true,
    width: '100%',
    items: [{
        xtype: 'container',
        ref: 'studentBaseInfo',
        tpl: new Ext.XTemplate(
             '<div class="trainClass_classInfo">',
            '<div class="trainClass_title">学生基本信息：</div>',
            '<div class="trainTeacher_teacherInfo" style="padding:0px;">',
                '<div class="trainTeacher_left">',
                    '<span class="zp" style="background-image: url({photo})"></span>',
                    '<span class="xm">{name}</span>',
                    '<span>性别：',
                    '<tpl if="sex==1">男<tpl elseif="sex==2">女</tpl></span>',
                    '<span>用户名：{userName}</span>',
                    '<span>学号：{userNumb}</span>', 
                '</div>',
                '<div class="trainTeacher_right">',
                    '<ul  style="padding: 10px">',
                        '<li style="font-size:14px;">曾用名：{userdName}</li>',
                        '<li style="font-size:14px;">姓名拼音：{spellName}</li>',
                        '<li style="font-size:14px;">英文姓名：{englishName}</li>',
                        
                        '<li style="font-size:14px;">出生日期：{birthDate}</li>',
                        '<li style="font-size:14px;">身份证号码：{identityNumber}</li>', 
                        '<li style="font-size:14px;">政治面貌：{politicalStatus}</li>',
                        
                        '<li style="font-size:14px;">学籍号：{studentCode}</li>',
                        '<li style="font-size:14px;">健康状况：{healthCode}</li>',
                        '<li style="font-size:14px;">血型：{bloodType}</li>',
                       
                        '<li style="font-size:14px;">国籍：{nationality}</li>',
                        '<li style="font-size:14px;">民族：{folkCode}</li>',
                        '<li style="font-size:14px;">账号状态：',
                        '<tpl if="state == 0">正常<tpl else>锁定</tpl></li>',
                        
                        '<li style="font-size:14px;">户口所在地：{administrativeAreaCode}</li>',
                        '<li style="font-size:14px;">户口性质：{accountTypeCode}</li>',
                        '<li style="font-size:14px;">现住址: {liveLocation}</li>',

                        '<li style="font-size:14px;">是否独生子女：{onlyChild}</li>',
                        '<li style="font-size:14px;">是否流动人口：{floatpopulation}</li>',                        
                        '<li style="font-size:14px;">特长：{speciality}</li>',  
                        
                        '<li style="font-size:14px;">创建时间：{createTime}</li>',
                        '<li style="font-size:14px;">创建人：{createUser}</li>',
                        '<div style="clear:both"></div>',
                    '</ul>',
                '</div>',
            '</div>',
            '</div>'
        ),
        data: {}
    }, {
        xtype: 'container',
        ref: "studentDetailInfo",
        tpl: new Ext.XTemplate(
            '<div class="trainClass_classTraineeInfo">',
            '<div class="trainClass_title">学生用户角色管理：</div>',
            '<ul class="trainClass_gridUl" style="max-height: 400px;">',
            '<li>',
            '<span style="width:20%" data-align="center">角色编码</span><span style="width:20%" data-align="center">角色名称</span>' ,
            '<span style="width:10%" data-align="center">是否系统角色</span><span style="width:30%" data-align="center">角色说明</span>' ,
            '{% if (values.rows.length == 0) %}',
            '<li style="width:100%;font-size: 14px;font-weight: 400;text-align: center;line-height: 100px;">此学生用户暂无角色...</li>',
            '{% if (values.rows.length == 0 ) return  %}',   //reutrun 表示不执行下面的了，在for里面可以使用break、continue
            '<tpl for="rows">',
            '<li>' ,
            '<span style="width: 20%;" >{roleCode}</span><span style="width: 20%;">{roleName}</span>',
            '<tpl if="issystem==0">',
            '<span style="width: 10%;color:red;" >否</span>' ,
            '<tpl else>',
            '<span style="width: 10%;color: green;" >是</span>' ,
            '</tpl>' ,
            '<span style="width: 30%;" >{remark}</span>',
            '</li>',
            '</tpl>',
            '<div style="clear:both"></div>',
            '</ul>',
            '</div>'
        ),
        data: {}
    }]
  
});