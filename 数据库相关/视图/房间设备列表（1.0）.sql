USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_RoomTermList]    Script Date: 04/16/2018 18:39:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


 -- =============================================
-- Author:		LUOYIBO
-- Create date: 2018-04-09
-- Description:	������󶨵��ն��豸������ͼ
-- �ֶ�˵��:   
--      roomId: ����ID
--		roomCode:�������
--		roomName:��������
--		houseNo01:���ƺ�1
--		terminalId1:���ƺ�1��Ӧ���ն�ID 
--		terminalNo1:���ƺ�1��Ӧ���ն˱���
--      �����terminalId2��terminalId3��terminalId4��terminalId5�ֱ��Ӧ5�����ƺ�
-- �������ݱ�˵��:
		--T_PT_RoomInfo:������Ϣ
		--T_PT_InfoTerminal:�ն��豸��Ϣ
-- =============================================
ALTER VIEW [dbo].[V_PT_RoomTermList]
AS
    SELECT  a.roomId AS roomId ,
            a.roomCode AS roomCode,
            a.roomName AS roomName ,
            a.houseNo01 AS houseNo01 ,
            ISNULL(( SELECT terminalId
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo01
                   ), '') AS terminalId1 ,
            ISNULL(( SELECT terminalNo
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo01
                   ), '') AS terminalNo1 ,
            a.houseNo02 AS houseNo02 ,
            ISNULL(( SELECT terminalId
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo02
                   ), '') AS terminalId2 ,
            ISNULL(( SELECT terminalNo
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo02
                   ), '') AS terminalNo2 ,
            a.houseNo03 AS houseNo03,
            ISNULL(( SELECT terminalId
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo03
                   ), '') AS terminalId3 ,
            ISNULL(( SELECT terminalNo
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo03
                   ), '') AS terminalNo3 ,
            a.houseNo04 AS houseNo04,
            ISNULL(( SELECT terminalId
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo04
                   ), '') AS terminalId4 ,
            ISNULL(( SELECT terminalNo
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo04
                   ), '') AS terminalNo4 ,
            a.houseNo05 AS houseNo05,
            ISNULL(( SELECT terminalId
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo05
                   ), '') AS terminalId5 ,
            ISNULL(( SELECT terminalNo
                     FROM   dbo.T_PT_InfoTerminal
                     WHERE  roomId = a.roomId
                            AND houseNo = a.houseNo05
                   ), '') AS terminalNo5
    FROM    dbo.T_PT_RoomInfo a;
 


GO


