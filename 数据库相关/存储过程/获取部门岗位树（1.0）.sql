USE [NewQ1_test]
GO
/****** Object:  StoredProcedure [dbo].[P_PT_GetDeptJobTree]    Script Date: 04/13/2018 17:54:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO





-- =============================================
-- Author:		LUOYIBO
-- Create date: 2017-04-06
-- Description:	获取部门岗位树形列表，只列设置了岗位的部门
-- Paramemt:    
--         @userId: 用户Id 
-- =============================================
 ALTER PROC [dbo].[P_PT_GetDeptJobTree]
 AS
	WITH ctr_parent(deptId,nodeText, iconcls ,isLeaf,nodeLevel,treeIds,parentNode,orderIndex)
	AS
	(
		--起始条件
		SELECT deptId,nodeText, '' AS iconcls ,'false' AS isLeaf,nodeLevel,'' AS treeIds,parentNode,orderIndex FROM dbo.T_PT_Department 
			WHERE deptId IN (SELECT deptId FROM dbo.T_PT_DeptJob GROUP BY deptId)  --列出父节点查询条件
		UNION ALL
		--递归条件
		SELECT a.deptId,a.nodeText, '' AS iconcls ,'false' AS isLeaf,a.nodeLevel,'' AS treeIds,a.parentNode,a.orderIndex FROM T_PT_Department a INNER JOIN ctr_parent b          
		--执行递归，这里就要理解下了 
		ON a.deptId=b.parentNode
	) 
	SELECT * FROM (                   
	SELECT  DISTINCT * FROM ctr_parent 
	UNION ALL 
	SELECT  *
	FROM    ( SELECT TOP 99.999999 PERCENT
						a.deptJobId ,
						a.jobName ,
						'' AS iconcls ,
						'true' AS isLeaf ,
						99 AS  nodeLevel,
						'' AS treeIds,
						a.deptId ,
						a.jobType
			  FROM      dbo.T_PT_DeptJob a
						INNER JOIN dbo.T_PT_Job b ON a.jobId = b.jobId
			  ORDER BY  a.deptId ,
						a.jobType ASC
			) AS deptjob) AS job ORDER BY job.parentNode,job.orderIndex ASC






