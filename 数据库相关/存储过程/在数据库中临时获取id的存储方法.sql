/*1.创建存储方法*/
/*	
*	获取主键ID；传入2位数的编码值
*	
*	手动初始化值：update T_PT_TempNo set NoValue=0 where NoType='idType';
*/
create function [dbo].[F_PT_GetKeyId]   
(   
	@code	varchar(2)
)   
RETURNS varchar(20)
AS   
BEGIN   
	
		DECLARE @return  VARCHAR(20)
		DECLARE @NoValue VARCHAR(6)
		DECLARE @value int
        declare @timeCode varchar(14);
        set @timeCode=CONVERT(varchar(8), GETDATE(), 12)+REPLACE(CONVERT(varchar(8), GETDATE(), 8),':','')
		
		SELECT  @value=NoValue FROM T_PT_TempNo WITH(ROWLOCK, UPDLOCK) where NoType='idType';

		IF @value Is Not Null
		BEGIN
			
			SET @NoValue = Right('000000' + Convert(NVARCHAR(6), @value), 6)
			
			set @return=@timeCode+@code+@NoValue;
		END
	
		return @return;
END   
GO
