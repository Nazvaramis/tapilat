CREATE PROCEDURE [dbo].[Login]

@Username VARCHAR(50),
@Password VARCHAR(50)

AS
BEGIN

SET NOCOUNT ON;

IF EXISTS ( SELECT 1 FROM LoginCredentials WHERE Username = @Username AND Password = @Password)

SELECT 'Login successful' AS Status;
ELSE
SELECT 'Invalid username or password' AS Status;

END







