
--insert into T_PT_TempNo values (0,'idType')

/*1����ʼ���û�
exec dbo.P_PT_GetKeyId '01'
select * from T_PT_User  
select * from Q1_bolun.dbo.SYS_T_USER
--��������Ա
insert into T_PT_User(userId, createTime,createUser,isDelete,orderIndex,version,
	category,isSystem,state,userName,userPwd,schoolId,isHidden,
	userNumb,name,sex,rightType)
values('18041316114801000001',GETDATE(),'ROOT',0,1,1,
	0,1,1,'administrator','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','',1,
	'YC0001','��������Ա',1,0)
	
--ѧУ����Ա
insert into T_PT_User(userId, createTime,createUser,isDelete,orderIndex,version,
	category,isSystem,state,userName,userPwd,schoolId,isHidden,
	userNumb,name,sex,rightType)
values('18041316151201000002',GETDATE(),'ROOT',0,2,1,
	0,1,1,'schooladmin','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','',1,
	'YC0002','ѧУ����Ա',1,0)
*/

/*2����ʼ������
exec dbo.P_PT_GetKeyId '01'
select * from T_PT_Department
select * from Q1_bolun.dbo.BASE_T_ORG

--ROOT��
insert into T_PT_Department(deptId, createTime,createUser,isDelete,orderIndex,version,
	isLeaf,nodeCode,nodeLevel,nodeText,parentNode,deptType,isSystem,allDeptName,treeIds)
values('ROOT',GETDATE(),'ROOT',0,1,1,
	'0','ROOT',0,'ROOT','','01',1,'','ROOT')
	
--��ʾѧУ
insert into T_PT_Department(deptId, createTime,createUser,isDelete,orderIndex,version,
	isLeaf,nodeCode,nodeLevel,nodeText,parentNode,deptType,isSystem,allDeptName,treeIds)
values('18041316182901000001',GETDATE(),'ROOT',0,1,1,
	'0','001',0,'��ʾѧУ','ROOT','01',1,'��ʾѧУ','18041316182901000001')

--У����
insert into T_PT_Department(deptId, createTime,createUser,isDelete,orderIndex,version,
	isLeaf,nodeCode,nodeLevel,nodeText,parentNode,deptType,isSystem,allDeptName,treeIds)
values('18041316315001000002',GETDATE(),'ROOT',0,1,1,
	'0','002',1,'У����','18041316182901000001','02',1,'��ʾѧУ/У����','18041316182901000001,18041316315001000002')

--��ʱ����
insert into T_PT_Department(deptId, createTime,createUser,isDelete,orderIndex,version,
	isLeaf,nodeCode,nodeLevel,nodeText,parentNode,deptType,isSystem,allDeptName,treeIds)
values('18041316340401000003',GETDATE(),'ROOT',0,2,1,
	'0','003',1,'��ʱ����','18041316182901000001','03',1,'��ʾѧУ/��ʱ����','18041316182901000001,18041316340401000003')
*/

/*3����ʼ����λ
exec dbo.P_PT_GetKeyId '01'
select * from T_PT_Job
select * from Q1_bolun.dbo.BASE_T_JOB order by ORDER_INDEX asc

insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000001',GETDATE(),'ROOT',0,1,1,'01','У��');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000002',GETDATE(),'ROOT',0,2,1,'02','��У��');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000003',GETDATE(),'ROOT',0,3,1,'03','����');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000004',GETDATE(),'ROOT',0,4,1,'04','������');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000005',GETDATE(),'ROOT',0,5,1,'05','��ί���');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000006',GETDATE(),'ROOT',0,6,1,'06','����');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000007',GETDATE(),'ROOT',0,7,1,'07','ѧ���鳤');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000008',GETDATE(),'ROOT',0,8,1,'08','�꼶�鳤');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000009',GETDATE(),'ROOT',0,9,1,'09','�����鳤');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000010',GETDATE(),'ROOT',0,10,1,'10','������');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000011',GETDATE(),'ROOT',0,11,1,'11','ʵ��Ա');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000012',GETDATE(),'ROOT',0,12,1,'12','��ʦ');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000013',GETDATE(),'ROOT',0,13,1,'13','����Ա');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000014',GETDATE(),'ROOT',0,14,1,'14','ѧ��');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000015',GETDATE(),'ROOT',0,15,1,'15','���ݸ�����');
insert into T_PT_Job(jobId, createTime,createUser,isDelete,orderIndex,version,jobCode,jobName)
	values('18041316444501000016',GETDATE(),'ROOT',0,16,1,'16','������Ա');
*/

/*4����ʼ����ɫ
exec dbo.P_PT_GetKeyId '01'
select * from T_PT_Role
select * from Q1_bolun.dbo.SYS_T_ROLE order by ORDER_INDEX asc

insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000001',GETDATE(),'ROOT',0,1,1,1,1,'SUPERADMIN','��������Ա');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000002',GETDATE(),'ROOT',0,2,1,0,1,'SCHOOLADMIN','ѧУ����Ա');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000003',GETDATE(),'ROOT',0,3,1,0,1,'PRESIDENT','У��');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000004',GETDATE(),'ROOT',0,4,1,0,1,'VICEPRESIDENT','��У��');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000005',GETDATE(),'ROOT',0,5,1,0,1,'DIRECTOR','����');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000006',GETDATE(),'ROOT',0,6,1,0,1,'VICEDIRECTOR','������');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000007',GETDATE(),'ROOT',0,7,1,0,1,'GRADELEADER','�꼶�鳤');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000008',GETDATE(),'ROOT',0,8,1,0,1,'CLASSLEADER','������');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000009',GETDATE(),'ROOT',0,9,1,0,1,'TEACHER','��ʦ');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000010',GETDATE(),'ROOT',0,10,1,0,1,'STUDENT','ѧ��');
insert into T_PT_Role(roleId, createTime,createUser,isDelete,orderIndex,version,
	isHidden,isSystem,roleCode,roleName)
	values('18041316595401000011',GETDATE(),'ROOT',0,11,1,0,1,'TEST','����');
*/

/*5��Ϊ�û������ɫ
select * from T_PT_User
select * from T_PT_Role 
select * from T_PT_RoleUser
select  * from Q1_bolun.dbo.SYS_T_ROLEUSER
insert into T_PT_RoleUser values('18041316114801000001','18041316595401000001')
insert into T_PT_RoleUser values('18041316151201000002','18041316595401000002')
*/

/*6.��ʼ��ѧУ
select * from T_PT_Department
select * from T_PT_School
select * from Q1_bolun.dbo.BASE_T_SCHOOL
insert into T_PT_School(schoolId, createTime,createUser,isDelete,orderIndex,version,schoolCode,schoolName)
	values('18041316182901000001',GETDATE(),'ROOT',0,1,1,'ZHXY','��ʾѧУ');
*/

/*7.��ʼ��У��
select * from T_PT_Department
select * from T_PT_Campus
select * from Q1_bolun.dbo.BASE_T_CAMPUS
insert into T_PT_Campus(campusId, createTime,createUser,isDelete,orderIndex,version,schoolId,campusName)
	values('18041316315001000002',GETDATE(),'ROOT',0,1,1,'18041316182901000001','У����');
*/

/*7.��ʼ��������
select * from T_PT_Department
select * from T_PT_RoomArea
select * from Q1_bolun.dbo.BUILD_T_ROOMAREA
insert into T_PT_RoomArea(areaId, createTime,createUser,isDelete,orderIndex,version,
	isLeaf,nodeCode,nodeLevel,nodeText,parentNode,treeIds,areaType)
	values('18041316182901000001',GETDATE(),'ROOT',0,1,1,0,'',1,'��ʾѧУ','ROOT','18041316182901000001','01');

insert into T_PT_RoomArea(areaId, createTime,createUser,isDelete,orderIndex,version,
	isLeaf,nodeCode,nodeLevel,nodeText,parentNode,treeIds,areaType)
	values('18041316315001000002',GETDATE(),'ROOT',0,2,1,1,'',2,'У����','18041316182901000001','18041316182901000001,18041316315001000002','02');
*/

/*8.��ʼ���˵�
exec dbo.P_PT_GetKeyId '01'
select  * from T_PT_Menu
select  * from Q1_bolun.dbo.SYS_T_MENU
-----------�ű���ʼ------------
--------��һ��
select  * from T_PT_Menu
select  * from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE='ROOT' order by ORDER_INDEX

declare @id varchar(20), @index1 int,@orderIndex1 int,@bigIcon1 varchar(100),@smallIcon1 varchar(100),
	@menuCode1 varchar(20), @menuType1 varchar(10),@isLeaf1 bit,
	@nodeText1 varchar(16),@nodeLevel int, @parentNode1 varchar(20);
	
select @index1=COUNT(*) from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE='ROOT'

select top 1 @orderIndex1=ORDER_INDEX, @bigIcon1=BIG_ICON,@smallIcon1=SMALL_ICON,@menuCode1=MENU_CODE,
	@menuType1=MENU_TYPE,@isLeaf1=ISLEAF,@nodeText1=NODE_TEXT,@nodeLevel=NODE_LEVEL,@parentNode1=PARENT_NODE
from Q1_bolun.dbo.SYS_T_MENU   where PARENT_NODE='ROOT' order by ORDER_INDEX

update T_PT_TempNo set NoValue=1 where NoType='idType';
while @index1>0
begin
	select @id=[dbo].[F_PT_GetKeyId]('01') ;
	
	insert into T_PT_Menu(menuId, createTime,createUser,isDelete,orderIndex,version,
		isLeaf,nodeLevel,nodeText,parentNode,
		bigIcon,isHidden,isSystem,menuCode,menuType,smallIcon)
	values(@id,GETDATE(),'ROOT',0,@orderIndex1,1,@isLeaf1,1,@nodeText1,
		@parentNode1,@bigIcon1,0,0,@menuCode1,@menuType1,@smallIcon1);
	
	select top 1 @orderIndex1=ORDER_INDEX, @bigIcon1=BIG_ICON,@smallIcon1=SMALL_ICON,@menuCode1=MENU_CODE,
		@menuType1=MENU_TYPE,@isLeaf1=ISLEAF,@nodeText1=NODE_TEXT,@nodeLevel=NODE_LEVEL,@parentNode1=PARENT_NODE
	from Q1_bolun.dbo.SYS_T_MENU   where PARENT_NODE='ROOT'  and ORDER_INDEX>@orderIndex1
	order by ORDER_INDEX
	
	set @index1=@index1-1;
	update T_PT_TempNo set NoValue=NoValue+1 where NoType='idType';
end


-------�ڶ���
select  * from T_PT_Menu
select  *
 from Q1_bolun.dbo.SYS_T_MENU a where PARENT_NODE in (
 select MENU_ID from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE='ROOT'
)
order by UPDATE_TIME

declare @id2 varchar(20), @index2 int,@i2 int, @updateTime2 datetime,@bigIcon2 varchar(100),@smallIcon2 varchar(100),
	@menuCode2 varchar(20), @menuType2 varchar(10),@isLeaf2 bit,@menuTarge2 varchar(256),
	@nodeText2 varchar(16),@nodeLeve2 int, @parentNode2 varchar(20);
	
select @index2=COUNT(*) from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE in (
	 select MENU_ID from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE='ROOT'
)

select top 1 @updateTime2=UPDATE_TIME, @bigIcon2=BIG_ICON,@smallIcon2=SMALL_ICON,@menuCode2=MENU_CODE,
	@menuType2=MENU_TYPE,@isLeaf2=ISLEAF,@nodeText2=NODE_TEXT,@nodeLeve2=NODE_LEVEL,@menuTarge2=MENU_TARGET,
	@parentNode2=(select menuId from T_PT_Menu where menuCode = (select b.MENU_CODE from Q1_bolun.dbo.SYS_T_MENU b where b.MENU_ID=a.PARENT_NODE))
from Q1_bolun.dbo.SYS_T_MENU as a where PARENT_NODE  in (
	 select MENU_ID from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE='ROOT'
)order by UPDATE_TIME

update T_PT_TempNo set NoValue=1 where NoType='idType';
set @i2=1;
while @index2>0
begin

	select @id2=[dbo].[F_PT_GetKeyId]('01') ;
	
	insert into T_PT_Menu(menuId, createTime,createUser,isDelete,orderIndex,version,
		isLeaf,nodeLevel,nodeText,parentNode,
		bigIcon,isHidden,isSystem,menuCode,menuType,smallIcon,menuTarget)
	values(@id2,GETDATE(),'ROOT',0,@i2,1,@isLeaf2,2,@nodeText2,
		@parentNode2,@bigIcon2,0,0,@menuCode2,@menuType2,@smallIcon2,@menuTarge2);
	
	
	select top 1 @updateTime2=UPDATE_TIME, @bigIcon2=BIG_ICON,@smallIcon2=SMALL_ICON,@menuCode2=MENU_CODE,
		@menuType2=MENU_TYPE,@isLeaf2=ISLEAF,@nodeText2=NODE_TEXT,@nodeLeve2=NODE_LEVEL,@menuTarge2=MENU_TARGET,
		@parentNode2=(select menuId from T_PT_Menu where menuCode = (select b.MENU_CODE from Q1_bolun.dbo.SYS_T_MENU b where b.MENU_ID=a.PARENT_NODE))
	from Q1_bolun.dbo.SYS_T_MENU  as a where PARENT_NODE in (
		 select MENU_ID from Q1_bolun.dbo.SYS_T_MENU where PARENT_NODE='ROOT'
	) and UPDATE_TIME>@updateTime2
	order by UPDATE_TIME 
	
	set @index2=@index2-1;
	set @i2=@i2+1;
	update T_PT_TempNo set NoValue=NoValue+1 where NoType='idType';
end
------------�ű�����------------
--�����㣬 �ֶ����
*/


/*9.��ʼ�������ֵ��
exec dbo.P_PT_GetKeyId '01'
select [dbo].[F_PT_GetKeyId]('01') ;
select  * from T_PT_DataDict
select  * from Q1_bolun.dbo.BASE_T_DIC where ISDELETE=0

declare @id varchar(20), @index1 int,@i1 int,@creatTime datetime,@isLeaf1 bit,
	@nodeText1 varchar(16),@nodeLevel int, @parentNode1 varchar(20),@dicCode varchar(16),@dicType varchar(10);
	
select @index1=COUNT(*) from Q1_bolun.dbo.BASE_T_DIC where ISDELETE=0

select top 1 @creatTime=CREATE_TIME,@isLeaf1=ISLEAF,@nodeText1=NODE_TEXT,@nodeLevel=NODE_LEVEL,
	@parentNode1=ISNULL((select DIC_CODE from Q1_bolun.dbo.BASE_T_DIC where DIC_ID=a.PARENT_NODE),'ROOT'),@dicCode=DIC_CODE,@dicType=DIC_TYPE
from Q1_bolun.dbo.BASE_T_DIC as a where ISDELETE=0 order by CREATE_TIME

update T_PT_TempNo set NoValue=1 where NoType='idType';
set @i1=1;
while @index1>0
begin

	select @id=[dbo].[F_PT_GetKeyId]('01') ;
	
	insert into T_PT_DataDict(dictId, createTime,createUser,isDelete,orderIndex,version,
		isLeaf,nodeLevel,nodeText,parentNode,dicCode,dicType)	--physicalPath��ʱ��Ÿ�id
	values(@id,GETDATE(),'ROOT',0,@i1,1,@isLeaf1,1,@nodeText1,
		@parentNode1,@dicCode,@dicType);
	
	select top 1 @creatTime=CREATE_TIME,@isLeaf1=ISLEAF,@nodeText1=NODE_TEXT,@nodeLevel=NODE_LEVEL,
		@parentNode1=ISNULL((select DIC_CODE from Q1_bolun.dbo.BASE_T_DIC where DIC_ID=a.PARENT_NODE),'ROOT'),@dicCode=DIC_CODE,@dicType=DIC_TYPE
	from Q1_bolun.dbo.BASE_T_DIC  as a where ISDELETE=0 and CREATE_TIME>@creatTime
	order by CREATE_TIME
	
	set @index1=@index1-1;
	set @i1=@i1+1;
	update T_PT_TempNo set NoValue=NoValue+1 where NoType='idType';
end
--���¸�Id
update a set a.parentNode=(select dictId from T_PT_DataDict where dicCode=a.parentNode) from T_PT_DataDict a 
update a set a.parentNode='ROOT' from T_PT_DataDict a  where a.parentNode IS NULL
*/


/*10.��ʼ�������ֵ�����
exec dbo.P_PT_GetKeyId '01'
select [dbo].[F_PT_GetKeyId]('01') ;
select  * from T_PT_DataDictItem

select  ROW_NUMBER() over( order by DIC_ID asc,ITEM_CODE asc) rowNum,* into tempTable  from Q1_bolun.dbo.BASE_T_DICITEM where ISDELETE=0 
--select * from tempTable

declare @id varchar(20), @index1 int,@rowNum int,@dicId varchar(20),@itemCode varchar(20),@itemDesc varchar(256),@itemName varchar(16);
	
select @index1=COUNT(*) from tempTable

select top 1 @rowNum=rowNum,
	@dicId=(select DIC_CODE from Q1_bolun.dbo.BASE_T_DIC where DIC_ID=a.DIC_ID),
	@itemCode=ITEM_CODE,@itemDesc=ITEM_DESC,@itemName=ITEM_NAME
from tempTable a

update T_PT_TempNo set NoValue=1 where NoType='idType';

while @index1>0
begin

	select @id=[dbo].[F_PT_GetKeyId]('01') ;
	
	insert into T_PT_DataDictItem(dictItemId, createTime,createUser,isDelete,orderIndex,version,
		dictId,itemCode,itemDesc,itemName)	--physicalPath��ʱ��Ÿ�id
	values(@id,GETDATE(),'ROOT',0,@rowNum,1,@dicId,@itemCode,@itemDesc,@itemName)
	
	
	select top 1 @rowNum=rowNum,
		@dicId=(select DIC_CODE from Q1_bolun.dbo.BASE_T_DIC where DIC_ID=a.DIC_ID),
		@itemCode=ITEM_CODE,@itemDesc=ITEM_DESC,@itemName=ITEM_NAME
	from tempTable a where rowNum>@rowNum
	
	set @index1=@index1-1;
	update T_PT_TempNo set NoValue=NoValue+1 where NoType='idType';
end
--���¸�Id
update a set a.dictId=isnull((select dictId from T_PT_DataDict where dicCode=a.dictId),'') from T_PT_DataDictItem a 
drop table tempTable
*/
