USE [Q1_test6]
GO

/****** Object:  View [dbo].[V_PT_ClassStudentList]    Script Date: 04/08/2018 17:59:25 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO





ALTER view [dbo].[V_PT_ClassStudentList]
as
select a.USER_ID as userId,a.XM as xm,a.USER_NUMB as userNumb,a.XBM as xbm,c.DEPT_ID as classId,c.NODE_TEXT as className,f.GRAI_ID AS gradeId,f.GRADE_CODE as gradeCode,f.GRADE_NAME as gradeName from SYS_T_USER a 
	join BASE_T_USERDEPTJOB b on a.USER_ID=b.USER_ID
	join BASE_T_ORG c on b.DEPT_ID=c.DEPT_ID
	join BASE_T_JOB d on b.JOB_ID=d.JOB_ID
	join JW_T_GRADECLASS e on e.CLAI_ID=c.DEPT_ID
	join JW_T_GRADE f on e.GRAI_ID=f.GRAI_ID
where a.ISDELETE=0 and b.ISDELETE=0 and c.ISDELETE=0 and d.ISDELETE=0 and e.ISDELETE=0 and f.ISDELETE=0 and
	a.CATEGORY='2' and b.MASTER_DEPT=1 and c.DEPT_TYPE='05' and d.JOB_NAME='Ñ§Éú'




GO


