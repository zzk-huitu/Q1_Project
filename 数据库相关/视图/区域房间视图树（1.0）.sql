
GO

/****** Object:  View [dbo].[V_PT_AreaRoomInfoTree]    Script Date: 04/08/2018 17:20:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



ALTER VIEW [dbo].[V_PT_AreaRoomInfoTree] AS
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        AREA_ID AS id ,
                        NODE_TEXT AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         NODE_LEVEL AS level ,
                         '' AS treeids,
                        PARENT_NODE AS parent
              FROM      dbo.BUILD_T_ROOMAREA 
              WHERE ISDELETE=0             
              ORDER BY  ORDER_INDEX ASC
            ) AS roomarea
    UNION ALL 
    
    SELECT  *
    FROM    ( SELECT TOP 99.999999 PERCENT
                        A.ROOM_ID AS id ,
                        A.ROOM_NAME AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        5 AS level ,
                        '' AS treeids,
                       A.AREA_ID AS parent
              FROM    dbo.BUILD_T_ROOMINFO A
              WHERE A.ISDELETE=0
              ORDER BY  text ASC
            ) AS roominfo;

GO


