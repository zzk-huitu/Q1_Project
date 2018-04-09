USE [Q1_test6]
GO

/****** Object:  View [dbo].[V_PT_UserRightDeptList]    Script Date: 04/08/2018 18:02:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




---=============================================
-- Author:		LUOYIBO
-- Create date: 2017-04-06
-- Alter date: 2017-05-25
-- Description:	用户有权限的所有部门
-- 字段说明:   99.999999 PERCENT 
-- 最新修改： 若父级部门有权限，则自动拥有子部门的权限。
-- 构建数据表说明:
		--BASE_T_ORG:部门信息
		--BASE_T_USERDEPTJOB:用户所在的部门岗位
		--SYS_T_DEPTRIGHT:用户指定的有权限的部门
		--SYS_T_DEPTJOBRIGHT:对部门岗位添加的部门权限
-- ==========================================
ALTER VIEW [dbo].[V_PT_UserRightDeptList]
AS

	select distinct A.*,B.USER_ID from BASE_T_ORG A,(			
		 --人员所在部门
		 SELECT a.DEPT_ID ,
				b.USER_ID
		 FROM   dbo.BASE_T_ORG a
				INNER JOIN BASE_T_USERDEPTJOB b ON b.DEPT_ID = a.DEPT_ID
		 WHERE  a.ISDELETE = 0 AND b.ISDELETE=0
		 UNION ALL                                                                     
		 --人员所在岗位主管的部门	
		 SELECT a.DEPT_ID ,
				b.USER_ID
		 FROM   dbo.BASE_T_ORG a
				INNER JOIN dbo.BASE_T_USERDEPTJOB b ON (a.SUPER_DEPT = b.DEPT_ID
					AND a.SUPER_JOB = b.JOB_ID)
		 WHERE  a.ISDELETE = 0 AND b.ISDELETE=0
		 UNION ALL 
		 --人员指定的有权限部门
		 SELECT a.DEPT_ID ,
				b.USER_ID
		 FROM   dbo.BASE_T_ORG a
				INNER JOIN dbo.SYS_T_DEPTRIGHT b ON a.DEPT_ID = b.DEPT_ID
		 WHERE  a.ISDELETE = 0 and b.ISDELETE=0
		 UNION ALL   
		 --配置的岗位权限（语句可能存在问题）
		SELECT a.DEPT_ID ,
		   b.USER_ID
		FROM   dbo.BASE_T_ORG a,
				 BASE_T_USERDEPTJOB b 
		WHERE  a.ISDELETE = 0
			AND b.ISDELETE = 0
			AND a.DEPT_ID IN ( SELECT   DEPT_ID
				   FROM     dbo.SYS_T_DEPTJOBRIGHT
				   WHERE    ISDELETE = 0 AND b.DEPTJOB_ID=DEPTJOB_ID )
	)B where A.ISDELETE=0 and A.TREE_IDS like '%'+B.DEPT_ID+'%'
		 

GO


