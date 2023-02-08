@echo off
arp -a
set /p s=
ping -a %s%
set /p p=
set /p c=
shutdown /s /m \\%p% /c "%c%" /f
pause>10
ping %p% -n 1 -w 60000 >NUL
pause