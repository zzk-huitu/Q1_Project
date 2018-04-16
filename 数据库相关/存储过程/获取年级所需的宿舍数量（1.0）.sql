USE [NewQ1_test]
GO
/****** Object:  StoredProcedure [dbo].[P_PT_GetGradeDormCount]    Script Date: 04/13/2018 17:54:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



ALTER PROC [dbo].[P_PT_GetGradeDormCount]
@gradeId VARCHAR(36)	--年级id
AS
DECLARE @dormCount INT --宿舍数量
DECLARE @studentCount INT --学生总数量
DECLARE @maleCount INT --男生数量
DECLARE @femaleCount INT --女生数量
--DECLARE @dorm INT --所需宿舍数量（由于宿舍人数不定，所以，此处由宿舍改为床位数）
--DECLARE @maledormCount INT	--男所需宿舍
--DECLARE @femaledormCount INT	--女所需宿舍
DECLARE @needDormBed INT	--所需宿舍床位数量
DECLARE @maleNeedBed INT	--男所需宿舍床位数量
DECLARE @femaleNeedBed INT	--女所需宿舍床位数量

DECLARE @maleDorm INT --男宿舍数量
DECLARE @femaleDorm INT --女宿舍数量
DECLARE @mixedDorm INT --混合宿舍数量


--:1：有效男宿舍（该班级有分配了班级宿舍，不过班级宿舍还没有班级学生入住，为0才可以一键分配宿舍）
SELECT @maleDorm=COUNT(*) FROM dbo.T_PT_ClassDormAllot a
JOIN dbo.T_PT_DormDefine b ON a.dormId=b.dormId
WHERE
classDormId NOT IN
(SELECT classDormId FROM dbo.T_PT_StudentDorm WHERE isDelete=0)--:只要这个班级存在宿舍 就排除在外
AND classId 
IN(SELECT classId FROM 
dbo.T_PT_GradeClass WHERE gradeId=@gradeId AND isDelete=0)
AND a.isDelete=0
AND b.dormType=1

--:2：有效女宿舍（该班级有分配了班级宿舍，不过班级宿舍还没有班级学生入住，为0才可以一键分配宿舍）
SELECT @femaleDorm=COUNT(*) FROM dbo.T_PT_ClassDormAllot a
JOIN dbo.T_PT_DormDefine b ON a.dormId=b.dormId
WHERE
classDormId NOT IN
(SELECT classDormId FROM dbo.T_PT_StudentDorm WHERE isDelete=0)
AND classId 
IN(SELECT classId FROM 
dbo.T_PT_GradeClass WHERE gradeId=@gradeId AND isDelete=0)
AND a.isDelete=0
AND b.dormType=2

--3：有效混合宿舍（该班级有分配了班级宿舍，不过班级宿舍还没有班级学生入住，为0才可以一键分配宿舍）
SELECT @mixedDorm=COUNT(*) FROM dbo.T_PT_ClassDormAllot a
JOIN dbo.T_PT_DormDefine b ON a.dormId=b.dormId
WHERE
classDormId NOT IN
(SELECT classDormId FROM dbo.T_PT_StudentDorm WHERE isDelete=0)
AND classId 
IN(SELECT classId FROM 
dbo.T_PT_GradeClass WHERE gradeId=@gradeId AND isDelete=0)
AND a.isDelete=0
AND b.dormType=3

SET @dormCount=@maleDorm+@femaleDorm+@mixedDorm

--4：学生总人数（未分配宿舍）
select @studentCount=count(*) from V_PT_ClassStudentList a
where a.gradeId =@gradeId
and a.userId not in (select studentId from T_PT_StudentDorm  where isDelete=0) 
AND a.sex IN(1,2)

IF(@studentCount IS NULL)
SET @studentCount=0

--5：男生总人数（未分配宿舍）
select @maleCount=count(*) from V_PT_ClassStudentList a
where a.gradeId =@gradeId
and a.userId not in (select studentId from T_PT_StudentDorm  where isDelete=0) 
AND a.sex=1
GROUP BY a.sex

IF(@maleCount IS NULL)
SET @maleCount=0

--6：女生总人数（未分配宿舍）
select @femaleCount=count(*) from V_PT_ClassStudentList a
where a.gradeId =@gradeId
and a.userId not in (select studentId from T_PT_StudentDorm  where isDelete=0) 
AND a.sex=2
GROUP BY a.sex 

IF(@femaleCount IS NULL)
SET @femaleCount=0

/*（由于宿舍人数不定，所以，此处由宿舍改为床位数）
IF(@maleCount%6=0)
 BEGIN
	SET @maledormCount=@maleCount/6-@maleDorm
 END
 ELSE
 BEGIN
	SET @maledormCount=@maleCount/6+1-@maleDorm
 END
 
 IF(@femaleCount%6=0)
 BEGIN
	SET @femaledormCount=@femaleCount/6-@femaleDorm
 END
 ELSE
 BEGIN
	SET @femaledormCount=@femaleCount/6+1-@femaleDorm
 END
 
 --计算总共所需宿舍
SET @dorm=@maledormCount+@femaledormCount
*/
set @needDormBed=@studentCount;
set @maleNeedBed=@maleCount;
set @femaleNeedBed=@femaleCount;
/*
PRINT '所需宿舍'+CONVERT(VARCHAR(100),@dorm)+
'学生人数'+CONVERT(VARCHAR(100),@studentCount)+'有效宿舍'+CONVERT(VARCHAR(100),@dormCount)
*/
SELECT @maleCount AS maleCount,@femaleCount AS femaleCount,@studentCount AS studentCount,
@maleNeedBed AS maleNeedBed,@femaleNeedBed AS femaleNeedBed, @needDormBed AS needDormBed,
@maleDorm AS maleDorm,@femaleDorm AS femaleDorm,@mixedDorm AS mixedDorm,@dormCount AS dormCount





