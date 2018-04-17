USE [NewQ1_test]
GO

/****** Object:  View [dbo].[V_PT_StudentDormList]    Script Date: 04/16/2018 18:39:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[V_PT_StudentDormList] 
AS

SELECT 
	T_PT_User.[userId], 
	T_PT_DormDefine.roomId,
	T_PT_RoomInfo.roomCode,
	T_PT_RoomInfo.houseNo01,
	T_PT_RoomInfo.areaId,
	T_PT_RoomInfo.roomName,
	T_PT_User.name
FROM      
	T_PT_User 
	INNER JOIN T_PT_StudentDorm ON T_PT_User.[userId] = T_PT_StudentDorm.studentId AND T_PT_User.category = 2 AND T_PT_StudentDorm.isDelete = 0 
	INNER JOIN T_PT_ClassDormAllot ON T_PT_StudentDorm.classDormId = T_PT_ClassDormAllot.classDormId AND T_PT_ClassDormAllot.isDelete = 0 
	INNER JOIN T_PT_DormDefine ON T_PT_ClassDormAllot.dormId = T_PT_DormDefine.dormId AND T_PT_DormDefine.isDelete = 0 
	INNER JOIN T_PT_RoomInfo ON T_PT_DormDefine.roomId = T_PT_RoomInfo.roomId AND T_PT_RoomInfo.isDelete = 0 





GO


