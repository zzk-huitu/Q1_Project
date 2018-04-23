
GO

/****** Object:  View [dbo].[OA_V_NOTICETYPETREE]    Script Date: 04/20/2018 09:19:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2016-12-26
-- Description:	�������͵�����������ͼ
-- �ֶ�˵��:   99.999999 PERCENT 
--      id: ���Ľڵ�ID
--		text:���Ľڵ�����
--		iconCls:���Ľڵ���ʽ,Ĭ��Ϊ��
--		leaf:�Ƿ�ΪҶ�ڵ�,0-��,1-��,����ʱ���һ��Ϊ��
--		level:�ڵ�Ĳ��,��1��˳������ 
--		parent:�����ϼ��ڵ�
-- �������ݱ�˵��:
		--BASE_T_DIC:�����ֵ�Ϊ��һ�㣬��DIC_CODE=NOTICETYPE
		--BASE_T_DICITEM:�ֵ���Ϊ�ڶ��㣬����ΪDIC_CODE=NOTICETYPE��Ӧ���ֵ�ID
-- =============================================
create VIEW [dbo].[V_PT_NoticeTypeTree]
AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        dictId AS id ,
                        nodeText AS text ,
                        '' AS iconCls ,
                        'false' AS leaf ,
                        1 AS level ,
                        'ROOT' AS parent ,
                        '' AS treeids
              FROM      dbo.T_PT_DataDict
              WHERE dicCode='NOTICETYPE'
            ) AS DIC
    UNION
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        itemCode AS id ,
                        itemName AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        2 AS level ,
                        dictId AS parent ,
                        '' AS treeids
              FROM      dbo.T_PT_DataDictItem
              WHERE dictId=(SELECT dictId FROM dbo.T_PT_DataDict WHERE dicCode='NOTICETYPE')
              ORDER BY itemCode 
            ) AS DICITEM
      



GO
