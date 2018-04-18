USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_BrandDeviceTree]    Script Date: 04/16/2018 18:37:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



ALTER VIEW [dbo].[V_PT_BrandDeviceTree] (id, text, iconCls, leaf, level, treeids, parent) AS 
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        brandId AS id ,
                        brandName AS text ,
                        '' AS iconCls ,
                        'false' AS leaf,
                         level AS level ,
                         '' AS treeids,
                        parentNode AS parent
              FROM      dbo.T_PT_IrDeviceBrand 
              WHERE isDelete=0  AND level<=3        
              ORDER BY  orderIndex ASC
            ) AS area

GO


