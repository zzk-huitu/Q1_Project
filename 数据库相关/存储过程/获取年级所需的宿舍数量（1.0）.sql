USE [Q1_test6]
GO
/*获取年级所需的宿舍数量（分配年级下的班级宿舍）*/
/****** Object:  StoredProcedure [dbo].[P_PT_GetGradeDormCount]    Script Date: 04/08/2018 15:59:00 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER PROC [dbo].[P_PT_GetGradeDormCount]
@graiId VARCHAR(36)	--年级id
AS
DECLARE @DORMCOUNT INT --宿舍数量
DECLARE @STUDENTCOUNT INT --学生总数量
DECLARE @nanCount INT --男生数量
DECLARE @nvCount INT --女生数量
--DECLARE @dorm INT --所需宿舍数量（由于宿舍人数不定，所以，此处由宿舍改为床位数）
--DECLARE @nanDormCount INT	--男所需宿舍
--DECLARE @nvDormCount INT	--女所需宿舍
DECLARE @dormBed INT	--所需宿舍床位数量
DECLARE @nanDormBed INT	--男所需宿舍床位数量
DECLARE @nvDormBed INT	--女所需宿舍床位数量

DECLARE @nanDorm INT --男宿舍数量
DECLARE @nvDorm INT --女宿舍数量
DECLARE @hunDorm INT --混合宿舍数量


--:1：有效男宿舍（该班级有分配了班级宿舍，不过班级宿舍还没有班级学生入住，为0才可以一键分配宿舍）
SELECT @nanDorm=COUNT(*) FROM dbo.JW_T_CLASSDORMALLOT a
JOIN dbo.BUILD_T_DORMDEFINE b ON a.DORM_ID=b.DORM_ID
WHERE
CDORM_ID NOT IN
(SELECT CDORM_ID FROM dbo.DORM_T_STUDENTDORM WHERE ISDELETE=0)--:只要这个班级存在宿舍 就排除在外
AND CLAI_ID 
IN(SELECT CLAI_ID FROM 
dbo.JW_T_GRADECLASS WHERE GRAI_ID=@graiId AND ISDELETE=0)
AND a.ISDELETE=0
AND b.DORM_TYPE=1

--:2：有效女宿舍（该班级有分配了班级宿舍，不过班级宿舍还没有班级学生入住，为0才可以一键分配宿舍）
SELECT @nvDorm=COUNT(*) FROM dbo.JW_T_CLASSDORMALLOT a
JOIN dbo.BUILD_T_DORMDEFINE b ON a.DORM_ID=b.DORM_ID
WHERE
CDORM_ID NOT IN
(SELECT CDORM_ID FROM dbo.DORM_T_STUDENTDORM WHERE ISDELETE=0)
AND CLAI_ID 
IN(SELECT CLAI_ID FROM 
dbo.JW_T_GRADECLASS WHERE GRAI_ID=@graiId AND ISDELETE=0)
AND a.ISDELETE=0
AND b.DORM_TYPE=2

--3：有效混合宿舍（该班级有分配了班级宿舍，不过班级宿舍还没有班级学生入住，为0才可以一键分配宿舍）
SELECT @hunDorm=COUNT(*) FROM dbo.JW_T_CLASSDORMALLOT a
JOIN dbo.BUILD_T_DORMDEFINE b ON a.DORM_ID=b.DORM_ID
WHERE
CDORM_ID NOT IN
(SELECT CDORM_ID FROM dbo.DORM_T_STUDENTDORM WHERE ISDELETE=0)
AND CLAI_ID 
IN(SELECT CLAI_ID FROM 
dbo.JW_T_GRADECLASS WHERE GRAI_ID=@graiId AND ISDELETE=0)
AND a.ISDELETE=0
AND b.DORM_TYPE=3

SET @DORMCOUNT=@nanDorm+@nvDorm+@hunDorm

--4：学生总人数（未分配宿舍）
select @STUDENTCOUNT=count(*) from STAND_V_CLASSSTUDENT a
where a.gradeId =@graiId
and a.userId not in (select STU_ID from DORM_T_STUDENTDORM  where isDelete=0) 
AND a.XBM IN(1,2)

IF(@STUDENTCOUNT IS NULL)
SET @STUDENTCOUNT=0

--5：男生总人数（未分配宿舍）
select @nanCount=count(*) from STAND_V_CLASSSTUDENT a
where a.gradeId =@graiId
and a.userId not in (select STU_ID from DORM_T_STUDENTDORM  where isDelete=0) 
AND a.XBM=1
GROUP BY a.XBM

IF(@nanCount IS NULL)
SET @nanCount=0

--6：女生总人数（未分配宿舍）
select @nvCount=count(*) from STAND_V_CLASSSTUDENT a
where a.gradeId =@graiId
and a.userId not in (select STU_ID from DORM_T_STUDENTDORM  where isDelete=0) 
AND a.XBM=2
GROUP BY a.XBM 

IF(@nvCount IS NULL)
SET @nvCount=0

/*（由于宿舍人数不定，所以，此处由宿舍改为床位数）
IF(@nanCount%6=0)
 BEGIN
	SET @nanDormCount=@nanCount/6-@nanDorm
 END
 ELSE
 BEGIN
	SET @nanDormCount=@nanCount/6+1-@nanDorm
 END
 
 IF(@nvCount%6=0)
 BEGIN
	SET @nvDormCount=@nvCount/6-@nvDorm
 END
 ELSE
 BEGIN
	SET @nvDormCount=@nvCount/6+1-@nvDorm
 END
 
 --计算总共所需宿舍
SET @dorm=@nanDormCount+@nvDormCount
*/
set @dormBed=@STUDENTCOUNT;
set @nanDormBed=@nanCount;
set @nvDormBed=@nvCount;
/*
PRINT '所需宿舍'+CONVERT(VARCHAR(100),@dorm)+
'学生人数'+CONVERT(VARCHAR(100),@STUDENTCOUNT)+'有效宿舍'+CONVERT(VARCHAR(100),@DORMCOUNT)
*/
SELECT @nanCount AS nanCount,@nvCount AS nvCount,@STUDENTCOUNT AS stuCount,
@nanDormBed AS nanDormBed,@nvDormBed AS nvDormBed, @dormBed AS sxDormBed,
@nanDorm AS nanDorm,@nvDorm AS nvDorm,@hunDorm AS hunDorm,@DORMCOUNT AS yxDorm




GO


