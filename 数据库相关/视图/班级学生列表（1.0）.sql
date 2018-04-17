USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_ClassStudentList]    Script Date: 04/16/2018 18:38:09 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER view [dbo].[V_PT_ClassStudentList]
as
select a.userId as userId,a.name as name,a.userNumb as userNumb,a.sex as sex,c.deptId as classId,c.nodeText as className,f.gradeId AS gradeId,f.gradeCode as gradeCode,f.gradeName as gradeName 
    from T_PT_User a 
	join T_PT_UseDeptJob b on a.userId=b.userId
	join T_PT_Department c on b.deptId=c.deptId
	join T_PT_Job d on b.jobId=d.jobId
	join T_PT_GradeClass e on e.classId=c.deptId
	join T_PT_Grade f on e.gradeId=f.gradeId
where a.isDelete=0 and b.isDelete=0 and c.isDelete=0 and d.isDelete=0 and e.isDelete=0 and f.isDelete=0 and
	a.category='2' and b.isMainDept=1 and c.deptType='05' and d.jobName='Ñ§Éú'





GO


