USE [Q1_test6]
GO
/****** Object:  StoredProcedure [dbo].[P_DK_GetTermStatusTotal]    Script Date: 04/08/2018 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
电控统计报表（在原有的代码上，去掉了起始度数和最高度数）
*/
ALTER procedure [dbo].[P_DK_GetTermStatusTotal] 
(    
	@querySql VARCHAR(MAX),	
	@PageIndex int,						--页码
	@PageSize int						--页大小
)    

as   

begin

set nocount on;

if object_id(N'PT_EC_TERMSTATUS_INFO_TEMP',N'U') is not null
	drop table PT_EC_TERMSTATUS_INFO_TEMP
	
declare @sql1 varchar(8000);
declare @sql2 varchar(8000);
declare @count bigint;

--参数
IF(@querySql IS NULL or @querySql='') 
BEGIN
	SET @querySql='and 1=1';
END

set @sql1='
	SELECT A.TERMSN,A.TERMNO,A.TERMTYPEID,A.TERMNAME,A.GATEWAY_ID,B.ROOM_ID,C.ROOM_NAME,C.AREA_ID,
	SUM(B.USEKWH) AS sumdl
FROM 
	PT_TERM AS A
	JOIN PT_EC_TERMSTATUS B ON A.TERMSN=B.TERMSN
	JOIN dbo.BUILD_T_ROOMINFO C ON A.ROOM_ID=C.ROOM_ID		
	WHERE 1=1 and A.isDelete=0 and C.ROOM_TYPE!=''0'' '+@querySql+'
GROUP BY 
	A.TERMSN,A.TERMNO,A.TERMTYPEID,A.TERMNAME,A.GATEWAY_ID,B.ROOM_ID,C.ROOM_NAME,C.AREA_ID
';


set @sql2='select ROW_NUMBER() over(order by X.TERMNO ASC) as rownum,X.* into PT_EC_TERMSTATUS_INFO_TEMP from ('+@sql1+') X';
exec(@sql2);

if @PageIndex<=0
	set @PageIndex=1
 
if @PageSize<0
	set @PageSize=20 
 

select XX.*,E.GATEWAYNAME,D.NODE_TEXT from (
	select top (@PageSize) *
	from PT_EC_TERMSTATUS_INFO_TEMP  
	where rownum<=(@PageIndex*@PageSize) and rownum>((@PageIndex-1)*@PageSize)	
)XX
LEFT JOIN dbo.BUILD_T_ROOMAREA D ON XX.AREA_ID=D.AREA_ID
LEFT JOIN dbo.PT_GATEWAY E ON XX.GATEWAY_ID=E.GATEWAY_ID
union all
select count(1),NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from PT_EC_TERMSTATUS_INFO_TEMP

set nocount off;    
 
end  

--  EXEC PT_EC_TERMSTATUS_INFO NULL,1,30

--EXEC PT_EC_TERMSTATUS_INFO ' and b.room_id in (''602668B5-7D1C-42E9-B7C2-6159A2C02444'')','0','20'
