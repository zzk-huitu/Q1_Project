
GO

/****** Object:  View [dbo].[V_PT_RoomTermList]    Script Date: 04/08/2018 17:53:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2017-02-06
-- Description:	各房间绑定的终端设备数据视图
-- 字段说明:   
--      roomId: 房间ID
--		roomCode:房间编码
--		roomName:房间名称
--		houseNumb1:门牌号1
--		termId1:门牌号1对应的终端ID 
--		termCode1:门牌号1对应的终端编码
--      后面的termId2、termId3、termId4、termId5分别对应5个门牌号
-- 构建数据表说明:
		--BUILD_T_ROOMINFO:房间信息
		--OA_T_INFOTERM:终端设备信息
-- =============================================
ALTER VIEW [dbo].[V_PT_RoomTermList]
AS
    SELECT  a.ROOM_ID AS roomId ,
            a.ROOM_CODE AS roomCode,
            a.ROOM_NAME AS roomName ,
            a.EXT_FIELD01 AS houseNumb1 ,
            ISNULL(( SELECT TERM_ID
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD01
                   ), '') AS termId1 ,
            ISNULL(( SELECT TERM_CODE
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD01
                   ), '') AS termCode1 ,
            a.EXT_FIELD02 AS houseNumb2 ,
            ISNULL(( SELECT TERM_ID
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD02
                   ), '') AS termId2 ,
            ISNULL(( SELECT TERM_CODE
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD02
                   ), '') AS termCode2 ,
            a.EXT_FIELD03 AS houseNumb3,
            ISNULL(( SELECT TERM_ID
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD03
                   ), '') AS termId3 ,
            ISNULL(( SELECT TERM_CODE
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD03
                   ), '') AS termCode3 ,
            a.EXT_FIELD04 AS houseNumb4,
            ISNULL(( SELECT TERM_ID
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD04
                   ), '') AS termId4 ,
            ISNULL(( SELECT TERM_CODE
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD04
                   ), '') AS termCode4 ,
            a.EXT_FIELD05 AS houseNumb5,
            ISNULL(( SELECT TERM_ID
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD05
                   ), '') AS termId5 ,
            ISNULL(( SELECT TERM_CODE
                     FROM   dbo.OA_T_INFOTERM
                     WHERE  ROOM_ID = a.ROOM_ID
                            AND HOUSE_NUMB = a.EXT_FIELD05
                   ), '') AS termCode5
    FROM    dbo.BUILD_T_ROOMINFO a;
 

GO


