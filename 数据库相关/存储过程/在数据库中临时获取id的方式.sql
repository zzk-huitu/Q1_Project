/*1.创建存储*/
/*	
*	获取主键ID；传入2位数的编码值
*	
*	手动初始化值：update T_PT_TempNo set NoValue=0 where NoType='idType';
*/

alter PROCEDURE [dbo].[P_PT_GetKeyId]   
(   
	@code	varchar(2)
)   

AS   
BEGIN   
	
		BEGIN TRANSACTION
		
		DECLARE @return  VARCHAR(200)
		DECLARE @NoValue VARCHAR(6)
		DECLARE @value int
        declare @timeCode varchar(14);
        set @timeCode=CONVERT(varchar(8), GETDATE(), 12)+REPLACE(CONVERT(varchar(8), GETDATE(), 8),':','')
		
		
		SELECT  @value=NoValue FROM T_PT_TempNo WITH(ROWLOCK, UPDLOCK) where NoType='idType';

		IF @value Is Not Null
		BEGIN
			
			IF @value + 1 > 99999999
			BEGIN
				SET @value = 0
			END
			
			set @value=@value+1;
			
			SET @NoValue = Right('000000' + Convert(NVARCHAR(6), @value), 6)
			
			set @return=@timeCode+@code+@NoValue;
			Update T_PT_TempNo SET NoValue = @value WHERE NoType='idType';
		END
	
		COMMIT TRANSACTION 
		
		select  @return

END   
GO


