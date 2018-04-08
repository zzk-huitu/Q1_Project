USE [Q1_test6]
GO

/****** Object:  View [dbo].[V_PT_BrandDeviceTree]    Script Date: 04/08/2018 17:54:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[V_PT_BrandDeviceTree] (id, text, iconCls, leaf, level, treeids, parent) AS 
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        BRAND_ID AS id ,
                        BRANDNAME AS text ,
                        '' AS iconCls ,
                        'false' AS leaf,
                         level AS level ,
                         '' AS treeids,
                        PARENT_NODE AS parent
              FROM      dbo.PT_IR_DEVICE_BRAND 
              WHERE ISDELETE=0  AND level<=3        
              ORDER BY  ORDER_INDEX ASC
            ) AS area
GO


