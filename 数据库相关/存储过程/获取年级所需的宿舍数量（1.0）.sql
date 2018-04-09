USE [Q1_test6]
GO
/*��ȡ�꼶��������������������꼶�µİ༶���ᣩ*/
/****** Object:  StoredProcedure [dbo].[P_PT_GetGradeDormCount]    Script Date: 04/08/2018 15:59:00 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


ALTER PROC [dbo].[P_PT_GetGradeDormCount]
@graiId VARCHAR(36)	--�꼶id
AS
DECLARE @DORMCOUNT INT --��������
DECLARE @STUDENTCOUNT INT --ѧ��������
DECLARE @nanCount INT --��������
DECLARE @nvCount INT --Ů������
--DECLARE @dorm INT --�����������������������������������ԣ��˴��������Ϊ��λ����
--DECLARE @nanDormCount INT	--����������
--DECLARE @nvDormCount INT	--Ů��������
DECLARE @dormBed INT	--�������ᴲλ����
DECLARE @nanDormBed INT	--���������ᴲλ����
DECLARE @nvDormBed INT	--Ů�������ᴲλ����

DECLARE @nanDorm INT --����������
DECLARE @nvDorm INT --Ů��������
DECLARE @hunDorm INT --�����������


--:1����Ч�����ᣨ�ð༶�з����˰༶���ᣬ�����༶���ỹû�а༶ѧ����ס��Ϊ0�ſ���һ���������ᣩ
SELECT @nanDorm=COUNT(*) FROM dbo.JW_T_CLASSDORMALLOT a
JOIN dbo.BUILD_T_DORMDEFINE b ON a.DORM_ID=b.DORM_ID
WHERE
CDORM_ID NOT IN
(SELECT CDORM_ID FROM dbo.DORM_T_STUDENTDORM WHERE ISDELETE=0)--:ֻҪ����༶�������� ���ų�����
AND CLAI_ID 
IN(SELECT CLAI_ID FROM 
dbo.JW_T_GRADECLASS WHERE GRAI_ID=@graiId AND ISDELETE=0)
AND a.ISDELETE=0
AND b.DORM_TYPE=1

--:2����ЧŮ���ᣨ�ð༶�з����˰༶���ᣬ�����༶���ỹû�а༶ѧ����ס��Ϊ0�ſ���һ���������ᣩ
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

--3����Ч������ᣨ�ð༶�з����˰༶���ᣬ�����༶���ỹû�а༶ѧ����ס��Ϊ0�ſ���һ���������ᣩ
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

--4��ѧ����������δ�������ᣩ
select @STUDENTCOUNT=count(*) from STAND_V_CLASSSTUDENT a
where a.gradeId =@graiId
and a.userId not in (select STU_ID from DORM_T_STUDENTDORM  where isDelete=0) 
AND a.XBM IN(1,2)

IF(@STUDENTCOUNT IS NULL)
SET @STUDENTCOUNT=0

--5��������������δ�������ᣩ
select @nanCount=count(*) from STAND_V_CLASSSTUDENT a
where a.gradeId =@graiId
and a.userId not in (select STU_ID from DORM_T_STUDENTDORM  where isDelete=0) 
AND a.XBM=1
GROUP BY a.XBM

IF(@nanCount IS NULL)
SET @nanCount=0

--6��Ů����������δ�������ᣩ
select @nvCount=count(*) from STAND_V_CLASSSTUDENT a
where a.gradeId =@graiId
and a.userId not in (select STU_ID from DORM_T_STUDENTDORM  where isDelete=0) 
AND a.XBM=2
GROUP BY a.XBM 

IF(@nvCount IS NULL)
SET @nvCount=0

/*�����������������������ԣ��˴��������Ϊ��λ����
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
 
 --�����ܹ���������
SET @dorm=@nanDormCount+@nvDormCount
*/
set @dormBed=@STUDENTCOUNT;
set @nanDormBed=@nanCount;
set @nvDormBed=@nvCount;
/*
PRINT '��������'+CONVERT(VARCHAR(100),@dorm)+
'ѧ������'+CONVERT(VARCHAR(100),@STUDENTCOUNT)+'��Ч����'+CONVERT(VARCHAR(100),@DORMCOUNT)
*/
SELECT @nanCount AS nanCount,@nvCount AS nvCount,@STUDENTCOUNT AS stuCount,
@nanDormBed AS nanDormBed,@nvDormBed AS nvDormBed, @dormBed AS sxDormBed,
@nanDorm AS nanDorm,@nvDorm AS nvDorm,@hunDorm AS hunDorm,@DORMCOUNT AS yxDorm




GO


