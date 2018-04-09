
GO

/****** Object:  View [dbo].[V_PT_GradeClassTree]    Script Date: 04/08/2018 17:30:03 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO










 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2016-07-21
-- Description:	ѧУ-�༶-�꼶������������ͼ
-- �ֶ�˵��:   99.999999 PERCENT 
--      id: ���Ľڵ�ID
--		text:���Ľڵ�����
--		iconCls:���Ľڵ���ʽ,Ĭ��Ϊ��
--		leaf:�Ƿ�ΪҶ�ڵ�,0-��,1-��,����ʱ���һ��Ϊ��
--		level:�ڵ�Ĳ��,��1��˳������ 
--		parent:�����ϼ��ڵ�
-- �������ݱ�˵��:
		--BASE_T_SCHOOL:ѧУ,���ĵ�һ��,���ڵ�ΪROOT
		--JW_T_GRADE:�꼶��,���ĵڶ���,���ڵ�ΪBASE_T_SCHOOL.SCHOOL_ID
		--JW_T_GRADECLASS:�༶��,���ĵ�����,���ڵ�Ϊ JW_T_GRADE.GRAI_ID
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
    --�꼶��ѧУ��ϵ
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
    --�꼶�Ͱ༶��ϵ
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


