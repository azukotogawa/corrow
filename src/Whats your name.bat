@echo off 
echo Hi, Whats your name?
set /p name=.
goto next

:next
cls 
echo Hi, %name% my name is Cole.
pause
cls
echo If you are a zombie click the Enter key.
echo If not, become one, then click the Enter Key
set /p pass=.
cls
echo DIE BITCH! *gunshots*
pause