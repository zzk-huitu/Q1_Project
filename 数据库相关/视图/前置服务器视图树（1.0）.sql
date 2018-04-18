USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_FrontServerTree]    Script Date: 04/16/2018 18:38:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER VIEW [dbo].[V_PT_FrontServerTree] AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        CONVERT(VARCHAR(36),1) AS id ,
                        'À˘”–«∞÷√' AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         1 AS level ,
                         '' AS treeids,
                         'ROOT' AS parent
            ) AS one
    UNION ALL 
    
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        A.frontServerId AS id ,
                        A.frontServerName AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        2 AS level ,
                        '' AS treeids,
                        CONVERT(VARCHAR(36),1) AS parent
              FROM    dbo.T_PT_FrontServer A
              WHERE A.isDelete=0
            ) AS two



GO


