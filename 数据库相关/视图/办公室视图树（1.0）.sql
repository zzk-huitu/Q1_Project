
GO

/****** Object:  View [dbo].[V_PT_OfficeRoomTree]    Script Date: 04/08/2018 17:33:37 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



ALTER VIEW [dbo].[V_PT_OfficeRoomTree] AS
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
                        B.ROOM_NAME AS text ,
                        '' AS iconCls ,
                        'true' AS leaf ,
                        5 AS level ,
                        '' AS treeids,
                       C.AREA_ID AS parent
              FROM      dbo.BUILD_T_OFFICEDEFINE A
              JOIN dbo.BUILD_T_ROOMINFO B
              ON A.ROOM_ID=B.ROOM_ID
              JOIN dbo.BUILD_T_ROOMAREA C
              ON A.AREA_ID=C.AREA_ID
              WHERE A.ISDELETE=0
              ORDER BY  A.ORDER_INDEX ASC
            ) AS officeAllot;




GO


