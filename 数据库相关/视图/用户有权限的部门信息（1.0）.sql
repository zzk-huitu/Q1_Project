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
-- Description:	�û���Ȩ�޵����в���
-- �ֶ�˵��:   99.999999 PERCENT 
-- �����޸ģ� ������������Ȩ�ޣ����Զ�ӵ���Ӳ��ŵ�Ȩ�ޡ�
-- �������ݱ�˵��:
		--BASE_T_ORG:������Ϣ
		--BASE_T_USERDEPTJOB:�û����ڵĲ��Ÿ�λ
		--SYS_T_DEPTRIGHT:�û�ָ������Ȩ�޵Ĳ���
		--SYS_T_DEPTJOBRIGHT:�Բ��Ÿ�λ��ӵĲ���Ȩ��
-- ==========================================
ALTER VIEW [dbo].[V_PT_UserRightDeptList]
AS

	select distinct A.*,B.USER_ID from BASE_T_ORG A,(			
		 --��Ա���ڲ���
		 SELECT a.DEPT_ID ,
				b.USER_ID
		 FROM   dbo.BASE_T_ORG a
				INNER JOIN BASE_T_USERDEPTJOB b ON b.DEPT_ID = a.DEPT_ID
		 WHERE  a.ISDELETE = 0 AND b.ISDELETE=0
		 UNION ALL                                                                     
		 --��Ա���ڸ�λ���ܵĲ���	
		 SELECT a.DEPT_ID ,
				b.USER_ID
		 FROM   dbo.BASE_T_ORG a
				INNER JOIN dbo.BASE_T_USERDEPTJOB b ON (a.SUPER_DEPT = b.DEPT_ID
					AND a.SUPER_JOB = b.JOB_ID)
		 WHERE  a.ISDELETE = 0 AND b.ISDELETE=0
		 UNION ALL 
		 --��Աָ������Ȩ�޲���
		 SELECT a.DEPT_ID ,
				b.USER_ID
		 FROM   dbo.BASE_T_ORG a
				INNER JOIN dbo.SYS_T_DEPTRIGHT b ON a.DEPT_ID = b.DEPT_ID
		 WHERE  a.ISDELETE = 0 and b.ISDELETE=0
		 UNION ALL   
		 --���õĸ�λȨ�ޣ������ܴ������⣩
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


