USE [NewQ1_test]
GO
/****** Object:  StoredProcedure [dbo].[P_PT_GetUserRightGradeClassCourseTree]    Script Date: 04/13/2018 17:54:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO






-- =============================================
-- Author:		LUOYIBO
-- Create date: 2017-04-06
-- Description:	获取指定用户的有权限年级、班级、学科树形列表
-- Paramemt:    
--         @userId: 用户Id 
--         @rightType: 权限类型 
--         @deptTye: 部门类型(04-年级 05-班级 06-学科)
-- =============================================
ALTER  PROC [dbo].[P_PT_GetUserRightGradeClassCourseTree]
    (
      @userId VARCHAR(36) ,
      @rightType INT ,
      @deptTye VARCHAR(4)
    )
AS
    IF ( @rightType = 0 )
        BEGIN
	--所有部门
            WITH    ctr_child ( deptId, nodeText, iconcls, isLeaf, nodeLevel, treeIds, parentNode, orderIndex, deptType, mainLeader, outPhone, remark,superDept, superJob, allDeptName, superDeptName, superJobName )
                      AS (
		--起始条件
                           SELECT   deptId ,
                                    nodeText ,
                                    courseId AS iconcls ,	--当为学科时，此值为课程id
                                    isLeaf ,
                                    nodeLevel ,
                                    treeIds AS treeIds ,
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
                           WHERE    isDelete = 0 and  deptType = @deptTye  --列出子节点查询条件
                           UNION ALL
							--递归条件
                           SELECT   a.deptId ,
                                    a.nodeText ,
                                    null AS iconcls ,
                                    a.isLeaf ,
                                    a.nodeLevel ,
                                    a.treeIds AS treeIds ,
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
                                    ON a.deptId = b.parentNode
                           WHERE    a.isDelete = 0 
                         )
                SELECT DISTINCT
                        * ,
                        '1' AS isRight
                FROM    ctr_child
                ORDER BY parentNode ,
                        orderIndex ASC;  
        END;
    ELSE
        BEGIN
	--指定权限部门及所在部门与主管的部门
            WITH    ctr_parent ( deptId, nodeText, iconcls, isLeaf, nodeLevel, treeIds, parentNode, orderIndex, deptType, mainLeader, outPhone, remark,superDept, superJob, allDeptName, superDeptName, superJobName )
                      AS (
						--起始条件
                           SELECT   deptId ,
                                    nodeText ,
                                    courseId AS iconcls ,	--当为学科时，此值为课程id
                                    isLeaf ,
                                    nodeLevel ,
                                    treeIds AS treeIds ,
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
                           WHERE    isDelete = 0 and deptId IN (
                                    SELECT  deptId
                                    FROM    V_PT_UserRightDeptList
                                    WHERE   userId = @userId
                                            AND deptType = @deptTye )  --列出父节点查询条件
                           UNION ALL
						--递归条件
                           SELECT   a.deptId ,
                                    a.nodeText ,
                                    null AS iconcls ,
                                    a.isLeaf ,
                                    a.nodeLevel ,
                                    a.treeIds AS treeIds ,
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
                           WHERE    a.isDelete = 0 
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







