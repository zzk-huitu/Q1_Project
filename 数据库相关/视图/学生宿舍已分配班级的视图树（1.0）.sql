USE [Q1_test6]
GO

/****** Object:  View [dbo].[V_PT_StudentDromAllotTree]    Script Date: 04/08/2018 17:41:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER VIEW [dbo].[V_PT_StudentDromAllotTree] AS

    SELECT  *
    FROM    ( 
			SELECT TOP 99.999999 PERCENT
                        AREA_ID AS id ,
                        NODE_TEXT AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         NODE_LEVEL AS level ,
                         TREE_IDS AS treeids,
                        PARENT_NODE AS parent
              FROM      dbo.BUILD_T_ROOMAREA 
              WHERE ISDELETE=0 and AREA_TYPE in ('01','02')
              ORDER BY  ORDER_INDEX ASC
              
    ) AS roomarea
          
    UNION ALL          
    
    SELECT  *
    FROM    (                 
           SELECT TOP 99.999999 PERCENT
                        AREA_ID AS id ,
                        NODE_TEXT AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         NODE_LEVEL AS level ,
                         TREE_IDS AS treeids,
                        PARENT_NODE AS parent
              FROM      dbo.BUILD_T_ROOMAREA 
              WHERE ISDELETE=0 and AREA_TYPE='03'   
				and AREA_ID  IN(
					SELECT btr.PARENT_NODE FROM BUILD_T_ROOMAREA btr
					WHERE btr.AREA_TYPE='04'  AND btr.AREA_ID IN(
						SELECT A.AREA_ID FROM BUILD_T_DORMDEFINE A
						JOIN dbo.BUILD_T_ROOMINFO B
						ON A.ROOM_ID=B.ROOM_ID
						JOIN dbo.BUILD_T_ROOMAREA C
						ON A.AREA_ID=C.AREA_ID						
						WHERE A.ISDELETE=0 and A.DORM_TYPELB='1'		--1为学生宿舍
							AND A.DORM_ID IN(
								SELECT
									DORM_ID 
								FROM
									dbo.JW_T_CLASSDORMALLOT 
								WHERE
									ISDELETE=0 
									AND CDORM_ID IN(
										SELECT
											CDORM_ID 
										FROM
											dbo.DORM_T_STUDENTDORM 
										WHERE
											ISDELETE=0
									)
								)							
					)
				)      
              ORDER BY  ORDER_INDEX ASC
    )as roomarea
    
    UNION ALL 
	
	SELECT  *
    FROM    ( 
		SELECT TOP 99.999999 PERCENT
                        AREA_ID AS id ,
                        NODE_TEXT AS text ,
                        '' AS iconCls ,
                         'false' AS leaf,
                         NODE_LEVEL AS level ,
                         TREE_IDS AS treeids,
                        PARENT_NODE AS parent
              FROM      dbo.BUILD_T_ROOMAREA 
              WHERE ISDELETE=0 and AREA_TYPE='04' 
				AND AREA_ID IN(
						SELECT A.AREA_ID FROM BUILD_T_DORMDEFINE A
						JOIN dbo.BUILD_T_ROOMINFO B
						ON A.ROOM_ID=B.ROOM_ID
						JOIN dbo.BUILD_T_ROOMAREA C
						ON A.AREA_ID=C.AREA_ID
						WHERE A.ISDELETE=0 and A.DORM_TYPELB='1'		--1为学生宿舍
							AND A.DORM_ID IN(
								SELECT
									DORM_ID 
								FROM
									dbo.JW_T_CLASSDORMALLOT 
								WHERE
									ISDELETE=0 
									AND CDORM_ID IN(
										SELECT
											CDORM_ID 
										FROM
											dbo.DORM_T_STUDENTDORM 
										WHERE
											ISDELETE=0
									)
								)							
				)       
              ORDER BY  ORDER_INDEX ASC
    ) AS roomarea  
    UNION ALL 
    
    SELECT  *
    FROM    ( 
		SELECT TOP 99.999999 PERCENT
			A.DORM_ID AS id ,
			B.ROOM_NAME AS text ,
			'' AS iconCls ,
			'true' AS leaf ,
			5 AS level ,
			'' AS treeids,
		    C.AREA_ID AS parent
		FROM	dbo.BUILD_T_DORMDEFINE A
			JOIN dbo.BUILD_T_ROOMINFO B
			ON A.ROOM_ID=B.ROOM_ID
			JOIN dbo.BUILD_T_ROOMAREA C
			ON A.AREA_ID=C.AREA_ID
			WHERE A.ISDELETE=0 and A.DORM_TYPELB='1'		--1为学生宿舍	
				AND A.DORM_ID IN(
					SELECT
						DORM_ID 
					FROM
						dbo.JW_T_CLASSDORMALLOT 
					WHERE
						ISDELETE=0 
						AND CDORM_ID IN(
							SELECT
								CDORM_ID 
							FROM
								dbo.DORM_T_STUDENTDORM 
							WHERE
								ISDELETE=0
						)
					)		
		ORDER BY  A.ORDER_INDEX ASC
     ) AS officeAllot;



GO


