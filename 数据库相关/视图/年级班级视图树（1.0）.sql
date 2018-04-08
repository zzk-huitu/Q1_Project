
GO

/****** Object:  View [dbo].[V_PT_GradeClassTree]    Script Date: 04/08/2018 17:30:03 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO










 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2016-07-21
-- Description:	学校-班级-年级的树形数据视图
-- 字段说明:   99.999999 PERCENT 
--      id: 树的节点ID
--		text:树的节点名称
--		iconCls:树的节点样式,默认为空
--		leaf:是否为叶节点,0-是,1-否,构建时最后一层为是
--		level:节点的层次,从1起按顺序增加 
--		parent:树的上级节点
-- 构建数据表说明:
		--BASE_T_SCHOOL:学校,树的第一层,父节点为ROOT
		--JW_T_GRADE:年级表,树的第二层,父节点为BASE_T_SCHOOL.SCHOOL_ID
		--JW_T_GRADECLASS:班级表,树的第三层,父节点为 JW_T_GRADE.GRAI_ID
-- =============================================
ALTER VIEW [dbo].[V_PT_GradeClassTree]
AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        SCHOOL_ID AS id ,
                        SCHOOL_NAME AS text ,
                        '' AS iconCls ,
                        'false' AS leaf ,
                        1 AS level ,
                        'ROOT' AS parent,
                        SCHOOL_ID AS treeids,
                        ORDER_INDEX
              FROM      dbo.BASE_T_SCHOOL
            ) AS school
    UNION ALL 
    --年级和学校联系
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        GRAI_ID AS id ,
                        GRADE_NAME AS text ,
                        '' AS iconCls ,
                        'false' AS leaf ,
                        2 AS level ,
                        SCHOOL_ID AS parent,
                        SCHOOL_ID + ',' + GRAI_ID AS treeids,
                        ORDER_INDEX
              FROM      dbo.JW_T_GRADE
              WHERE ISDELETE = 0
              ORDER BY GRADE_CODE
            ) AS grade
    UNION ALL 
    --年级和班级联系
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        gradeClass.CLAI_ID AS id ,
                        gradeClass.CLASS_NAME AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        3 AS level ,
                        gradeClass.GRAI_ID AS parent,
                        SCHOOL_ID + ',' + gradeClass.GRAI_ID + ',' + gradeClass.CLAI_ID AS treeids,
                        gradeClass.ORDER_INDEX
              FROM      dbo.JW_T_GRADECLASS gradeClass
						INNER JOIN dbo.JW_T_GRADE grade ON grade.GRAI_ID=gradeClass.GRAI_ID
			  WHERE gradeclass.ISDELETE=0
              ORDER BY  grade.GRADE_CODE ,
                        gradeClass.ORDER_INDEX ASC
            ) AS gradeclass;       






GO


