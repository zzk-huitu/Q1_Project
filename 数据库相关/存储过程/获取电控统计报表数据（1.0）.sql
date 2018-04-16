USE [NewQ1_test]
GO
/****** Object:  StoredProcedure [dbo].[P_DK_GetTermStatusTotal]    Script Date: 04/13/2018 17:53:43 ******/
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
	SELECT A.termSn,A.termNo,A.termTypeId,A.termName,A.gatewayId,B.roomId,C.roomName,C.areaId,
	SUM(B.useKwh) AS sumdl
FROM 
	T_PT_Term AS A
	JOIN T_DK_TermStatus B ON A.termSn=B.termSn
	JOIN dbo.T_PT_RoomInfo C ON A.roomId=C.roomId		
	WHERE 1=1 and A.isDelete=0 and C.roomType!=''0'' '+@querySql+'
GROUP BY 
	A.termSn,A.termNo,A.termTypeId,A.termName,A.gatewayId,B.roomId,C.roomName,C.areaId
';


set @sql2='select ROW_NUMBER() over(order by X.termNo ASC) as rownum,X.* into PT_EC_TERMSTATUS_INFO_TEMP from ('+@sql1+') X';
exec(@sql2);

if @PageIndex<=0
	set @PageIndex=1
 
if @PageSize<0
	set @PageSize=20 
 

select XX.*,E.gatewayName,D.nodeText from (
	select top (@PageSize) *
	from PT_EC_TERMSTATUS_INFO_TEMP  
	where rownum<=(@PageIndex*@PageSize) and rownum>((@PageIndex-1)*@PageSize)	
)XX
LEFT JOIN dbo.T_PT_RoomArea D ON XX.areaId=D.areaId
LEFT JOIN dbo.T_PT_Gateway E ON XX.gatewayId=E.gatewayId
union all
select count(1),NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from PT_EC_TERMSTATUS_INFO_TEMP

set nocount off;    
 
end  

--  EXEC PT_EC_TERMSTATUS_INFO NULL,1,30

--EXEC PT_EC_TERMSTATUS_INFO ' and b.roomId in (''602668B5-7D1C-42E9-B7C2-6159A2C02444'')','0','20'


