USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_OfficeRoomTree]    Script Date: 04/16/2018 18:38:55 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



ALTER VIEW [dbo].[V_PT_OfficeRoomTree] AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        areaId AS id ,
                        nodeText AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         nodeLevel AS level ,
                         '' AS treeids,
                        parentNode AS parent
              FROM      T_PT_RoomArea 
              WHERE isDelete=0             
              ORDER BY  orderIndex ASC
            ) AS roomarea
    UNION ALL 
    
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        A.roomId AS id ,
                        B.roomName AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        5 AS level ,
                        '' AS treeids,
                       C.areaId AS parent
              FROM      T_PT_OfficeDefine A
              JOIN T_PT_RoomInfo B
              ON A.roomId=B.roomId
              JOIN T_PT_RoomArea C
              ON A.areaId=C.areaId
              WHERE A.isDelete=0
              ORDER BY  A.orderIndex ASC
            ) AS officeAllot;





GO


