
GO

/****** Object:  View [dbo].[OA_V_NOTICETYPETREE]    Script Date: 04/20/2018 09:19:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2016-12-26
-- Description:	公告类型的树形数据视图
-- 字段说明:   99.999999 PERCENT 
--      id: 树的节点ID
--		text:树的节点名称
--		iconCls:树的节点样式,默认为空
--		leaf:是否为叶节点,0-是,1-否,构建时最后一层为是
--		level:节点的层次,从1起按顺序增加 
--		parent:树的上级节点
-- 构建数据表说明:
		--BASE_T_DIC:数据字典为第一层，其DIC_CODE=NOTICETYPE
		--BASE_T_DICITEM:字典项为第二层，数据为DIC_CODE=NOTICETYPE对应的字典ID
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
