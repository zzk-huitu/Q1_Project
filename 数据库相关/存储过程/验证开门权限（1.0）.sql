USE [Q1_test6]
GO
/****** Object:  StoredProcedure [dbo].[P_MJ_CheckUserMjAuth]    Script Date: 04/08/2018 16:10:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/********************************************************* 
	验证开门权限
	使用存储过程验证权限是为了以后验证权限的规则有变化可以直接修改存储过程即可

	date:			2017-01-15
	author:			TanJun
	description:	创建存储过程
*********************************************************/
ALTER PROCEDURE [dbo].[P_MJ_CheckUserMjAuth]
(		
	@USER_ID VARCHAR(36),
	@TERM_ID VARCHAR(36),
	@ROOM_ID VARCHAR(36),
	@RETURD_CODE INT OUTPUT
)
AS

SET @RETURD_CODE = -1

IF (@USER_ID = 'f111ebab-933b-4e48-b328-c731ae792ca0')
BEGIN
	SET @RETURD_CODE = 0
	RETURN 0
END

IF EXISTS (SELECT * FROM PT_MJ_USERRIGHT WHERE [STU_ID] = @USER_ID AND TERM_ID = @TERM_ID)
BEGIN
	SET @RETURD_CODE = 0
	RETURN 0
END

IF EXISTS (SELECT * FROM PT_V_STUDENTROOM WHERE [USER_ID] = @USER_ID AND ROOM_ID = @ROOM_ID)
BEGIN
	SET @RETURD_CODE = 0
	RETURN 0
END

IF EXISTS (SELECT * FROM PT_V_TEACHERROOM WHERE [USER_ID] = @USER_ID AND ROOM_ID = @ROOM_ID)
BEGIN
	SET @RETURD_CODE = 0
	RETURN 0
END

SET @RETURD_CODE = 1