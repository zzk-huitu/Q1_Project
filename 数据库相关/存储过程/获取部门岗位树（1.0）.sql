USE [Q1_test6]
GO

/****** Object:  StoredProcedure [dbo].[P_PT_GetDeptJobTree]    Script Date: 04/08/2018 15:56:42 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




-- =============================================
-- Author:		LUOYIBO
-- Create date: 2017-04-06
-- Description:	��ȡ���Ÿ�λ�����б�ֻ�������˸�λ�Ĳ���
-- Paramemt:    
--         @userId: �û�Id 
-- =============================================
 ALTER PROC [dbo].[P_PT_GetDeptJobTree]
 AS
	WITH ctr_parent(DEPT_ID,NODE_TEXT, iconcls ,ISLEAF,NODE_LEVEL,TREE_IDS,PARENT_NODE,ORDER_INDEX)
	AS
	(
		--��ʼ����
		SELECT DEPT_ID,NODE_TEXT, '' AS iconcls ,'false' AS ISLEAF,NODE_LEVEL,'' AS TREE_IDS,PARENT_NODE,ORDER_INDEX FROM dbo.BASE_T_ORG 
			WHERE DEPT_ID IN (SELECT DEPT_ID FROM dbo.BASE_T_DEPTJOB GROUP BY DEPT_ID)  --�г����ڵ��ѯ����
		UNION ALL
		--�ݹ�����
		SELECT a.DEPT_ID,a.NODE_TEXT, '' AS iconcls ,'false' AS ISLEAF,a.NODE_LEVEL,'' AS TREE_IDS,a.PARENT_NODE,a.ORDER_INDEX FROM BASE_T_ORG a INNER JOIN ctr_parent b          
		--ִ�еݹ飬�����Ҫ������� 
		ON a.DEPT_ID=b.PARENT_NODE
	) 
	SELECT * FROM (                   
	SELECT  DISTINCT * FROM ctr_parent 
	UNION ALL 
	SELECT  *
	FROM    ( SELECT TOP 99.999999 PERCENT
						a.DEPTJOB_ID ,
						a.JOB_NAME ,
						'' AS iconcls ,
						'true' AS ISLEAF ,
						99 AS  NODE_LEVEL,
						'' AS TREE_IDS,
						a.DEPT_ID ,
						a.JOB_TYPE
			  FROM      dbo.BASE_T_DEPTJOB a
						INNER JOIN dbo.BASE_T_JOB b ON a.JOB_ID = b.JOB_ID
			  ORDER BY  a.DEPT_ID ,
						a.JOB_TYPE ASC
			) AS deptjob) AS job ORDER BY job.PARENT_NODE,job.ORDER_INDEX ASC





GO


