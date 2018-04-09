USE [Q1_test6]
GO

/****** Object:  View [dbo].[V_PT_FrontServerTree]    Script Date: 04/08/2018 17:49:35 ******/
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
                        A.FRONTSERVER_ID AS id ,
                        A.FRONTSERVER_NAME AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        2 AS level ,
                        '' AS treeids,
                        CONVERT(VARCHAR(36),1) AS parent
              FROM    dbo.SYS_FRONTSERVER A
              WHERE A.ISDELETE=0
            ) AS two


GO


