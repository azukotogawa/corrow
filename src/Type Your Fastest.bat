@echo off
:: By: Cole Withington
:: Started: 5-4-12
:: Last Updated: 5-4-12
TITLE Type Your Fastest
echo GlauxTalons Productions
echo.
echo Loading...
ping localhost -n 5 >nul
cls
@echo off
TITLE Type Your Fastest (Pre-Alpha v.0.0.1)
:homeMain
cls
echo -------------------------------------------------------------------------------
echo                             Type Your Fastest
echo                              By: Cole Withington         Pre-Alpha v.0.0.1
echo -------------------------------------------------------------------------------
echo.
echo                                  1) Begin
echo                                  2) Info
echo                                  3) Exit
echo.                                     
set /p pass=.
                                              Enter:
if %pass% == 1 goto Start
if %pass% == 2 goto Info
if %pass% == 3 exit
goto home

:Start
cls
color 0f
setlocal enabledelayedexpansion
set /p name= Enter your name:
if exist TypeYourFastest%name%SAV.dll set load=1
goto new

:Info
cls
echo Type the words and letters as fast as you can! Don't make any mistakes or it will not go to the next word. You don't have to do uppercase.
pause
goto homeMain



:new
cls
echo Starting with: THE HOME ROW AND SPACEBAR (DON'T ADD SPACES)
echo                    Click the ENTER KEY to continue...
set /p pass=.
if %pass% == enter goto Ready

:Ready
cls
echo Ready
set /p pass=.
echo                    Click the ENTER KEY to continue...
if %pass% == enter goto Set

:Set
cls
echo Set
set /p pass=.
echo                    Click the ENTER KEY to continue...
if %pass% == goto GO

:GO
cls
echo GO!
@echo off
echo                 Type:   A  S  D  F  S  D  F  A  F  S  D  A  S  F  A  D 
set /p pass=.
if %pass% == asdfsdfafsdasfad goto RightHand
goto LeftHandFail

:RightHand
cls
echo   Good Job Now Type:  J  K  L  J  L  K  L  J  K  L  J  L  K  J  L  K
set /p pass=.
if %pass% == jkljlkljkljlkjlk goto BothHand
goto RightHandFail

:BothHand
cls
echo   Two in a row. Wow. Now type:  A  D  J  S  L  S  D  K  L  S  F  A  J  K  F  S 
set /p pass=.
if %pass% == adjslsdklsfajkfs goto Congrats
goto BothHandFail

:LeftHandFail
cls
echo            Sorry you failed.
echo   Type the letters again:  A  S  D  F  S  D  F  A  F  S  D  A  S  F  A  D
set /p pass=.
if %pass% == asdfsdfafsdasfad goto RightHand
goto LeftHandFail

:RightHandFail
cls
echo            Sorry you failed.
echo   Type the letters again:  J  K  L  J  L  K  L  J  K  L  J  L  K  J  L  K
set /p pass=.
if %pass% == jkljlkljkljlkjlk goto BothHand
goto RightHandFail

:BothHandFail
cls
echo            Sorry you failed.
echo   Type the letters again:   A  D  J  S  L  S  D  K  L  S  F  A  J  K  F  S
set /p pass=.
if %pass% == adjslsdklsfajkfs goto Congrats
goto BothHandFail     

:Congrats
cls
echo You did it!
pause
cls
goto homeMain