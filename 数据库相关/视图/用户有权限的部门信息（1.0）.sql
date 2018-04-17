USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_UserRightDeptList]    Script Date: 04/16/2018 18:40:42 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[V_PT_UserRightDeptList]
AS

	select distinct A.*,B.userId from T_PT_Department A,(			
		 --��Ա���ڲ���
		 SELECT a.deptId ,
				b.userId
		 FROM   dbo.T_PT_Department a
				INNER JOIN T_PT_UseDeptJob b ON b.deptId = a.deptId
		 WHERE  a.isDelete = 0 AND b.isDelete=0
		 UNION ALL                                                                     
		 --��Ա���ڸ�λ���ܵĲ���	
		 SELECT a.deptId ,
				b.userId
		 FROM   dbo.T_PT_Department a
				INNER JOIN dbo.T_PT_UseDeptJob b ON (a.superDept = b.deptId
					AND a.superJob = b.jobId)
		 WHERE  a.isDelete = 0 AND b.isDelete=0
		 UNION ALL 
		 --��Աָ������Ȩ�޲���
		 SELECT a.deptId ,
				b.userId
		 FROM   dbo.T_PT_Department a
				INNER JOIN dbo.T_PT_UserDeptRight b ON a.deptId = b.deptId
		 WHERE  a.isDelete = 0 and b.isDelete=0
		-- UNION ALL   
		 --���õĸ�λȨ�ޣ������ܴ������⣩
		--SELECT a.deptId ,
		   --b.userId
		--FROM   dbo.T_PT_Department a,
			--	 T_PT_UseDeptJob b 
		--WHERE  a.isDelete = 0
		--	AND b.isDelete = 0
		--	AND a.deptId IN ( SELECT   deptId
			--	   FROM     dbo.SYS_T_DEPTJOBRIGHT
			--	   WHERE    isDelete = 0 AND b.deptJobId=deptJobId )
	)B where A.isDelete=0 and A.treeIds like '%'+B.deptId+'%'
		 


GO


