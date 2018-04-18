USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_AreaRoomInfoTree]    Script Date: 04/16/2018 18:37:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




ALTER VIEW [dbo].[V_PT_AreaRoomInfoTree] AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        areaId AS id ,
                        nodeText AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         nodeLevel AS level ,
                         '' AS treeids,
                        parentNode AS parent
              FROM      dbo.T_PT_RoomArea 
              WHERE isDelete=0             
              ORDER BY  orderIndex ASC
            ) AS roomarea
    UNION ALL 
    
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        A.roomId AS id ,
                        A.roomName AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        5 AS level ,
                        '' AS treeids,
                       A.areaId AS parent
              FROM    dbo.T_PT_RoomInfo A
              WHERE A.isDelete=0
              ORDER BY  text ASC
            ) AS roominfo;

GO


