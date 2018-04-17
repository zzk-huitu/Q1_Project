Ext.define("core.baseset.teachermanager.view.DetailHtml", {
    extend: "Ext.Container",
    alias: "widget.baseset.teachermanager.detailhtml",
    layout: "form", 
    margin: '0 0 0 10',
    scrollable: true,
    width: '100%',
    items: [{
        xtype: 'container',
        ref: 'teacherBaseInfo',
        tpl: new Ext.XTemplate(
            '<div class="trainClass_classInfo">',
            '<div class="trainClass_title">教师基本信息：</div>',
            '<div class="trainTeacher_teacherInfo" style="padding:0px;">',
                '<div class="trainTeacher_left" style="width: 200px;">',
                    '<span class="zp" style="background-image: url({photo})"></span>',
                    '<span class="xm">{name}</span>',
                    '<span>性别：',
                    '<tpl if="sex==1">男<tpl elseif="sex==2">女</tpl></span>',
                    '<span>用户名：{userName}</span>',
                    '<span>工号：{userNumb}</span>',
                '</div>',
                '<div class="trainTeacher_right">',
                    '<ul style="padding: 10px">',
                        '<li  style="font-size:14px;">电话：{mobilePhone}</li>',
                        '<li title={identityNumber} style="font-size:14px;">身份证号码：{identityNumber}</li>',
                        '<li style="font-size:14px;">电子邮件：{email}</li>',
                        
                        '<li style="font-size:14px;">编制：{compileTypeCode}</li>',
                        '<li style="font-size:14px;">学历：{degreeCode}</li>',
                        '<li style="font-size:14px;">政治面貌：{politicalStatus}</li>',
                        
                        '<li style="font-size:14px;">出生日期：{birthDate}</li>',
                        '<li style="font-size:14px;">来校年月：{comeSchoolYears}</li>',
                        '<li style="font-size:14px;">从教年月：{teachYears}</li>',
                       
                        '<li style="font-size:14px;">婚姻状况：{marriageCode}</li>',
                        '<li style="font-size:14px;">健康状况：{healthCode}</li>',
                        '<li style="font-size:14px;">血型：{bloodType}</li>',
                        
                        '<li style="font-size:14px;">国籍：{nationality}</li>',
                        '<li style="font-size:14px;">港澳台侨外：{foreignCode}</li>',
                        '<li style="font-size:14px;">民族：{folkCode}</li>',
                        
                        '<li style="font-size:14px;">信仰宗教：{religiousCode}</li>',
                        '<li style="font-size:14px;">户口所在地：{hkadr}</li>',
                        '<li style="font-size:14px;">户口性质：{AccountPropertyCode}</li>',
                        
                        
                        '<li style="font-size:14px;">档案编号：{fileNumber}</li>',
                        '<li style="font-size:14px;">档案文本：{textFiles}</li>',
                        '<li style="font-size:14px;">现住址: {liveLocation}</li>',
                        
                        '<li style="font-size:14px;">特长：{speciality}</li>',
                        '<li style="font-size:14px;">账号状态：',
                        '<tpl if="state == 0">正常<tpl else>锁定</tpl></li>',
                        '<li style="font-size:14px;">创建时间：{createTime}</li>',
                       
                        '<div style="clear:both"></div>',
                    '</ul>',
                '</div>',
            '</div>',
            '</div>'
        
        ),
        data: {}
    }, {
        xtype: 'container',
        ref: "teacherDetailInfo",
        tpl: new Ext.XTemplate(
            '<div class="trainClass_classTraineeInfo">',
            '<div class="trainClass_title">教师用户角色管理：</div>',
            '<ul class="trainClass_gridUl" style="max-height: 400px;">',
            '<li>',
            '<span style="width:20%" data-align="center">角色编码</span><span style="width:20%" data-align="center">角色名称</span>' ,
            '<span style="width:10%" data-align="center">是否系统角色</span><span style="width:30%" data-align="center">角色说明</span>' ,
            '{% if (values.rows.length == 0) %}',
            '<li style="width:100%;font-size: 14px;font-weight: 400;text-align: center;line-height: 100px;">此教师用户暂无角色...</li>',
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