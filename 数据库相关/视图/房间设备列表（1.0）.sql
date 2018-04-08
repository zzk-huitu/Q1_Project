
GO

/****** Object:  View [dbo].[V_PT_RoomTermList]    Script Date: 04/08/2018 17:53:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2017-02-06
-- Description:	������󶨵��ն��豸������ͼ
-- �ֶ�˵��:   
--      roomId: ����ID
--		roomCode:�������
--		roomName:��������
--		houseNumb1:���ƺ�1
--		termId1:���ƺ�1��Ӧ���ն�ID 
--		termCode1:���ƺ�1��Ӧ���ն˱���
--      �����termId2��termId3��termId4��termId5�ֱ��Ӧ5�����ƺ�
-- �������ݱ�˵��:
		--BUILD_T_ROOMINFO:������Ϣ
		--OA_T_INFOTERM:�ն��豸��Ϣ
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


