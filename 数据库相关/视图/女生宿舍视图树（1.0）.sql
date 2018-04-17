USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_StuGirlDormTree]    Script Date: 04/16/2018 18:40:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



ALTER VIEW [dbo].[V_PT_StuGirlDormTree] AS

    SELECT  *
    FROM    ( 
			SELECT TOP 99.999999 PERCENT
                        areaId AS id ,
                        nodeText AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         nodeLevel AS level ,
                         '' AS treeids,
                        parentNode AS parent
              FROM      dbo.T_PT_RoomArea 
              WHERE isDelete=0 and areaType in ('01','02')
              ORDER BY  orderIndex ASC
              
    ) AS roomarea
          
    UNION ALL          
    
    SELECT  *
    FROM    (                 
           SELECT TOP 99.999999 PERCENT
                        areaId AS id ,
                        nodeText AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         nodeLevel AS level ,
                         '' AS treeids,
                        parentNode AS parent
              FROM      dbo.T_PT_RoomArea 
              WHERE isDelete=0 and areaType='03'   
				and areaId  IN(
					SELECT btr.parentNode FROM T_PT_RoomArea btr
					WHERE btr.areaType='04'  AND btr.areaId IN(
						SELECT A.areaId FROM T_PT_DormDefine A
						JOIN dbo.T_PT_RoomInfo B
						ON A.roomId=B.roomId
						JOIN dbo.T_PT_RoomArea C
						ON A.areaId=C.areaId
						WHERE A.isDelete=0 and A.dormCategory='1'		--1为学生宿舍
							and A.dormType='2'		--2为女生宿舍
					)
				)      
              ORDER BY  orderIndex ASC
    )as roomarea
    
    UNION ALL 
	
	SELECT  *
    FROM    ( 
		SELECT TOP 99.999999 PERCENT
                        areaId AS id ,
                        nodeText AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         nodeLevel AS level ,
                         '' AS treeids,
                        parentNode AS parent
              FROM      dbo.T_PT_RoomArea 
              WHERE isDelete=0 and areaType='04' 
				AND areaId IN(
						SELECT A.areaId FROM T_PT_DormDefine A
						JOIN dbo.T_PT_RoomInfo B
						ON A.roomId=B.roomId
						JOIN dbo.T_PT_RoomArea C
						ON A.areaId=C.areaId
						WHERE A.isDelete=0	and A.dormCategory='1'		--1为学生宿舍
							and A.dormType='2'		--2为女生宿舍
				)       
              ORDER BY  orderIndex ASC
    ) AS roomarea  
    UNION ALL 
    
    SELECT  *
    FROM    ( 
		SELECT TOP 99.999999 PERCENT
			A.dormId AS id ,
			B.roomName AS text ,
			'' AS iconCls ,
			'true' AS leaf ,
			5 AS level ,
			'' AS treeids,
		    C.areaId AS parent
		FROM	dbo.T_PT_DormDefine A
			JOIN dbo.T_PT_RoomInfo B
			ON A.roomId=B.roomId
			JOIN dbo.T_PT_RoomArea C
			ON A.areaId=C.areaId
		WHERE A.isDelete=0 and A.dormCategory='1'		--1为学生宿舍
			and A.dormType='2'		--2为女生宿舍
		ORDER BY  A.orderIndex ASC
     ) AS officeAllot;




GO


