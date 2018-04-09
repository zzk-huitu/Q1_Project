USE [Q1_test6]
GO
/****** Object:  StoredProcedure [dbo].[P_PT_GetUserRightDeptTree]    Script Date: 04/08/2018 16:31:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




-- =============================================
-- Author:		LUOYIBO
-- Create date: 2017-04-06
-- Description:	获取指定用户的有权限部门树形列表 isRight为1时，代表用户具备管理这个部门的权限
-- Paramemt:    
--         @userId: 用户Id 
-- =============================================
ALTER  PROC [dbo].[P_PT_GetUserRightDeptTree]
    (
      @userId VARCHAR(36) ,
      @rightType INT
    )
AS
    IF ( @rightType = 0 )
        BEGIN
	--所有部门
            WITH    ctr_child ( DEPT_ID, NODE_TEXT, iconcls, ISLEAF, NODE_LEVEL, TREE_IDS, PARENT_NODE, ORDER_INDEX, DEPT_TYPE, MAIN_LEADER, OUT_PHONE, REMARK, VICE_LEADER, SUPER_DEPT, SUPER_JOB, ALL_DEPTNAME, SUPERDEPT_NAME, SUPERJOB_NAME )
                      AS (
		--起始条件
                           SELECT   DEPT_ID ,		
                                    NODE_TEXT ,
                                    '' AS iconcls ,
                                    ISLEAF ,
                                    NODE_LEVEL ,
                                    '' AS TREE_IDS ,
                                    PARENT_NODE ,
                                    ORDER_INDEX ,
                                    DEPT_TYPE ,
                                    (
										SELECT JOB_NAME FROM BASE_T_DEPTJOB 
											WHERE ISDELETE=0 and JOB_TYPE=0 and DEPT_ID=A.DEPT_ID
									)as MAIN_LEADER ,
                                    OUT_PHONE ,
                                    REMARK ,
                                    VICE_LEADER ,
                                    SUPER_DEPT ,
                                    SUPER_JOB ,
                                    ALL_DEPTNAME ,
                                    SUPERDEPT_NAME ,
                                    SUPERJOB_NAME
                           FROM     dbo.BASE_T_ORG AS A
                           WHERE    ISDELETE = 0 and DEPT_ID = 'ROOT'  --列出子节点查询条件
                           UNION ALL
							--递归条件
                           SELECT   a.DEPT_ID ,
                                    a.NODE_TEXT ,
                                    '' AS iconcls ,
                                    a.ISLEAF ,
                                    a.NODE_LEVEL ,
                                    '' AS TREE_IDS ,
                                    a.PARENT_NODE ,
                                    a.ORDER_INDEX ,
                                    a.DEPT_TYPE ,
                                    (
										SELECT JOB_NAME FROM BASE_T_DEPTJOB 
											WHERE ISDELETE=0 and JOB_TYPE=0 and DEPT_ID=a.DEPT_ID
									)as MAIN_LEADER ,
                                    a.OUT_PHONE ,
                                    a.REMARK ,
                                    a.VICE_LEADER ,
                                    a.SUPER_DEPT ,
                                    a.SUPER_JOB ,
                                    a.ALL_DEPTNAME ,
                                    a.SUPERDEPT_NAME ,
                                    a.SUPERJOB_NAME
                           FROM     BASE_T_ORG a
                                    INNER JOIN ctr_child b --执行递归，这里就要理解下了 
                                    ON a.PARENT_NODE = b.DEPT_ID
                           WHERE a.ISDELETE=0
                         )
                SELECT  * ,
                        '1' AS isRight
                FROM    ctr_child
                ORDER BY PARENT_NODE ,
                        ORDER_INDEX ASC;  
        END;
    ELSE
        BEGIN
	--指定权限部门及所在部门与岗位主管的部门
            WITH    ctr_parent ( DEPT_ID, NODE_TEXT, iconcls, ISLEAF, NODE_LEVEL, TREE_IDS, PARENT_NODE, ORDER_INDEX, DEPT_TYPE, MAIN_LEADER, OUT_PHONE, REMARK, VICE_LEADER, SUPER_DEPT, SUPER_JOB, ALL_DEPTNAME, SUPERDEPT_NAME, SUPERJOB_NAME )
                      AS (
						--起始条件
                           SELECT   DEPT_ID ,
                                    NODE_TEXT ,
                                    '' AS iconcls ,
                                    ISLEAF ,
                                    NODE_LEVEL ,
                                    '' AS TREE_IDS ,
                                    PARENT_NODE ,
                                    ORDER_INDEX ,
                                    DEPT_TYPE ,
                                    (
										SELECT JOB_NAME FROM BASE_T_DEPTJOB 
											WHERE ISDELETE=0 and JOB_TYPE=0 and DEPT_ID=A.DEPT_ID
									)as MAIN_LEADER ,
                                    OUT_PHONE ,
                                    REMARK ,
                                    VICE_LEADER ,
                                    SUPER_DEPT ,
                                    SUPER_JOB ,
                                    ALL_DEPTNAME ,
                                    SUPERDEPT_NAME ,
                                    SUPERJOB_NAME
                           FROM     dbo.BASE_T_ORG as A
                           WHERE    ISDELETE = 0 and DEPT_ID IN ( SELECT DEPT_ID
                                                 FROM   SYS_V_USERRIGHTDEPT
                                                 WHERE  USER_ID = @userId )  --列出父节点查询条件
                           UNION ALL
						--递归条件
                           SELECT   a.DEPT_ID ,
                                    a.NODE_TEXT ,
                                    '' AS iconcls ,
                                    a.ISLEAF ,
                                    a.NODE_LEVEL ,
                                    '' AS TREE_IDS ,
                                    a.PARENT_NODE ,
                                    a.ORDER_INDEX ,
                                    a.DEPT_TYPE ,
                                    (
										SELECT JOB_NAME FROM BASE_T_DEPTJOB 
											WHERE ISDELETE=0 and JOB_TYPE=0 and DEPT_ID=a.DEPT_ID
									)as MAIN_LEADER ,
                                    a.OUT_PHONE ,
                                    a.REMARK ,
                                    a.VICE_LEADER ,
                                    a.SUPER_DEPT ,
                                    a.SUPER_JOB ,
                                    a.ALL_DEPTNAME ,
                                    a.SUPERDEPT_NAME ,
                                    a.SUPERJOB_NAME
                           FROM     BASE_T_ORG a
                                    INNER JOIN ctr_parent b --执行递归，这里就要理解下了 
                                    ON a.DEPT_ID = b.PARENT_NODE
                           WHERE a.ISDELETE=0
                         )
                SELECT  DISTINCT
                        a.* ,
                        ( CASE ISNULL(b.USER_ID, '0')
                            WHEN '0' THEN '0'
                            ELSE '1'
                          END ) AS isRight
                FROM    ctr_parent a
                        LEFT JOIN dbo.SYS_V_USERRIGHTDEPT b ON a.DEPT_ID = b.DEPT_ID
                                                              AND b.USER_ID = @userId
                ORDER BY a.PARENT_NODE ,
                        a.ORDER_INDEX ASC; 
        END;





