USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_GradeClassTree]    Script Date: 04/16/2018 18:38:44 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[V_PT_GradeClassTree]
AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        schoolId AS id ,
                        schoolName AS text ,
                        '' AS iconCls ,
                        'false' AS leaf ,
                        1 AS level ,
                        'ROOT' AS parent,
                        '' AS treeids,
                        orderIndex
              FROM      dbo.T_PT_School
            ) AS school
    UNION ALL 
    --年级和学校联系
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        gradeId AS id ,
                        gradeName AS text ,
                        '' AS iconCls ,
                        'false' AS leaf ,
                        2 AS level ,
                        '' AS parent,
                        '' + ',' + gradeId AS treeids,
                        orderIndex
              FROM      dbo.T_PT_Grade
              WHERE isDelete = 0
              ORDER BY gradeCode
            ) AS grade
    UNION ALL 
    --年级和班级联系
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        gradeClass.classId AS id ,
                        gradeClass.className AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        3 AS level ,
                        gradeClass.gradeId AS parent,
                        '' + ',' + gradeClass.gradeId + ',' + gradeClass.classId AS treeids,
                        gradeClass.orderIndex
              FROM      dbo.T_PT_GradeClass gradeClass
						INNER JOIN dbo.T_PT_Grade grade ON grade.gradeId=gradeClass.gradeId
			  WHERE gradeclass.isDelete=0
              ORDER BY  grade.gradeCode ,
                        gradeClass.orderIndex ASC
            ) AS gradeclass;       







GO


