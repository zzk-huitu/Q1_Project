USE [NewQ1_test]
GO
/****** Object:  StoredProcedure [dbo].[P_PT_GetUserRightDeptTree]    Script Date: 04/13/2018 17:54:22 ******/
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
            WITH    ctr_child ( deptId, nodeText, iconcls, isLeaf, nodeLevel, treeIds, parentNode, orderIndex, deptType, mainLeader, outPhone, remark, superDept, superJob, allDeptName, superDeptName, superJobName )
                      AS (
		--起始条件
                           SELECT   deptId ,		
                                    nodeText ,
                                    '' AS iconcls ,
                                    isLeaf ,
                                    nodeLevel ,
                                    '' AS treeIds ,
                                    parentNode ,
                                    orderIndex ,
                                    deptType ,
                                    (
										SELECT jobName FROM T_PT_DeptJob 
											WHERE isDelete=0 and jobType=0 and deptId=A.deptId
									)as mainLeader ,
                                    outPhone ,
                                    remark ,
                                    superDept ,
                                    superJob ,
                                    allDeptName ,
                                    superDeptName ,
                                    superJobName
                           FROM     dbo.T_PT_Department AS A
                           WHERE    isDelete = 0 and deptId = 'ROOT'  --列出子节点查询条件
                           UNION ALL
							--递归条件
                           SELECT   a.deptId ,
                                    a.nodeText ,
                                    '' AS iconcls ,
                                    a.isLeaf ,
                                    a.nodeLevel ,
                                    '' AS treeIds ,
                                    a.parentNode ,
                                    a.orderIndex ,
                                    a.deptType ,
                                    (
										SELECT jobName FROM T_PT_DeptJob 
											WHERE isDelete=0 and jobType=0 and deptId=a.deptId
									)as mainLeader ,
                                    a.outPhone ,
                                    a.remark ,
                                    a.superDept ,
                                    a.superJob ,
                                    a.allDeptName ,
                                    a.superDeptName ,
                                    a.superJobName
                           FROM     T_PT_Department a
                                    INNER JOIN ctr_child b --执行递归，这里就要理解下了 
                                    ON a.parentNode = b.deptId
                           WHERE a.isDelete=0
                         )
                SELECT  * ,
                        '1' AS isRight
                FROM    ctr_child
                ORDER BY parentNode ,
                        orderIndex ASC;  
        END;
    ELSE
        BEGIN
	--指定权限部门及所在部门与岗位主管的部门
            WITH    ctr_parent ( deptId, nodeText, iconcls, isLeaf, nodeLevel, treeIds, parentNode, orderIndex, deptType, mainLeader, outPhone, remark, superDept, superJob, allDeptName, superDeptName, superJobName )
                      AS (
						--起始条件
                           SELECT   deptId ,
                                    nodeText ,
                                    '' AS iconcls ,
                                    isLeaf ,
                                    nodeLevel ,
                                    '' AS treeIds ,
                                    parentNode ,
                                    orderIndex ,
                                    deptType ,
                                    (
										SELECT jobName FROM T_PT_DeptJob 
											WHERE isDelete=0 and jobType=0 and deptId=A.deptId
									)as mainLeader ,
                                    outPhone ,
                                    remark ,
                                    superDept ,
                                    superJob ,
                                    allDeptName ,
                                    superDeptName ,
                                    superJobName
                           FROM     dbo.T_PT_Department as A
                           WHERE    isDelete = 0 and deptId IN ( SELECT deptId
                                                 FROM   V_PT_UserRightDeptList 
                                                 WHERE  userId = @userId )  --列出父节点查询条件
                           UNION ALL
						--递归条件
                           SELECT   a.deptId ,
                                    a.nodeText ,
                                    '' AS iconcls ,
                                    a.isLeaf ,
                                    a.nodeLevel ,
                                    '' AS treeIds ,
                                    a.parentNode ,
                                    a.orderIndex ,
                                    a.deptType ,
                                    (
										SELECT jobName FROM T_PT_DeptJob 
											WHERE isDelete=0 and jobType=0 and deptId=a.deptId
									)as mainLeader ,
                                    a.outPhone ,
                                    a.remark ,
                                    a.superDept ,
                                    a.superJob ,
                                    a.allDeptName ,
                                    a.superDeptName ,
                                    a.superJobName
                           FROM     T_PT_Department a
                                    INNER JOIN ctr_parent b --执行递归，这里就要理解下了 
                                    ON a.deptId = b.parentNode
                           WHERE a.isDelete=0
                         )
                SELECT  DISTINCT
                        a.* ,
                        ( CASE ISNULL(b.userId, '0')
                            WHEN '0' THEN '0'
                            ELSE '1'
                          END ) AS isRight
                FROM    ctr_parent a
                        LEFT JOIN dbo.V_PT_UserRightDeptList b ON a.deptId = b.deptId
                                                              AND b.userId = @userId
                ORDER BY a.parentNode ,
                        a.orderIndex ASC; 
        END;







